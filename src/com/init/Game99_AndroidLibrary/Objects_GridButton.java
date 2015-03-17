package com.init.Game99_AndroidLibrary;

import java.util.Random;

import com.init.framework.Image;

public class Objects_GridButton{
	private static Random rand = new Random(); // Shared Random Number Generator
	int xCoor, yCoor;
	String buttonType;
	Boolean clickable;
	int randomInt;
	Image ImageDisplay;
	String contentDisplay;
	
	Objects_GridButton(int x, int y, boolean bool){
		this.xCoor = x;
		this.yCoor = y;
		// Initialization does not have power ups.
		if (bool){
			setNormalClickable();
		} else{
			setNormalNotClickable();
		}
	}
	
	public void setButton(String type){
		if (type.equals("NC")){
			setNormalClickable();
		} else if (type.equals("NN")){
			setNormalNotClickable();
		} else if (type.equals("B")){
			setBomb();
		} else if (type.equals("42")){
			set42();
		}
	}
	
	private void setNormalClickable(){
		buttonType = "NC";
		clickable = true;
		randomInt = rand.nextInt(10);
		ImageDisplay = Assets.gridButtonNotMyPlanet;
		contentDisplay = String.valueOf(randomInt);
	}
	
	private void setNormalNotClickable(){
		buttonType = "NN";
		clickable = false;
		randomInt = -1;
		ImageDisplay = Assets.gridButtonMyPlanet;
		contentDisplay = "";	
	}
	
	private void setBomb(){
		buttonType = "B";
		clickable = true;
		randomInt = -1;
		ImageDisplay = Assets.gridButtonNotMyPlanet;
		contentDisplay = "B";
	}
	
	private void set42(){
		buttonType = "42";
		clickable = true;
		randomInt = -1;
		ImageDisplay = Assets.gridButtonNotMyPlanet;
		contentDisplay = "42";
	}
	
	
	public boolean getClickable() {
		return clickable;
	}
	public String getType() {
		return buttonType;
	}
	
	public String getRandomInt() {
		return String.valueOf(randomInt);
	}

	public Image getImageDisplay() {
		return ImageDisplay;
	}

	public String getContentDisplay() {
		return contentDisplay;
	}
	
	public int getX(){
		return xCoor;
	}
	
	public int getY(){
		return yCoor;
	}

}
