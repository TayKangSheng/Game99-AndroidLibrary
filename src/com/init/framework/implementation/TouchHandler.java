package com.init.framework.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import com.init.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public void clearTouchEvents();
    
    public List<TouchEvent> getTouchEvents();
    
	public void setTouchEvents(TouchEvent touchEvent);
}