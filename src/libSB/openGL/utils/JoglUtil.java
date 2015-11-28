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

import com.jogamp.opengl.*;
import java.util.Optional;

/**
 *
 * @author Simon Berndt
 */
public final class JoglUtil {

    private JoglUtil() {
    }
    
    public static Optional<GL2> getGL2(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL2());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2ES1> getGL2ES1(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL2ES1());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2ES2> getGL2ES2(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL2ES2());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2ES3> getGL2ES3(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL2ES3());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2GL3> getGL3(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL2GL3());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
        
    public static Optional<GL3ES3> getGL3ES3(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL3ES3());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2> getGL3bc(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL3bc());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL4> getGL4(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL4());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL4ES3> getGL4ES3(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL4ES3());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GL2> getGL4bc(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGL4bc());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    public static Optional<GLES1> getGLES1(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGLES1());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GLES2> getGLES2(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGLES2());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }
    
    public static Optional<GLES3> getGLES3(GLAutoDrawable glAutoDrawable) {
	try {
	    return Optional.of(glAutoDrawable.getGL().getGLES3());
	} catch (GLException e) {
	    return Optional.empty();
	}
    }

}
