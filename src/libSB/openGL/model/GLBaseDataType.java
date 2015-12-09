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
package libSB.openGL.model;

import com.jogamp.common.nio.Buffers;
import libSB.openXL.model.TypeDefQualifier;

/**
 *
 * @author Simon Berndt
 */
public enum GLBaseDataType implements TypeDefQualifier {

    //BOOL("bool"), BVEC2("bvec2"), BVEC3("bvec3"), BVEC4("bvec4"),
    INT("int", Buffers.SIZEOF_INT), IVEC2("ivec2", Buffers.SIZEOF_INT * 2), IVEC3("ivec3", Buffers.SIZEOF_INT * 3), IVEC4("ivec4", Buffers.SIZEOF_INT * 4),
    UINT("uint", Buffers.SIZEOF_INT), UVEC2("uvec2", Buffers.SIZEOF_INT * 2), UVEC3("uvec3", Buffers.SIZEOF_INT * 3), UVEC4("uvec4", Buffers.SIZEOF_INT * 4),
    FLOAT("float", Buffers.SIZEOF_FLOAT), VEC2("vec2", Buffers.SIZEOF_FLOAT * 2), VEC3("vec3", Buffers.SIZEOF_FLOAT * 3), VEC4("vec4", Buffers.SIZEOF_FLOAT * 4),
    DOUBLE("double", Buffers.SIZEOF_DOUBLE), DVEC2("dvec2", Buffers.SIZEOF_DOUBLE * 2), DVEC3("dvec3", Buffers.SIZEOF_DOUBLE * 3), DVEC4("dvec4", Buffers.SIZEOF_DOUBLE * 4),
    MAT2("mat2", -1), MAT3("mat3", -1), MAT4("mat4", -1);

    private final String typeDef;
    private final int bytes;

    private GLBaseDataType(String typeDef, int bytes) {
        this.typeDef = typeDef;
        this.bytes = bytes;
    }

    @Override
    public String getTypeDef() {
        return typeDef;
    }

    @Override
    public int getSizeOf() {
        return bytes;
    }

}
