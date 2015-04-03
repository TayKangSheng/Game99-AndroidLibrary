package com.init.Game99_AndroidLibrary;

import java.util.Random;

import android.util.Log;

import com.init.framework.Image;

public class Objects_GridButton{
	private static Random rand = new Random(); // Shared Random Number Generator
	final int xCoorFinal, yCoorFinal;
	int xCoor, yCoor, randomInt;
	Boolean clickable, normalClickable = false, 
			bomb=false, smallest=false, hint=false, lastClickable;

	Image ImageDisplay;
	String contentDisplay;

	Objects_GridButton(int x, int y, boolean bool){
		this.xCoor = x;
		this.yCoor = y;
		this.xCoorFinal = x;
		this.yCoorFinal = y;
		// Initialization does not have power ups.
		if (bool) setNormalClickable();
		else setNormalNotClickable();
	}
	//setters
	public void setNormalClickable(){
		if(!this.normalClickable){
			normalClickable = true;
			hint = false; bomb = false; smallest = false;
			randomInt = rand.nextInt(10);
			ImageDisplay = Assets.gridButtonNotMyPlanet;
			contentDisplay = String.valueOf(randomInt);
		}
	}
	public void setNormalNotClickable(){
		normalClickable = false;
		bomb = false; smallest = false; hint = false;
		randomInt = -1;
		ImageDisplay = Assets.gridButtonMyPlanet;
	}
	public void setBomb(){
		bomb = true; smallest = false; hint = false;
		normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.bomb;
	}
	public void setSmallest(){
		smallest = true; bomb = false; hint = false;
		lastClickable = normalClickable;
		normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.smallest;
	}
	public void setHint(){
		hint = true; bomb = false; smallest = false;
		lastClickable = normalClickable;
		normalClickable = false;
		randomInt = -1;
		ImageDisplay = Assets.hint;
		Assets.glow = true;
		Assets.glowRunTime = 0;
	}
	//animations
	private boolean shrinking = false, poping = false, shaking = false, bombed = false;
	private int shakingIndex = 0, shrinkingIndex = 0, popingIndex = 0, bombingIndex = 0,
			shakingframe = 0, shrinkingframe = 0, popingframe = 0,
			xchange, ychange, wchange, hchange, relativeLoc;
	private int[] shrinkingData, popingData;
	private int[][] shakingData = {{2, 1},{-1,-2},
			{-3,0},{0,2},{1,-1},
			{-1,2},{-3,1},{2,1},
			{-1,-1},{2,2},{1,-2}};

	//calculating shrinking/poping data


	public void shrink(int frames1, int frames2){
		shrinking = true;
		shrinkingframe = frames1;
		popingframe = frames2;
		shrinkingIndex = 0;
		popingIndex = 0;
		shrinkingData = utils.increasing(shrinkingframe);
		popingData = utils.decreasing(popingframe);
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
		popingData = utils.decreasing(popingframe);
		poping = true;
	}
	public void bombed(int frames1, int frames2, int relativelocation){
		bombed = true;
		shrinking = true;
		shrinkingframe = frames1;
		popingframe = frames2;
		shrinkingIndex = 0;
		popingIndex = 0;
		bombingIndex = 0;
		shrinkingData = utils.increasing(shrinkingframe);
		popingData = utils.decreasing(popingframe);
		relativeLoc = relativelocation;
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
				if (this.bombed){
					bombingIndex ++;
					/*
					 *  shift xCoor to center
					 *  if (left) {shift right}
					 *  else if (center) {no shift};
					 *  else {shift left}
					 */
					if (	relativeLoc == 4 ||
							relativeLoc == -1 ||
							relativeLoc == -6) {
						xCoor = xCoorFinal - (shrinkingData[bombingIndex-1]); 
					} else if (	relativeLoc == 0 ||
								relativeLoc == -5 ||
								relativeLoc == 5) {
						;
					} else{
						xCoor = xCoorFinal + (shrinkingData[bombingIndex-1]);
					}
					/*
					 *  shift yCoor to center
					 *  if (above) {shift down};
					 *  else if (center) {no shift};
					 *  else {shift upwards};
					 */
					if (relativeLoc == -6||
							relativeLoc == -5||
							relativeLoc == -4){
						yCoor = yCoorFinal - (shrinkingData[bombingIndex-1]);
					} else if (relativeLoc == -1||
							relativeLoc == 0||
							relativeLoc == 1){
						;
					} else{
						yCoor = yCoorFinal + (shrinkingData[bombingIndex-1]);
					}

				}
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
				if (this.bombed){
					bombed = false;
					xCoor = xCoorFinal;
					yCoor = yCoorFinal;
				}
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
	public boolean getBombed(){return bombed;}

	public boolean getNormalClickable() {return normalClickable;}
	public boolean getBomb() {return bomb;}
	public boolean getHint() {return hint;}
	public boolean getSmallest() {return smallest;}
	public int getInt(){ return randomInt;}
	public String getRandomInt() { return String.valueOf(randomInt);}
	public Image getImageDisplay(){ return ImageDisplay;}
	public String getContentDisplay(){ return contentDisplay;}

}
