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

import com.jogamp.opengl.GL2ES2;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Berndt
 */
public final class UniformUtil {

    private static final Logger LOG = Logger.getLogger(UniformUtil.class.getName());
    private static final String WARNING_TEMPLATE = "Can\'t resolve Uniform \'{0}\'";

    private UniformUtil() {
    }

    public static OptionalInt getUniformId(GL2ES2 gl, int shaderProgramId, String uniformName) {
	int uniformId = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformId != -1) {
	    return OptionalInt.of(uniformId);
	} else {
	    return OptionalInt.empty();
	}
    }

    public static void setUniform1f(GL2ES2 gl, int shaderProgramId, String uniformName, float x) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform1f(uniformLocation, x);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform1fv(GL2ES2 gl, int shaderProgramId, String uniformName, FloatBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform1fv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform1i(GL2ES2 gl, int shaderProgramId, String uniformName, int x) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform1i(uniformLocation, x);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform1iv(GL2ES2 gl, int shaderProgramId, String uniformName, IntBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform1iv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform2f(GL2ES2 gl, int shaderProgramId, String uniformName, float x, float y) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform2f(uniformLocation, x, y);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform2fv(GL2ES2 gl, int shaderProgramId, String uniformName, FloatBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform2fv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform2i(GL2ES2 gl, int shaderProgramId, String uniformName, int x, int y) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform2i(uniformLocation, x, y);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform2iv(GL2ES2 gl, int shaderProgramId, String uniformName, IntBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform2iv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform3f(GL2ES2 gl, int shaderProgramId, String uniformName, float x, float y, float z) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform3f(uniformLocation, x, y, z);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform3fv(GL2ES2 gl, int shaderProgramId, String uniformName, FloatBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform3fv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform3i(GL2ES2 gl, int shaderProgramId, String uniformName, int x, int y, int z) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform3i(uniformLocation, x, y, z);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform3iv(GL2ES2 gl, int shaderProgramId, String uniformName, IntBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform3iv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform4f(GL2ES2 gl, int shaderProgramId, String uniformName, float x, float y, float z, float w) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform4f(uniformLocation, x, y, z, w);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform4fv(GL2ES2 gl, int shaderProgramId, String uniformName, FloatBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform4fv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform4i(GL2ES2 gl, int shaderProgramId, String uniformName, int x, int y, int z, int w) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform4i(uniformLocation, x, y, z, w);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

    public static void setUniform4iv(GL2ES2 gl, int shaderProgramId, String uniformName, IntBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniform4iv(uniformLocation, 1, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }
    
    public static void setUniformMat4fv(GL2ES2 gl, int shaderProgramId, String uniformName, boolean transpose, FloatBuffer values) {
	int uniformLocation = gl.glGetUniformLocation(shaderProgramId, uniformName);
	if (uniformLocation != -1) {
	    gl.glUniformMatrix4fv(uniformLocation, 1, transpose, values);
	} else {
	    LOG.log(Level.WARNING, WARNING_TEMPLATE, uniformName);
	}
    }

}
