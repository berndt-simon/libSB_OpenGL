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

import com.jogamp.common.nio.Buffers;

/**
 *
 * @author Simon Berndt
 */
public final class StandardShaders {

    private static final String NL = System.lineSeparator();
    private static final String TAB = "\t";
    
    public static final int LOCATION_IN_VERTEX_COORD = 0;
    public static final int LOCATION_IN_VERTEX_COLOR = 1;
    public static final int LOCATION_IN_INSTANCE_COORD = 2;
    
    public static final int ELEMENTS_PER_VERTEX_COORD = 4;
    public static final int ELEMENTS_PER_VERTEX_COLOR = 4;
    public static final int ELEMENTS_PER_INSTANCE_COORD = 4;
    
    public static final int BYTES_PER_VERTEX_COORD = Buffers.SIZEOF_FLOAT * ELEMENTS_PER_VERTEX_COORD;
    public static final int BYTES_PER_VERTEX_COLOR = Buffers.SIZEOF_FLOAT * ELEMENTS_PER_VERTEX_COLOR;
    public static final int BYTES_PER_INSTANCE_COORD = Buffers.SIZEOF_FLOAT * ELEMENTS_PER_INSTANCE_COORD;
        
    private static final String IN_VERTEX_COORD = "inVertCoord";
    private static final String IN_VERTEX_COLOR = "inVertColor";
    private static final String IN_INSTANCE_COORD = "inInstCoord";
        
    private static final String EX_COLOR = "exColor";
    
    public static final int LOCATION_OUT_FRAG_COLOR = 0;
    private static final String OUT_COLOR = "outColor";
    

    private StandardShaders() {
    }
    
    public static String createMinimalInstancedGL3ES3VertexShader() {
	StringBuilder shader = new StringBuilder("#version 330").append(NL);
	shader.append(NL);
	shader.append("layout(location = ").append(LOCATION_IN_VERTEX_COORD).append(") in vec4 ").append(IN_VERTEX_COORD).append(";").append(NL);
	shader.append("layout(location = ").append(LOCATION_IN_VERTEX_COLOR).append(") in vec4 ").append(IN_VERTEX_COLOR).append(";").append(NL);
	shader.append("layout(location = ").append(LOCATION_IN_INSTANCE_COORD).append(") in vec4 ").append(IN_INSTANCE_COORD).append(";").append(NL);
	shader.append("out vec4 ").append(EX_COLOR).append(";").append(NL);
	shader.append(NL);
	shader.append("void main(void) {").append(NL);
	shader.append(TAB).append("gl_Position = ").append(IN_VERTEX_COORD).append(" + ").append(IN_INSTANCE_COORD).append(";").append(NL);
	shader.append(TAB).append(EX_COLOR).append(" = ").append(IN_VERTEX_COLOR).append(";").append(NL);
	shader.append("}").append(NL);
	return shader.toString();
    }

    public static String createMinimalGL3ES3VertexShader() {
	StringBuilder shader = new StringBuilder("#version 330").append(NL);
	shader.append(NL);
	shader.append("layout(location = ").append(LOCATION_IN_VERTEX_COORD).append(") in vec4 ").append(IN_VERTEX_COORD).append(";").append(NL);
	shader.append("layout(location = ").append(LOCATION_IN_VERTEX_COLOR).append(") in vec4 ").append(IN_VERTEX_COLOR).append(";").append(NL);
	shader.append("out vec4 ").append(EX_COLOR).append(";").append(NL);
	shader.append(NL);
	shader.append("void main(void) {").append(NL);
	shader.append(TAB).append("gl_Position = ").append(IN_VERTEX_COORD).append(";").append(NL);
	shader.append(TAB).append(EX_COLOR).append(" = ").append(IN_VERTEX_COLOR).append(";").append(NL);
	shader.append("}").append(NL);
	return shader.toString();
    }

    public static String createMinimalGL3ES3FragmentShader() {
	StringBuilder shader = new StringBuilder("#version 330").append(NL);
	shader.append(NL);
	shader.append("in vec4 ").append(EX_COLOR).append(";").append(NL);
	shader.append("layout (location = ").append(LOCATION_OUT_FRAG_COLOR).append(")out vec4 ").append(OUT_COLOR).append(";").append(NL);
	shader.append(NL);
	shader.append("void main(void) {").append(NL);
	shader.append(TAB).append(OUT_COLOR).append(" = ").append(EX_COLOR).append(";").append(NL);
	shader.append("}").append(NL);
	return shader.toString();
    }

}
