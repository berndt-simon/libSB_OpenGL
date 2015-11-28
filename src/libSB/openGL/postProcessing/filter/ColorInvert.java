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

import java.util.OptionalInt;
import java.util.logging.Level;
import javax.media.opengl.GL2;
import libSB.openGL.utils.ShaderUtil;
import libSB.openGL.utils.UniformUtil;

/**
 *
 * @author Simon Berndt
 */
public final class ColorInvert implements PostProcessingFilter {

    private static final String NL = System.lineSeparator();
    private static final String TAB = "\t";

    private static final String UNIFORM_NAME_SAMPLER0 = "sampler0";

    private int linkedShader;
    private int linkedProgram;

    private ColorInvert() {
	this.linkedShader = 0;
	this.linkedProgram = 0;
    }

    @Override
    public void allocateFilterResources(GL2 gl) {
	OptionalInt shader = ShaderUtil.createFragmentShader(gl, createSource());
	if (shader.isPresent()) {
	    linkedShader = shader.getAsInt();
	    OptionalInt program = ShaderUtil.createSingleShaderProgram(gl, linkedShader);
	    if (program.isPresent()) {
		linkedProgram = program.getAsInt();
	    } else {
		ShaderUtil.deleteShaderProgram(gl, linkedProgram);
		linkedProgram = 0;
	    }
	}
    }

    @Override
    public void freeFilterResources(GL2 gl) {
	if (linkedProgram != 0) {
	    ShaderUtil.deleteShaderProgram(gl, linkedProgram);
	    linkedProgram = 0;
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
	    gl.glValidateProgram(linkedProgram);
	}
    }

    @Override
    public void stopProgramUse(GL2 gl) {
	gl.glUseProgram(0);
    }

    private static String createSource() {
	StringBuilder shaderSource = new StringBuilder();
	shaderSource.append("uniform sampler2D sampler0;").append(NL);
	shaderSource.append(NL);
	shaderSource.append("void main(void) {").append(NL);
	shaderSource.append(TAB).append("vec4 sample;").append(NL);
	shaderSource.append(TAB).append("sample = texture2D(sampler0, gl_TexCoord[0].st);").append(NL);
	shaderSource.append(TAB).append("gl_FragColor.rgb = 1.0 - sample.rgb;").append(NL);
	shaderSource.append(TAB).append("gl_FragColor.a = 1.0;").append(NL);
	shaderSource.append("}").append(NL);
	return shaderSource.toString();
    }

}
