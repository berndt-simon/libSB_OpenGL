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
package libSB.openGL.utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES2;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Berndt
 */
public final class ShaderUtil {

    private static final Logger LOG = Logger.getLogger(ShaderUtil.class.getName());

    private ShaderUtil() {
    }

    public static OptionalInt createVertexShader(GL2ES2 gl, String shaderSource) {
	return createShader(gl, GL_VERTEX_SHADER, shaderSource);
    }

    public static OptionalInt createFragmentShader(GL2ES2 gl, String shaderSource) {
	return createShader(gl, GL_FRAGMENT_SHADER, shaderSource);
    }

    public static OptionalInt createShader(GL2ES2 gl, int shaderType, String... source) {
	if (source == null || source.length == 0) {
	    LOG.log(Level.WARNING, "Source is missing");
	    return OptionalInt.empty();
	}
	gl.glGetError();
	int shaderId = gl.glCreateShader(shaderType);
	gl.glShaderSource(shaderId, source.length, source, null, 0);
	gl.glCompileShader(shaderId);
	Optional<ShaderError> error = retrieveError(gl, shaderId);
	if (error.isPresent()) {
	    gl.glDeleteShader(shaderId);
	    LOG.log(Level.WARNING, error.get().getErrorMessage());
	    return OptionalInt.empty();
	}
	return OptionalInt.of(shaderId);
    }

    public static Optional<ShaderError> retrieveError(GL2ES2 gl, int shaderObjectId) {
	int checkError = gl.glGetError();
	if (checkError != GL.GL_NO_ERROR) {
	    return Optional.of(ShaderError.create(gl, shaderObjectId));
	}
	return Optional.empty();
    }

    public static void deleteShader(GL2ES2 gl, int shaderObjectId) {
	gl.glDeleteShader(shaderObjectId);
    }

    public static void deleteShaderProgram(GL2ES2 gl, int shaderProgramObjectId) {
	gl.glDeleteProgram(shaderProgramObjectId);
    }

}
