package com.init.Game99_AndroidLibrary;

import java.util.Random;

import com.init.framework.Image;

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
}
