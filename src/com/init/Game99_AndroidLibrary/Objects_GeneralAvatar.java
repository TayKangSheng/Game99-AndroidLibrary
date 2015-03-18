package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_GeneralAvatar {
	private int xCoor;
	private int yCoor;
	private String name;
	private Image avatarImage;
	
	public Objects_GeneralAvatar(String name, int x, int y, Image image){
		this.xCoor = x;
		this.yCoor = y;
		this.avatarImage = image;
		this.name = name;
	}
	
	public int getX(){
		return xCoor;
	}
	
	public int getY(){
		return yCoor;
	}
	
	public String toString(){
		return name;
	}
	
	public Image getImage(){
		return avatarImage;
	}
	
	public int getWidth(){
		return avatarImage.getWidth();
	}
	
	public int getHeight(){
		return avatarImage.getHeight();
	}
	
}




