package com.init.Game99_AndroidLibrary;

import java.util.Random;

import android.graphics.Color;

import com.init.framework.Image;

public class Objects_GridButton {
	
	private int color; // we can change this to an image when we have the graphics up
	private Image currentDisplay; // Your image or not your image.
	private int xCoor, yCoor; // x and y coordinate of the top left corner
	private String randomInt; // Integer displayed of the button
	private int buttonWidth = 130; // change this width to graphic's width
	private int buttonHeight = 130; // change this height to graphic's height
	private static Random rand = new Random(); // Shared Random Number Generator
	
	Objects_GridButton(int x, int y, boolean Color){
		xCoor = x;
		yCoor = y;
		setColor(Color);
	}
	
	public void setColor(boolean color){
		if (color == true){
			this.color = Color.BLUE;
			this.currentDisplay = Assets.gridButtonMyPlanet;
			randomInt = String.valueOf(rand.nextInt(10));
			
		}
		else{
			this.color = Color.RED;
			this.currentDisplay = Assets.gridButtonNotMyPlanet;
			randomInt = null;
		}
	}
	
	public Image getImage(){
		return currentDisplay;
	}
	
	public int getColor(){
		return color;
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
