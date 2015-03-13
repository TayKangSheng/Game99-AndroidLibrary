package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_GridSlot {
	int xCoor, yCoor;
	int buttonWidth, buttonHeight = 130;
	Interface_Button Button;
	
	
	public Objects_GridSlot(int x, int y){
		this.xCoor = x;
		this.yCoor = y;
	}
	
	public void setButton(Interface_Button button){
		this.Button = button;
	}
	
	public int getX(){
		return xCoor;
	}
	
	public int getY(){
		return yCoor;
	}
	
	public boolean getClickable(){
		return Button.getClickable();
	}
	
	public String getRandomInt(){
		return Button.getRandomInt();
	}
	
	public String getButtonType(){
		return Button.getButtonType();
	}
	
	public Image getImageDisplay(){
		return Button.getImageDisplay();
	}
	
}
