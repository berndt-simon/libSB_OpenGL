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
package libSB.openGL.model.variable;

import java.util.Objects;
import libSB.openGL.model.variable.layout.Layout;
import libSB.openGL.model.variable.usage.Usage;
import libSB.openXL.model.TypeDefQualifier;
import libSB.openXL.model.field.FieldBuilder;

/**
 *
 * @author Simon Berndt
 */
public class VariableBuilder extends FieldBuilder {

    private Usage usage;
    private Layout layout;

    VariableBuilder(TypeDefQualifier typeDefQualifier, String identifier) {
        super(typeDefQualifier, identifier);
    }
    
    public VariableBuilder usage(Usage usage) {
        Objects.requireNonNull(usage);
        this.usage = usage;
        return this;
    }
    
    public VariableBuilder layout(Layout layout) {
        Objects.requireNonNull(layout);
        this.layout = layout;
        return this;
    }

    @Override
    public Variable build() {
        return new Variable(layout, usage, dataType, pointer, identifier, array);
    }

}
