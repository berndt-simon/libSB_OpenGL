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
package libSB.openGL.postProcessing.filter;

import com.jogamp.common.nio.Buffers;
import java.nio.FloatBuffer;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.logging.Level;
import javax.media.opengl.GL2;
import libSB.openGL.objectfied.ShaderProgram;
import libSB.openGL.utils.ShaderUtil;
import libSB.openGL.utils.UniformUtil;

/**
 *
 * @author Simon Berndt
 */
@Deprecated
public class StaticKernel implements PostProcessingFilter {

    private static final String NL = System.lineSeparator();
    private static final String TAB = "\t";

    private static final String UNIFORM_NAME_SAMPLER0 = "sampler0";
    private static final String UNIFORM_NAME_TEXTURE_COORDINATE_OFFSET = "textureCoordinateOffset";

    private final float[] kernel;
    private final float fac;
    
    private int linkedShader;
    private ShaderProgram linkedProgram;
    
    private FloatBuffer textureCoordinateOffset;

    public StaticKernel(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float fac) {
	this.kernel = new float[]{m00, m01, m02, m10, m11, m12, m20, m21, m22};
	this.fac = fac;
	
	this.linkedShader = 0;
	this.linkedProgram = ShaderProgram.NULL_PROGRAM;
	
	this.textureCoordinateOffset = Buffers.newDirectFloatBuffer(2);
    }
    
    public void setTextureOffsetX(float offset) {
	textureCoordinateOffset.put(0, offset);
    }
    
    public void setTextureOffsetY(float offset) {
	textureCoordinateOffset.put(1, offset);
    }
    
    public void setTextureOffset(float offsetX, float offsetY) {
	textureCoordinateOffset.put(0, offsetX);
	textureCoordinateOffset.put(1, offsetY);
    }
    
    public float getTextureOffsetX() {
	return textureCoordinateOffset.get(0);
    }
    
    public float getTextureOffsetY() {
	return textureCoordinateOffset.get(1);
    }

    @Override
    public void allocateFilterResources(GL2 gl) {
	OptionalInt shader = ShaderUtil.createFragmentShader(gl, createSource());
	if (shader.isPresent()) {
	    linkedShader = shader.getAsInt();
	    Optional<ShaderProgram> program = ShaderUtil.createSingleShaderProgram(gl, linkedShader);
	    if (program.isPresent()) {
		linkedProgram = program.get();
	    } else {
		linkedProgram = ShaderProgram.NULL_PROGRAM;
	    }
	}
    }

    @Override
    public void freeFilterResources(GL2 gl) {
	if (!ShaderProgram.NULL_PROGRAM.equals(linkedProgram)) {
	    ShaderUtil.deleteShaderProgram(gl, linkedProgram.getShaderProgramId());
	    linkedProgram = ShaderProgram.NULL_PROGRAM;
	}
	if (linkedShader != 0) {
	    ShaderUtil.deleteShader(gl, linkedShader);
	    linkedShader = 0;
	}
    }

    @Override
    public void startProgramUse(GL2 gl) {
	if (linkedProgram == 0 || linkedShader == 0) {
	    LOG.log(Level.WARNING, "No Shader-Program linked");
	} else {
	    gl.glUseProgram(linkedProgram);
	    UniformUtil.setUniform1i(gl, linkedShader, UNIFORM_NAME_SAMPLER0, 0);
	    UniformUtil.setUniform2fv(gl, linkedProgram, UNIFORM_NAME_TEXTURE_COORDINATE_OFFSET, textureCoordinateOffset);
	    gl.glValidateProgram(linkedProgram);
	}
    }

    @Override
    public void stopProgramUse(GL2 gl) {
	gl.glUseProgram(0);
    }

    private String createSource() {
	StringBuilder shaderSource = new StringBuilder();
	shaderSource.append("uniform sampler2D ").append(UNIFORM_NAME_SAMPLER0).append(";").append(NL);
	shaderSource.append("uniform vec2 ").append(UNIFORM_NAME_TEXTURE_COORDINATE_OFFSET).append("[9];").append(NL);
	shaderSource.append(NL);
	shaderSource.append("void main(void) {").append(NL);
	shaderSource.append(TAB).append("vec4 sample[9];").append(NL);
	shaderSource.append(TAB).append("for (int i = 0; i < 9; i++) {").append(NL);
	shaderSource.append(TAB).append(TAB).append("sample[i] = texture2D(")
		.append(UNIFORM_NAME_SAMPLER0).append(", gl_TexCoord[0].st + ")
		.append(UNIFORM_NAME_TEXTURE_COORDINATE_OFFSET).append("[i]);").append(NL);
	shaderSource.append(TAB).append("}").append(NL);
	shaderSource.append(TAB).append("gl_FragColor = ((")
		.append(Float.toString(kernel[0])).append("*sample[0]) + (")
		.append(Float.toString(kernel[1])).append("*sample[1]) + (")
		.append(Float.toString(kernel[2])).append("*sample[2]) + (")
		
		.append(Float.toString(kernel[3])).append("*sample[3]) + (")
		.append(Float.toString(kernel[4])).append("*sample[4]) + (")
		.append(Float.toString(kernel[5])).append("*sample[5]) + (")
		
		.append(Float.toString(kernel[6])).append("*sample[6]) + (")
		.append(Float.toString(kernel[7])).append("*sample[7]) + (")
		.append(Float.toString(kernel[8])).append("*sample[8])) / ")
		.append(Float.toString(fac)).append(";").append(NL);
	shaderSource.append("}").append(NL);
	return shaderSource.toString();
    }

}
