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
package libSB.openGL.model.variable.layout;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Berndt
 */
public class Layout {

    private static final Logger LOG = Logger.getLogger(Layout.class.getName());

    private final List<Attribute<? extends Object>> attributes;

    Layout(List<Attribute<? extends Object>> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute<? extends Object>> getAttributes() {
        return attributes;
    }

    public static LayoutBuilder builder() {
        return new LayoutBuilder();
    }

    public static Attribute<Integer> binding(int binding) {
        return new Attribute<>("binding", binding);
    }

    public static Attribute<Integer> offset(int offset) {
        return new Attribute<>("offset", offset);
    }

    public static Attribute<Integer> location(int location) {
        return new Attribute<>("location", location);
    }

    public static <T> Attribute<T> customAttribute(String key, T value) {
        Objects.requireNonNull(key);
        LOG.log(Level.WARNING, "Unchecked Datatype \'{0}\' with {1} Bytes - use with caution", new Object[]{key, value});
        return new Attribute<>(key, value);
    }

}
