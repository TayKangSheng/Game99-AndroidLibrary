package com.init.Game99_AndroidLibrary;

import android.graphics.Color;

import com.init.framework.Input.TouchEvent;

public class utils {
	public static boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x 
				&& event.x < x + width - 1 
				&& event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param constant: from 0 to (y*2) value
	 * @param speed: speed of glowing. eg. 0.012 is a visible speed of glow
	 * @param runTime: runTime of the game
	 * @return
	 */
	public static Double accelerateDeccelerateCurve(int constant, double speed, double runTime, int offset){
		Double value = constant*Math.cos(speed*(runTime+1)*Math.PI)+(constant+offset);
		if (value > (constant*2)+(offset/2)){
			return (double)(constant*2)+(offset/2);
		} else{
			return value;
		}
	}
}
