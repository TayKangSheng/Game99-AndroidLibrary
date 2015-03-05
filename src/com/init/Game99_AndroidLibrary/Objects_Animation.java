package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import com.init.framework.Image;

public class Objects_Animation {
	private ArrayList<Image> Images = new ArrayList<Image>();
	private String[] Strings;
	private int width, height;
	
	
	/**
	 *  This class aims to automates LOOP_Animation
	 * @param animationList is the list of all images to be displayed one after another.
	 */
	public Objects_Animation (ArrayList<Image> animationList){
		Images = animationList;
		width = animationList.get(0).getWidth();
		height = animationList.get(0).getHeight();
	}
	
	public Objects_Animation (String[] animationString){
		Strings = animationString;
	}
	
	/**
	 * 
	 * @param runTime = Current runTime of application
	 * @return returns the correct image based on runTime.
	 */
	public Image getImageFrame(float runTime){
		
		int numberOfImages = Images.size();
		int frame = (int) runTime%numberOfImages;
		
		return Images.get(frame);
	}
	
	public String getStringFrame(float runTime){
		int numberOfStrings = Strings.length;
		int frame = (int) runTime%numberOfStrings;
		
		return Strings[frame];
		
	}
	
	public int getWidth(){
		
		return width;
	}
	
	public int getHeight(){
		
		return height;
	}
	
}
