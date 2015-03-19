package com.init.Game99_AndroidLibrary;

import com.init.framework.Input.TouchEvent;

public class utils {
	public static boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x 
        		&& event.x < x + width - 1 
        		&& event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
}
