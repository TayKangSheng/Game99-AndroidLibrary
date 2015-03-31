package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_GeneralAvatar {
	private float xCoor;
	private float yCoor;
	private int imageWidth;
	private int imageHeight;
	private String name;
	private Image avatarImage;
	private boolean chosen = false;
	
	public Objects_GeneralAvatar(String name, int x, int y, Image image){
		this.xCoor = x;
		this.yCoor = y;
		this.avatarImage = image;
		this.name = name;
		this.imageHeight = image.getHeight();
		this.imageWidth = image.getWidth();
	}
	
	public int getX(){
		return (int) xCoor;
	}
	
	public int getY(){
		return (int) yCoor;
	}
	
	public String toString(){
		return name;
	}
	public void setchosen(){
		chosen = true;
	}
	public boolean getchosen(){
		return chosen;
	}
	public Image getImage(){
		return avatarImage;
	}
	
	public int getWidth(){
		return imageWidth;
	}
	
	public int getHeight(){
		return imageHeight;
	}
	
	public void addX(float add){
		xCoor += add;
	}
	
	public void addY(float add){
		yCoor += add;
	}
	
}




