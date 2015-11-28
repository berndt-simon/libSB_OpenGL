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
import com.jogamp.opengl.GL2ES2;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;
import static com.jogamp.opengl.GL2ES2.GL_SHADER_SOURCE_LENGTH;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Simon Berndt
 */
public final class ShaderError {

    private final String errorMessage;
    private final String shaderSource;

    private ShaderError(String message, String shaderSource) {
        this.errorMessage = message;
        this.shaderSource = shaderSource;
    }

    public static ShaderError create(GL2ES2 gl, int shaderObjectId) {
        IntBuffer logLengthHolder = Buffers.newDirectIntBuffer(1);
        IntBuffer sourceLengthHolder = Buffers.newDirectIntBuffer(1);
        
        gl.glGetShaderiv(shaderObjectId, GL_INFO_LOG_LENGTH, logLengthHolder);
        gl.glGetShaderiv(shaderObjectId, GL_SHADER_SOURCE_LENGTH, sourceLengthHolder);
        
        ByteBuffer infoLog = Buffers.newDirectByteBuffer(logLengthHolder.get(0));
        ByteBuffer sourceCode = Buffers.newDirectByteBuffer(sourceLengthHolder.get(0));
       
        gl.glGetShaderInfoLog(shaderObjectId, infoLog.limit(), logLengthHolder, infoLog);
        gl.glGetShaderInfoLog(shaderObjectId, sourceCode.limit(), sourceLengthHolder, sourceCode);
        
        String errorMessage = StandardCharsets.US_ASCII.decode(infoLog).toString();
        String shaderSource = StandardCharsets.US_ASCII.decode(sourceCode).toString();
        return new ShaderError(errorMessage, shaderSource);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getShaderSource() {
        return shaderSource;
    }

    @Override
    public String toString() {
        return getErrorMessage();
    }
    
}
