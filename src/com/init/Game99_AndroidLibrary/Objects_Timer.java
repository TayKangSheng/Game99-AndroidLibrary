package com.init.Game99_AndroidLibrary;

import android.graphics.Color;

public class Objects_Timer {

	int color = Color.BLACK;
	int textSize = 140;
	float countDown = 3000;

	public String getValue(float runTime){
		if ((countDown-runTime)>0){
			return String.valueOf((int) (countDown-runTime)/100);
		} else{
			return "0";
		}
	}

	public int getColor(){
		return color;
	}

	public int getTextSize(){
		return textSize;
	}

}
