package com.init.Game99_AndroidLibrary;

import android.graphics.Color;
import android.util.Log;

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
	public static Double accelerateDeccelerateCurve
	(int constant, double speed, double runTime, int offset){
		Double value = constant*Math.cos(speed*(runTime+1)*Math.PI)+(constant+offset);
		//if (value > (constant*2)+(offset/2)){
			//return (double)(constant*2)+(offset/2);
		//} else{
			return value;
		//}
	}
	public static void restoreGame(){
		Assets.running = false;
		Assets.glow = false;
		Assets.ready = false;
		Assets.gameover = false; 
		Assets.Imready = false; //player is ready
	    Assets.otherQuit = false; 
		Assets.freeze = false;
	    Assets.glow = false;
	    Assets.runTime = 0;
	    Assets.bombedLoc = -1; Assets.bombLoc = -1;
	    Assets.hintLoc = -1;
	}
	public static int[] decreasing(int frame){
		if(frame==0) frame = 7;
		int[] result = new int[frame];
		double interval = Math.sqrt(Assets.GRIDSIZE)/frame;
		for(int i=0;i<frame;i++){
			result[i] = (int)(interval*interval*i*i - Assets.GRIDSIZE);
			Log.i("decreasing", result[i]+"");
		}
		return result;
	}
	
	public static int[] increasing(int frame){
		if(frame==0) frame = 7;
		int[] result1 = new int[frame];
		double interval = Math.sqrt(Assets.GRIDSIZE) / frame;
		for(int i=0;i<frame;i++){
			result1[result1.length-1-i] = (int)(interval*interval*i*i - Assets.GRIDSIZE);
			Log.i("increasing", result1[result1.length-1-i]+"");
		}
		return result1;
	}
	
	public static int[] shift(int frame){
		int[] result = new int[frame];
		for (int i=0 ; i<frame; i++){
			result[i] = (int) i*(Assets.GRIDSIZE / frame);
		}
		return result;
				
	}
}
