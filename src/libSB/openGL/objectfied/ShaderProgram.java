/* 
 * The MIT License
 *
 * Copyright 2015 Simon Berndt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package libSB.openGL.objectfied;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES2;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import libSB.openGL.utils.ShaderError;
import libSB.openGL.utils.ShaderUtil;

/**
 *
 * @author Simon Berndt
 */
public final class ShaderProgram implements GLBlock<GL2ES2>, GLClosable<GL2ES2> {

    private static final Logger LOG = Logger.getLogger(ShaderProgram.class.getName());

    public static final ShaderProgram NULL_PROGRAM = new ShaderProgram();

    private final int shaderProgramId;
    private final int[] shaderIds;

    private ShaderProgram() {
	this.shaderProgramId = 0;
	this.shaderIds = new int[0];
    }
    
    private ShaderProgram(int shaderProgramId, int usedShaderId) {
	this.shaderProgramId = shaderProgramId;
	this.shaderIds = new int[]{usedShaderId};
    }

    private ShaderProgram(int shaderProgramId, int[] usedShaderIds) {
	this.shaderProgramId = shaderProgramId;
	this.shaderIds = usedShaderIds;
    }

    public static Optional<ShaderProgram> create(GL2ES2 gl, int shaderId) {
	gl.glGetError();
	if (!gl.glIsShader(shaderId)) {
	    LOG.log(Level.WARNING, "Given Object-Id is not a Shader");
	    return Optional.empty();
	}
	int programId = gl.glCreateProgram();
	gl.glAttachShader(programId, shaderId);
	gl.glLinkProgram(programId);
	gl.glValidateProgram(programId);
	gl.glDetachShader(programId, shaderId);
	Optional<ShaderError> error = ShaderUtil.retrieveError(gl, programId);
	if (error.isPresent()) {
	    gl.glDeleteProgram(programId);
            LOG.log(Level.WARNING, "An Error occured:\n{0}", error.get().getErrorMessage());
	    return Optional.empty();
	}
	return Optional.of(new ShaderProgram(programId, shaderId));
    }
    
    public static Optional<ShaderProgram> create(GL2ES2 gl, int shaderId, int... additionalShaderIds) {
	gl.glGetError();
	int[] shaderIds = new int[additionalShaderIds.length + 1];
	shaderIds[0] = shaderId;
	IntStream.range(0, additionalShaderIds.length).forEach((int i) -> {
	    shaderIds[i+1] = additionalShaderIds[i];
	});
	if (!Arrays.stream(shaderIds).allMatch(gl::glIsShader)) {
	    LOG.log(Level.WARNING, "One of the given Object-Ids is not a Shader");
            return Optional.empty();
        }
        int programId = gl.glCreateProgram();
	Arrays.stream(shaderIds).forEach((int shader) -> gl.glAttachShader(programId, shader));
        gl.glLinkProgram(programId);
        gl.glValidateProgram(programId);
//	Arrays.stream(shaderIds).forEach((int shader) -> gl.glDetachShader(programId, shader));
        Optional<ShaderError> error = ShaderUtil.retrieveError(gl, programId);
        if (error.isPresent()) {
            gl.glDeleteProgram(programId);
            LOG.log(Level.WARNING, "An Error occured:\n{0}", error.get().getErrorMessage());
            return Optional.empty();
        }
        return Optional.of(new ShaderProgram(programId, shaderIds));
    }

    @Override
    public void freeResources(GL2ES2 gl) {
	gl.glGetError();
	
	gl.glDeleteProgram(shaderProgramId);
	Arrays.stream(shaderIds).forEach(gl::glDeleteShader);
	
	int checkError = gl.glGetError();
	if (checkError != GL.GL_NO_ERROR) {
	    LOG.log(Level.WARNING, "An Error occured while trying to free Resources of Shader-Program");
	}
    }

    @Override
    public void beginBlock(GL2ES2 gl) {
	gl.glUseProgram(shaderProgramId);
    }

    @Override
    public void endBlock(GL2ES2 gl) {
	gl.glUseProgram(0);
    }

    public int getShaderProgramId() {
	return shaderProgramId;
    }

    public int[] getUsedShaderIds() {
	return Arrays.copyOf(shaderIds, shaderIds.length);
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 37 * hash + this.shaderProgramId;
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final ShaderProgram other = (ShaderProgram) obj;
	if (this.shaderProgramId != other.shaderProgramId) {
	    return false;
	}
	return true;
    }

}
