package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.Random;

public class Objects_ButtonHandler {
	
	ArrayList<Objects_GridButton> grid;
	public Objects_ButtonHandler(ArrayList<Objects_GridButton> gameGrid){
		grid = gameGrid;
	}
	
	public void Click(int index, int small){
		String buttonType = grid.get(index).getType();
		if (buttonType.equals("NC")){ 
			if(grid.get(index).getInt()==small){
				Assets.socketIO.getSocket().emit("button", index);
				grid.get(index).setNormalNotClickable();
			}
			else Assets.health --;
		}
		else if (buttonType.equals("B")){
			// Set surrounding to NN
			if ( (int)(index/5)==0 ){
				if (index == 0){
					grid.get(index)  .setNormalNotClickable();
					grid.get(index+1).setNormalNotClickable();
					grid.get(index+5).setNormalNotClickable();
					grid.get(index+6).setNormalNotClickable();
				} else if (index == 4){
					grid.get(index)  .setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index+5).setButton("NN");
					grid.get(index+4).setButton("NN");
				} else{
					grid.get(index)  .setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index+4).setButton("NN");
					grid.get(index+5).setButton("NN");
					grid.get(index+6).setButton("NN");
				}
			} else if ( (int)(index/5)==7 ){
				if (index == 30){
					grid.get(index)  .setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-4).setButton("NN");
					grid.get(index-5).setButton("NN");
				} else if (index == 34){
					grid.get(index)  .setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index-5).setButton("NN");
					grid.get(index-6).setButton("NN");
				} else{
					grid.get(index)  .setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index-4).setButton("NN");
					grid.get(index-5).setButton("NN");
					grid.get(index-6).setButton("NN");
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				grid.get(index)  .setButton("NN");
				grid.get(index+1).setButton("NN");
				grid.get(index-5).setButton("NN");
				grid.get(index-4).setButton("NN");
				grid.get(index+5).setButton("NN");
				grid.get(index+6).setButton("NN");
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				grid.get(index)  .setButton("NN");
				grid.get(index-1).setButton("NN");
				grid.get(index-5).setButton("NN");
				grid.get(index-6).setButton("NN");
				grid.get(index+5).setButton("NN");
				grid.get(index+4).setButton("NN");
			} else{
				grid.get(index)  .setButton("NN");
				grid.get(index-1).setButton("NN");
				grid.get(index+1).setButton("NN");
				grid.get(index-4).setButton("NN");
				grid.get(index-5).setButton("NN");
				grid.get(index-6).setButton("NN");
				grid.get(index+4).setButton("NN");
				grid.get(index+5).setButton("NN");
				grid.get(index+6).setButton("NN");
			}
		} else if (buttonType.equals("42")){
			grid.get(index).setButton("NN");
		}
		else{
			Assets.health--;
		}
	}
}




