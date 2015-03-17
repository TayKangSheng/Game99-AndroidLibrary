package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_StartButton {
	private int xCoor;
	private int yCoor;
	private Image pressed;
	private Image notPressed;
	private Image currentDisplay;
	
	Objects_StartButton(int x, int y, Image pressed, Image notPressed){
		this.pressed = pressed;
		this.notPressed = notPressed;
		currentDisplay = this.notPressed;
		
	}
	
	public int getX(){
		return xCoor;
	}
	
	public int getY(){
		return yCoor;
	}
	
	public Image getDisplay(){
		return currentDisplay;
	}
	
	public void onClick(){
		currentDisplay = pressed;
	}
	
	public int getWidth(){
		return currentDisplay.getWidth();
	}
	
	public int getHeight(){
		return currentDisplay.getHeight();
	}
	
	
}
