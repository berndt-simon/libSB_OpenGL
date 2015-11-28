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
package libSB.openGL.mesh;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GL3ES3;
import java.util.Optional;
import libSB.openGL.objectfied.VBO;

/**
 *
 * @author Simon Berndt
 */
public final class TestVBO {

    private static final float[] VERTICES_COORDS_FLAKE = new float[]{
	0.0f, 0.0f, 0.0f, 1.0f,
	// Top
	-0.2f, 0.8f, 0.0f, 1.0f,
	0.2f, 0.8f, 0.0f, 1.0f,
	0.0f, 0.8f, 0.0f, 1.0f,
	0.0f, 1.0f, 0.0f, 1.0f,
	// Bottom
	-0.2f, -0.8f, 0.0f, 1.0f,
	0.2f, -0.8f, 0.0f, 1.0f,
	0.0f, -0.8f, 0.0f, 1.0f,
	0.0f, -1.0f, 0.0f, 1.0f,
	// Left
	-0.8f, -0.2f, 0.0f, 1.0f,
	-0.8f, 0.2f, 0.0f, 1.0f,
	-0.8f, 0.0f, 0.0f, 1.0f,
	-1.0f, 0.0f, 0.0f, 1.0f,
	// Right
	0.8f, -0.2f, 0.0f, 1.0f,
	0.8f, 0.2f, 0.0f, 1.0f,
	0.8f, 0.0f, 0.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f
    };

    private static final float[] VERTICES_COLORS_FLAKE = new float[]{
	1.0f, 1.0f, 1.0f, 1.0f,
	// Top
	0.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 0.0f, 1.0f, 1.0f,
	0.0f, 1.0f, 1.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f,
	// Bottom
	0.0f, 0.0f, 1.0f, 1.0f,
	0.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 1.0f, 1.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f,
	// Left
	0.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 0.0f, 1.0f, 1.0f,
	0.0f, 1.0f, 1.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f,
	// Right
	0.0f, 0.0f, 1.0f, 1.0f,
	0.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 1.0f, 1.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f
    };

    public static final int INSTANCE_COUNT = 4;
    
    private static final float[] INSTANCE_COORDS_FLAKE = new float[]{
	-0.4f, -0.4f, 0.0f, 0.0f,
	-0.4f, 0.4f, 0.0f, 0.0f,
	0.4f, 0.4f, 0.0f, 0.0f,
	0.4f, -0.4f, 1.0f, 0.0f
    };

    private static final int[] INDICES_FLAKE = new int[]{
	// Top
	0, 1, 3,
	0, 3, 2,
	3, 1, 4,
	3, 4, 2,
	// Bottom
	0, 5, 7,
	0, 7, 6,
	7, 5, 8,
	7, 8, 6,
	// Left
	0, 9, 11,
	0, 11, 10,
	11, 9, 12,
	11, 12, 10,
	// Right
	0, 13, 15,
	0, 15, 14,
	15, 13, 16,
	15, 16, 14
    };

    private TestVBO() {
    }

    public static Optional<VBO> createFlake(GL2ES3 gl) {
	return TestVBOImpl.create(gl, GL.GL_TRIANGLES, VERTICES_COORDS_FLAKE, VERTICES_COLORS_FLAKE, INDICES_FLAKE);
    }

    public static Optional<VBO.Instanced> createFlakeInstanced(GL3ES3 gl) {
	float[] scaledVertices = VERTICES_COORDS_FLAKE;
	for (int i = 0; i < scaledVertices.length; i++) {
	    if (i % 4 != 3) {
		scaledVertices[i] *= 0.45f;
	    }
	}
	return TestVBOInstancedImpl.create(gl, GL.GL_TRIANGLES, scaledVertices, VERTICES_COLORS_FLAKE, INDICES_FLAKE, INSTANCE_COORDS_FLAKE);
    }

}
