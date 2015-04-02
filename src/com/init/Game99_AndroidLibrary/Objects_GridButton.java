package com.init.Game99_AndroidLibrary;

import java.util.Random;

import android.util.Log;

import com.init.framework.Image;

public class Objects_GridButton{
	private static Random rand = new Random(); // Shared Random Number Generator
	int xCoor, yCoor, randomInt;
	Boolean clickable, normalClickable, 
		bomb=false, smallest=false, hint=false, lastClickable;
	Image ImageDisplay;
	String contentDisplay;
	
	Objects_GridButton(int x, int y, boolean bool){
		this.xCoor = x;
		this.yCoor = y;
		// Initialization does not have power ups.
		if (bool) setNormalClickable();
		else setNormalNotClickable();
	}
	//setters
	public void setNormalClickable(){
		normalClickable = true;
		bomb = false; smallest = false;
		randomInt = rand.nextInt(10);
		ImageDisplay = Assets.gridButtonNotMyPlanet;
		contentDisplay = String.valueOf(randomInt);
	}
	public void setNormalNotClickable(){
		normalClickable = false;
		bomb = false; smallest = false;
		randomInt = -1;
		ImageDisplay = Assets.gridButtonMyPlanet;
	}
	public void setBomb(){
		bomb = true;
		normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.bomb;
	}
	public void setSmallest(){
		lastClickable = normalClickable;
	    normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.smallest;
	}
	public void setHint(){
		lastClickable = normalClickable;
		normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.hint;
	}
	//animations
	private boolean shrinking = false, poping = false, shaking = false;
	private int shakingIndex = 0, shrinkingIndex = 0, popingIndex = 0,
			shakingframe = 0, shrinkingframe = 0, popingframe = 0
			,xchange, ychange, wchange, hchange;
	private int[] shrinkingData, popingData;
	private int[][] shakingData = {{2, 1},{-1,-2},
							{-3,0},{0,2},{1,-1},
							{-1,2},{-3,1},{2,1},
							{-1,-1},{2,2},{1,-2}};
	
	//calculating shrinking/poping data
	private int[] decreasing(int frame){
		if(frame==0) frame = 7;
		int[] result = new int[frame];
		double interval = Math.sqrt(Assets.GRIDSIZE)/frame;
		for(int i=0;i<frame;i++){
			result[i] = (int)(interval*interval*i*i - Assets.GRIDSIZE);
			Log.i("decreasing", result[i]+"");
		}
		return result;
	}
	private int[] increasing(int frame){
		if(frame==0) frame = 7;
		int[] result1 = new int[frame];
		double interval = Math.sqrt(Assets.GRIDSIZE) / frame;
		for(int i=0;i<frame;i++){
			result1[result1.length-1-i] = (int)(interval*interval*i*i - Assets.GRIDSIZE);
			Log.i("increasing", result1[result1.length-1-i]+"");
		}
		return result1;
	}
	
	public void shrink(int frames1, int frames2){
		shrinking = true;
		shrinkingframe = frames1;
		popingframe = frames2;
		shrinkingIndex = 0;
		popingIndex = 0;
		shrinkingData = increasing(shrinkingframe);
		popingData = decreasing(popingframe);
	}
	public void shake(int frames){
		shaking = true;
		shakingframe = frames;
		Assets.freeze = true;
		shakingIndex = 0;
	}
	public void pop(int frames){
		popingframe = frames;
		popingIndex = 0;
		popingData = decreasing(popingframe);
		poping = true;
	}
	public void updateFrame(){
		if(this.shaking){
			if(shakingIndex<shakingframe){
				shakingIndex ++;
				int len = shakingData.length;
				xchange = 2*shakingData[shakingIndex%len][0];
				ychange = 2*shakingData[shakingIndex%len][1];
			} else{
				shaking = false;
				Assets.freeze = false;
				xchange = 0; ychange = 0;
			}
		} else if(this.shrinking){
			if(shrinkingIndex<shrinkingframe){
				shrinkingIndex ++;
				wchange = shrinkingData[shrinkingIndex-1];
				hchange = this.wchange;
				xchange = -wchange/2; 
				ychange = -wchange/2; 
			} else{
				shrinking = false; poping = true;
			}
		} else if(this.poping){
			if(popingIndex<popingframe){
				popingIndex ++;
				wchange = popingData[popingIndex-1];
				hchange = popingData[popingIndex-1];
				xchange = -wchange/2;
				ychange = -wchange/2;
			} else{
				poping = false;
			}
		}
	}
	
	//getters
	public int getX(){ return xCoor; }
	public int getY(){ return yCoor; }
	public int getxchange(){return xchange;}
	public int getychange(){return ychange;}
	public int getw(){return wchange+Assets.GRIDSIZE;}
	public int geth(){return hchange+Assets.GRIDSIZE;}
	public boolean getlastclickable(){return lastClickable;}
	//get states
	public boolean getShrink() {return shrinking;}
	public boolean getPop() {return poping;}
	public boolean getShake(){return shaking;}
	
	public boolean getNormalClickable() {return normalClickable;}
	public boolean getBomb() {return bomb;}
	public boolean getHint() {return hint;}
	public boolean getSmallest() {return smallest;}
	public int getInt(){ return randomInt;}
	public String getRandomInt() { return String.valueOf(randomInt);}
	public Image getImageDisplay(){ return ImageDisplay;}
	public String getContentDisplay(){ return contentDisplay;}
	

}
