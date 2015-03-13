package com.init.Game99_AndroidLibrary;

import java.util.Random;

import com.init.framework.Image;

<<<<<<< HEAD
public class Objects_GridButton {

	private boolean clickable;
	private Image currentDisplay; // Your image or not your image.
	private int xCoor, yCoor; // x and y coordinate of the top left corner
	private String randomInt; // Integer displayed of the button
	private int buttonWidth = 130; // change this width to graphic's width
	private int buttonHeight = 130; // change this height to graphic's height
	private String buttonType; // Normal, 42
	private static Random rand = new Random(); // Shared Random Number Generator
	private static int count = 0; // The number of count till powerup appears

	Objects_GridButton(int x, int y, boolean type){
		xCoor = x;
		yCoor = y;
		setImage(type);
	}

	public void setImage(boolean color){
		//color==true: grid changes to other's color: number would appear.
		if (color){
			clickable = true;
			this.currentDisplay = Assets.gridButtonNotMyPlanet;
			if (count == 5){
				buttonType = "42";
				randomInt = null;
				//count = 0;
			} else{
				buttonType = "N";
				randomInt = rand.nextInt(10)+"";
				//count++;
			}
		}else {
			clickable = false;
			buttonType = "F";
			this.currentDisplay = Assets.gridButtonMyPlanet;
			randomInt = null;
		}
	}

	public boolean getClickable(){
		return clickable;
	}

	public String getType(){
		return buttonType;
	}

	public Image getImage(){
		return currentDisplay;
	}

	public int getX(){
		return xCoor;
	}

	public int getY(){
		return yCoor;
	}

	public String getRandomInt(){
		return randomInt;
	}

	public int getWidth(){
		return buttonWidth;
	}

	public int getHeight(){
		return buttonHeight;
	}
=======
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

	public String getButtonType() {
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
	 
	 
>>>>>>> origin/13-03-2015-kang
}
