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
			{0,1,-5,-4,5,6},{0,-1,-5,-6,5,4},{0,1,-1,4,-4,5, -5,6,-6}
	};
	public void Click(int index, int small){
		if(small==Assets.BOMBED){
			if (index<=4 && index>=0){
				if (index == 0){
					int[] r = round[0];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				} else if (index == 4){
					int[] r = round[1];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				} else{
					int[] r = round[2];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				}
			} else if (index<=34 && index>=30){
				if (index == 30){
					int[] r = round[3];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				} else if (index == 34){
					int[] r = round[4];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				} else{
					int[] r = round[5];
					for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				int[] r = round[6];
				for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				int[] r = round[7];
				for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
			} else{
				int[] r = round[8];
				for(int i=0;i<r.length;i++) grid.get(index+r[i]).setNormalClickable();
		}
		}
		//if clicked on the right one
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
			Assets.socketIO.getSocket().emit("bomb", index);
			if (index<=4 && index>=0){
				if (index == 0){
					int[] r = round[0];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				} else if (index == 4){
					int[] r = round[1];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				} else{
					int[] r = round[2];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				}
			} else if (index<=34 && index>=30){
				if (index == 30){
					int[] r = round[3];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				} else if (index == 34){
					int[] r = round[4];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				} else{
					int[] r = round[5];
					for(int i=0;i<r.length;i++){
						grid.get(index+r[i]).setNormalNotClickable();
					}
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				int[] r = round[6];
				for(int i=0;i<r.length;i++){
					grid.get(index+r[i]).setNormalNotClickable();
				}
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				int[] r = round[7];
				for(int i=0;i<r.length;i++){
					grid.get(index+r[i]).setNormalNotClickable();
				}
			} else{
				int[] r = round[8];
				for(int i=0;i<r.length;i++){
					grid.get(index+r[i]).setNormalNotClickable();
				}
			}
		} else if (grid.get(index).getSmallest()){
			grid.get(index).setNormalNotClickable();
			if(grid.get(index).getlastclickable()) 
				Assets.socketIO.getSocket().emit("button", index);
			int tmp = 0;
			for(Objects_GridButton btn: grid){
				if(btn.getInt()==small){
					btn.setNormalNotClickable();
					Assets.socketIO.getSocket().emit("button", tmp);
					tmp++;
				}
			}
		} else if(grid.get(index).getHint()){
			grid.get(index).setNormalNotClickable();
			if(grid.get(index).getlastclickable()) 
				Assets.socketIO.getSocket().emit("button", index);
		}
		else{
			grid.get(index).shake(14);
			v.vibrate(500);
		}

	}}




