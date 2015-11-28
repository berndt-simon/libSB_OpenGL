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
package libSB.openGL.javafx;

/**
 *
 * @author Simon Berndt
 */
public final class FXEventConverter {

    private static final javafx.scene.input.ScrollEvent.HorizontalTextScrollUnits HORIZONTAL_SCROLL_UNIT = javafx.scene.input.ScrollEvent.HorizontalTextScrollUnits.NONE;
    private static final javafx.scene.input.ScrollEvent.VerticalTextScrollUnits VERTICAL_SCROLL_UNIT = javafx.scene.input.ScrollEvent.VerticalTextScrollUnits.NONE;

    private FXEventConverter() {
    }

    public static javafx.event.EventType<javafx.scene.input.KeyEvent> convertKeyEventType(short joglEventTypeCode) {
	switch (joglEventTypeCode) {
	    case com.jogamp.newt.event.KeyEvent.EVENT_KEY_PRESSED:
		return javafx.scene.input.KeyEvent.KEY_PRESSED;
	    case com.jogamp.newt.event.KeyEvent.EVENT_KEY_RELEASED:
		return javafx.scene.input.KeyEvent.KEY_RELEASED;
	}
	return javafx.scene.input.KeyEvent.ANY;
    }

    public static javafx.scene.input.MouseButton convertMouseButton(short joglButtonCode) {
	switch (joglButtonCode) {
	    case com.jogamp.newt.event.MouseEvent.BUTTON1:
		return javafx.scene.input.MouseButton.PRIMARY;
	    case com.jogamp.newt.event.MouseEvent.BUTTON2:
		return javafx.scene.input.MouseButton.SECONDARY;
	    case com.jogamp.newt.event.MouseEvent.BUTTON3:
		return javafx.scene.input.MouseButton.MIDDLE;
	}
	return javafx.scene.input.MouseButton.NONE;
    }

    public static javafx.event.EventType<javafx.scene.input.MouseEvent> convertMouseEventType(short joglEventTypeCode) {
	switch (joglEventTypeCode) {
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_CLICKED:
		return javafx.scene.input.MouseEvent.MOUSE_CLICKED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_DRAGGED:
		return javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_ENTERED:
		return javafx.scene.input.MouseEvent.MOUSE_ENTERED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_EXITED:
		return javafx.scene.input.MouseEvent.MOUSE_EXITED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_MOVED:
		return javafx.scene.input.MouseEvent.MOUSE_MOVED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_PRESSED:
		return javafx.scene.input.MouseEvent.MOUSE_PRESSED;
	    case com.jogamp.newt.event.MouseEvent.EVENT_MOUSE_RELEASED:
		return javafx.scene.input.MouseEvent.MOUSE_RELEASED;
	}
	return javafx.scene.input.MouseEvent.ANY;
    }

    public static javafx.scene.input.MouseEvent convertMouseEvent(com.jogamp.newt.event.MouseEvent event) {
	javafx.event.EventType<javafx.scene.input.MouseEvent> type = FXEventConverter.convertMouseEventType(event.getEventType());
	javafx.scene.input.MouseButton button = FXEventConverter.convertMouseButton(event.getButton());
	return new javafx.scene.input.MouseEvent(
		javafx.scene.input.MouseEvent.NULL_SOURCE_TARGET,
		javafx.scene.input.MouseEvent.NULL_SOURCE_TARGET,
		type,
		event.getX(),
		event.getY(),
		event.getX(),
		event.getY(),
		button,
		event.getClickCount(),
		event.isShiftDown(),
		event.isControlDown(),
		event.isAltDown(),
		event.isMetaDown(),
		event.isButtonDown(com.jogamp.newt.event.MouseEvent.BUTTON1),
		event.isButtonDown(com.jogamp.newt.event.MouseEvent.BUTTON3),
		event.isButtonDown(com.jogamp.newt.event.MouseEvent.BUTTON2),
		true,
		false,
		true,
		null);
    }

    public static javafx.scene.input.ScrollEvent convertMouseWheelEvent(com.jogamp.newt.event.MouseEvent event) {
	javafx.event.EventType<javafx.scene.input.ScrollEvent> type = javafx.scene.input.ScrollEvent.SCROLL;
	float[] scroll = event.getRotation();
	double hScroll = scroll[0];
	double vScroll = scroll[1];
	return new javafx.scene.input.ScrollEvent(
		javafx.scene.input.MouseEvent.NULL_SOURCE_TARGET,
		javafx.scene.input.MouseEvent.NULL_SOURCE_TARGET,
		type,
		event.getX(),
		event.getY(),
		event.getX(),
		event.getY(),
		event.isShiftDown(),
		event.isControlDown(),
		event.isAltDown(),
		event.isMetaDown(),
		false,
		false,
		hScroll,
		vScroll,
		hScroll,
		vScroll,
		HORIZONTAL_SCROLL_UNIT,
		hScroll,
		VERTICAL_SCROLL_UNIT,
		vScroll,
		event.getPointerCount(),
		null);
    }

}
