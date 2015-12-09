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

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import libSB.openGL.model.variable.Variable;
import libSB.openGL.model.variable.layout.Attribute;
import libSB.openGL.model.variable.layout.Layout;
import libSB.openGL.model.variable.usage.Usage;
import libSB.openXL.model.field.Field;
import libSB.openXL.model.struct.Struct;
import libSB.openXL.util.AbstractCodeBuilder;

/**
 *
 * @author Simon Berndt
 */
public class ShaderCodeBuilder extends AbstractCodeBuilder {

    public ShaderCodeBuilder(StringBuilder code) {
        super(code);
    }

    public void version(int versionCode) {
        code.append("#version ").append(versionCode).append(NL);
    }

    public void typeDef(Struct struct) {
        code.append("struct ").append(struct.getTypeDef()).append(" {").append(NL);

        for (int i = 0; i < struct.getFields().size(); i++) {
            final Field field = struct.getFields().get(i);
            code.append(TAB).append(field.getType().getTypeDef()).append(' ');
            final OptionalInt pointerLevel = field.getPointerLevel();
            if (pointerLevel.isPresent()) {
                for (int p = 0; p < pointerLevel.getAsInt(); p++) {
                    code.append('*');
                }
            }
            code.append(field.getIdentifier());
            final OptionalInt arraySize = field.getArraySize();
            if (arraySize.isPresent()) {
                code.append('[').append(arraySize.getAsInt()).append(']');
            }
            code.append(';').append(NL);
        }

        code.append("};").append(NL);
    }

    public void varibale(Variable variable) {
        Optional<Layout> layout = variable.getLayout();
        if (layout.isPresent()) {
            layout(layout.get());
        }
        Optional<Usage> usage = variable.getUsage();
        if (usage.isPresent()) {
            code.append(usage.get().getKeyword()).append(' ');
        }
        code.append(variable.getType().getTypeDef()).append(' ');
        final OptionalInt pointerLevel = variable.getPointerLevel();
        if (pointerLevel.isPresent()) {
            for (int p = 0; p < pointerLevel.getAsInt(); p++) {
                code.append('*');
            }
        }
        code.append(variable.getIdentifier());
        final OptionalInt arraySize = variable.getArraySize();
        if (arraySize.isPresent()) {
            code.append('[').append(arraySize.getAsInt()).append(']');
        }
        code.append(';').append(NL);
    }

    private void layout(Layout layout) {
        code.append("layout (");
        code.append(layout.getAttributes().stream().map((Attribute<? extends Object> attribute) -> {
            Optional<? extends Object> value = attribute.getValue();
            if (value.isPresent()) {
                return attribute.getKey() + " = " + Objects.toString(value.get());
            } else {
                return attribute.getKey();
            }
        }).collect(Collectors.joining(", ")));
        code.append(") ");
    }

}
