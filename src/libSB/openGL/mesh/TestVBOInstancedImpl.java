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

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3ES3;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import libSB.openGL.objectfied.VBO;
import libSB.openGL.utils.StandardShaders;

public class TestVBOInstancedImpl implements VBO.Instanced {

    private static final Logger LOG = Logger.getLogger(TestVBOInstancedImpl.class.getName());

    private final int elements;
    private final int mode;

    private final int vertexArrayObjectId;
    private final int vertexCoordBufferId;
    private final int vertexColorBufferId;

    private final int indicesBufferId;
    private final int instanceCoordBufferId;

    private TestVBOInstancedImpl(int mode, int elements, int vertexArrayObjectId, int vertexCoordBufferId, int vertexColorBufferId, int indicesBufferId, int instanceCoordBufferId) {
	this.mode = mode;
	this.elements = elements;

	this.vertexArrayObjectId = vertexArrayObjectId;
	this.vertexCoordBufferId = vertexCoordBufferId;
	this.vertexColorBufferId = vertexColorBufferId;

	this.indicesBufferId = indicesBufferId;
	
	this.instanceCoordBufferId = instanceCoordBufferId;
    }

    public static Optional<VBO.Instanced> create(GL3ES3 gl, int mode, float[] verticeCoords, float[] verticeColors, int[] indices, float[] instanceCoords) {
	final int elements = indices.length;
	
	IntBuffer vertexArrayObjectId = Buffers.newDirectIntBuffer(1);
	
	IntBuffer vertexCoordBufferId = Buffers.newDirectIntBuffer(1);
	IntBuffer vertexColorBufferId = Buffers.newDirectIntBuffer(1);
	IntBuffer indicesBufferId = Buffers.newDirectIntBuffer(1);
	
	IntBuffer instanceCoordsBufferId = Buffers.newDirectIntBuffer(1);

	FloatBuffer vertexCoordsBuffer = Buffers.newDirectFloatBuffer(verticeCoords);
	FloatBuffer vertexColorsBuffer = Buffers.newDirectFloatBuffer(verticeColors);
	IntBuffer indicesBuffer = Buffers.newDirectIntBuffer(indices);
	
	FloatBuffer instanceCoordsBuffer = Buffers.newDirectFloatBuffer(instanceCoords);

	// reset Error Flag
	gl.glGetError();

	gl.glGenVertexArrays(1, vertexArrayObjectId);
	gl.glBindVertexArray(vertexArrayObjectId.get(0));

	gl.glGenBuffers(1, vertexCoordBufferId);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexCoordBufferId.get(0));
	gl.glBufferData(GL.GL_ARRAY_BUFFER, VBO.byteSizeOf(vertexCoordsBuffer), vertexCoordsBuffer, GL.GL_STATIC_DRAW);
	gl.glVertexAttribPointer(StandardShaders.LOCATION_IN_VERTEX_COORD, 4, GL.GL_FLOAT, false, 0, 0);
	gl.glEnableVertexAttribArray(StandardShaders.LOCATION_IN_VERTEX_COORD);
	
	gl.glGenBuffers(1, vertexColorBufferId);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexColorBufferId.get(0));
	gl.glBufferData(GL.GL_ARRAY_BUFFER, VBO.byteSizeOf(vertexColorsBuffer), vertexColorsBuffer, GL.GL_STATIC_DRAW);
	gl.glVertexAttribPointer(StandardShaders.LOCATION_IN_VERTEX_COLOR, 4, GL.GL_FLOAT, false, 0, 0);
	gl.glEnableVertexAttribArray(StandardShaders.LOCATION_IN_VERTEX_COLOR);
	
	gl.glGenBuffers(1, instanceCoordsBufferId);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, instanceCoordsBufferId.get(0));
	gl.glBufferData(GL.GL_ARRAY_BUFFER, VBO.byteSizeOf(instanceCoordsBuffer), instanceCoordsBuffer, GL.GL_STATIC_DRAW);
	gl.glVertexAttribPointer(StandardShaders.LOCATION_IN_INSTANCE_COORD, 4, GL.GL_FLOAT, false, 0, 0);
	gl.glVertexAttribDivisor(StandardShaders.LOCATION_IN_INSTANCE_COORD, 1);
	gl.glEnableVertexAttribArray(StandardShaders.LOCATION_IN_INSTANCE_COORD);

	gl.glGenBuffers(1, indicesBufferId);
	gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId.get(0));
	gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, VBO.byteSizeOf(indicesBuffer), indicesBuffer, GL.GL_STATIC_DRAW);

	int errorCheck = gl.glGetError();
	if (errorCheck != GL.GL_NO_ERROR) {
	    return Optional.empty();
	}
	LOG.log(Level.INFO, "Indexed VBO creation successfull");
	gl.glBindVertexArray(0);
	return Optional.of(new TestVBOInstancedImpl(mode, elements, vertexArrayObjectId.get(0), vertexCoordBufferId.get(0), vertexColorBufferId.get(0), indicesBufferId.get(0), instanceCoordsBufferId.get(0)));
    }

    @Override
    public void freeResources(GL3ES3 gl) {
	final IntBuffer idBuffer = Buffers.newDirectIntBuffer(1);

	gl.glGetError();

	gl.glDisableVertexAttribArray(StandardShaders.LOCATION_IN_VERTEX_COLOR);
	gl.glDisableVertexAttribArray(StandardShaders.LOCATION_IN_VERTEX_COORD);
	gl.glDisableVertexAttribArray(StandardShaders.LOCATION_IN_INSTANCE_COORD);

	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
	idBuffer.put(0, vertexCoordBufferId);
	gl.glDeleteBuffers(1, idBuffer);
	idBuffer.put(0, vertexColorBufferId);
	gl.glDeleteBuffers(1, idBuffer);
	idBuffer.put(0, instanceCoordBufferId);
	gl.glDeleteBuffers(1, idBuffer);

	gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, 0);
	idBuffer.put(0, indicesBufferId);
	gl.glDeleteBuffers(1, idBuffer);

	gl.glBindVertexArray(0);
	idBuffer.put(0, vertexArrayObjectId);
	gl.glDeleteVertexArrays(1, idBuffer);

	int errorCheck = gl.glGetError();
	if (errorCheck != GL.GL_NO_ERROR) {
	    LOG.log(Level.WARNING, "Could not destroy VBO(id: {0}) properly", vertexCoordBufferId);
	}
    }

    @Override
    public void use(GL3ES3 gl, int count) {
	gl.glBindVertexArray(vertexArrayObjectId);
	gl.glDrawElementsInstanced(mode, elements, GL.GL_UNSIGNED_INT, 0, count);
	gl.glBindVertexArray(0);
    }
}
