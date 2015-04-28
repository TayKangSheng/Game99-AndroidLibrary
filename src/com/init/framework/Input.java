package com.init.framework;

import java.util.List;

import com.init.framework.implementation.TouchHandler;

public interface Input {
    
    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;


    }

    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);
    
    public void clearTouchEvents();

    public List<TouchEvent> getTouchEvents();
    
    public TouchHandler getTouchHandler();
    
    public void setTouchEvents(TouchEvent event);
}