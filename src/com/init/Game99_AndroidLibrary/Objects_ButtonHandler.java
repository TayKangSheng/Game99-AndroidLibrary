package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.Random;

import com.init.framework.Game;

import android.os.Vibrator;
public class Objects_ButtonHandler {
	private NNGame game;
	Vibrator v;
	ArrayList<Objects_GridButton> grid;
	public Objects_ButtonHandler(ArrayList<Objects_GridButton> gameGrid, NNGame game){
		grid = gameGrid;
		this.game = game;
		v = (Vibrator) game.getSystemService(game.VIBRATOR_SERVICE);
	}
	
	public void Click(int index, int small){
		if (grid.get(index).getNormalClickable()){ 
			if(grid.get(index).getInt() == small){
				Assets.socketIO.getSocket().emit("button", index);
				grid.get(index).setNormalNotClickable();
				grid.get(index).shrink(7, 7);
			}else{
				grid.get(index).shake(14);
				v.vibrate(500);
			}
		}
		else if (grid.get(index).getBomb()){
			if ( (int)(index/5)==0 ){
				if (index == 0){
					grid.get(index)  .setNormalNotClickable();
					grid.get(index+1).setNormalNotClickable();
					grid.get(index+5).setNormalNotClickable();
					grid.get(index+6).setNormalNotClickable();
				} else if (index == 4){
					grid.get(index)  .setNormalNotClickable();
					grid.get(index-1).setNormalNotClickable();
					grid.get(index+5).setNormalNotClickable();
					grid.get(index+4).setNormalNotClickable();
				} else{
					grid.get(index)  .setNormalNotClickable();
					grid.get(index+1).setNormalNotClickable();
					grid.get(index-1).setNormalNotClickable();
					grid.get(index+4).setNormalNotClickable();
					grid.get(index+5).setNormalNotClickable();
					grid.get(index+6).setNormalNotClickable();
				}
			} else if ( (int)(index/5)==7 ){
				if (index == 30){
					grid.get(index)  .setNormalNotClickable();
					grid.get(index+1).setNormalNotClickable();
					grid.get(index-4).setNormalNotClickable();
					grid.get(index-5).setNormalNotClickable();
				} else if (index == 34){
					grid.get(index)  .setNormalNotClickable();
					grid.get(index-1).setNormalNotClickable();
					grid.get(index-5).setNormalNotClickable();
					grid.get(index-6).setNormalNotClickable();
				} else{
					grid.get(index)  .setNormalNotClickable();
					grid.get(index+1).setNormalNotClickable();
					grid.get(index-1).setNormalNotClickable();
					grid.get(index-4).setNormalNotClickable();
					grid.get(index-5).setNormalNotClickable();
					grid.get(index-6).setNormalNotClickable();
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				grid.get(index)  .setNormalNotClickable();
				grid.get(index+1).setNormalNotClickable();
				grid.get(index-5).setNormalNotClickable();
				grid.get(index-4).setNormalNotClickable();
				grid.get(index+5).setNormalNotClickable();
				grid.get(index+6).setNormalNotClickable();
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				grid.get(index)  .setNormalNotClickable();
				grid.get(index-1).setNormalNotClickable();
				grid.get(index-5).setNormalNotClickable();
				grid.get(index-6).setNormalNotClickable();
				grid.get(index+5).setNormalNotClickable();
				grid.get(index+4).setNormalNotClickable();
			} else{
				grid.get(index)  .setNormalNotClickable();
				grid.get(index-1).setNormalNotClickable();
				grid.get(index+1).setNormalNotClickable();
				grid.get(index-4).setNormalNotClickable();
				grid.get(index-5).setNormalNotClickable();
				grid.get(index-6).setNormalNotClickable();
				grid.get(index+4).setNormalNotClickable();
				grid.get(index+5).setNormalNotClickable();
				grid.get(index+6).setNormalNotClickable();
			}
		} else if (grid.get(index).getSmallest()){
			grid.get(index).setNormalNotClickable();
		}
		else{
			grid.get(index).shake(14);
			v.vibrate(500);
		}
	}
}




