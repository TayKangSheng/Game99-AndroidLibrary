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
	private int[][] round = {
			{0,1,5,6},{0,-1,5,4},{0,1,-1,4,5,6},
			{0,1,-4,-5},{0,-1,-5,-6},{0,1,-1,-4,-5,-6},
			{0,1,-5,-4,5,6},{0,-1,-5,-6,5,4},{0,1,-1,4,-4,5-5,6,-6}
	};
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
					int[] r = round[0];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect", index, r);
					}
				} else if (index == 4){
					int[] r = round[1];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect", index, r);
					}
				} else{
					int[] r = round[2];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect", index, r);
					}
				}
			} else if ( (int)(index/5)==7 ){
				if (index == 30){
					int[] r = round[3];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect",index,  r);
					}
				} else if (index == 34){
					int[] r = round[4];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect",index,  r);
					}
				} else{
					int[] r = round[5];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
						Assets.socketIO.getSocket().emit("bombeffect", index, r);
					}
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				int[] r = round[6];
				for(int i=0;i<r.length;i++){
					grid.get(index+r[i]).setNormalNotClickable();
					Assets.socketIO.getSocket().emit("bombeffect", index, r);
				}
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				int[] r = round[7];
				for(int i=0;i<r.length;i++){
					grid.get(index+r[i]).setNormalNotClickable();
					Assets.socketIO.getSocket().emit("bombeffect", index, r);
				}
			} else{
				int[] r = round[8];
				for(int i=0;i<r.length;i++){
					if(index+r[i]>=0 && index+r[i]<=34){
					grid.get(index+r[i]).setNormalNotClickable();
					Assets.socketIO.getSocket().emit("bombeffect", index, r);
					}
					}
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




