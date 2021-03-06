package com.init.Game99_AndroidLibrary;

import android.graphics.Color;

public class Objects_Timer {

	int color = Color.WHITE;
	int textSize = 100;
	float countDown;
	public Objects_Timer(float countdown){
		countDown = countdown;
	}
	public String getValue(float runTime){
		if ((countDown-runTime)>0){
			return String.valueOf((int) (countDown-runTime)/100);
		} else{
			return "0";
		}
	}
	public int getIntValue(float runtime){
		return Integer.parseInt(getValue(runtime));
	}
	public int getColor(){
		return color;
	}

	public int getTextSize(){
		return textSize;
	}

}
