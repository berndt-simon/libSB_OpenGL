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

import java.util.Optional;
import libSB.openGL.model.variable.layout.Layout;
import libSB.openGL.model.variable.usage.Usage;
import libSB.openXL.model.TypeDefQualifier;
import libSB.openXL.model.field.Field;

/**
 *
 * @author Simon Berndt
 */
public class Variable extends Field {
    
    private final Layout layout;
    private final Usage usage;

    Variable(Layout layout, Usage usage, TypeDefQualifier type, int pointerLevel, String identifier, int arraySize) {
        super(type, pointerLevel, identifier, arraySize);
        this.layout = layout;
        this.usage = usage;
    }
    
    public Optional<Layout> getLayout() {
        return Optional.ofNullable(layout);
    }
    
    public Optional<Usage> getUsage() {
        return Optional.ofNullable(usage);
    }
    
    public static VariableBuilder builder(TypeDefQualifier typeDefQualifier, String identifier) {
        return new VariableBuilder(typeDefQualifier, identifier);
    }
    
    
}
