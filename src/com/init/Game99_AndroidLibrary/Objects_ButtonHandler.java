package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.Random;

public class Objects_ButtonHandler {
	
	ArrayList<Objects_GridButton> grid;
	//Objects_GridButtonGenerator buttonGenerator = new Objects_GridButtonGenerator();
	static Random rand = new Random();
	
	public Objects_ButtonHandler(ArrayList<Objects_GridButton> gameGrid){
		grid = gameGrid;
	}
	
	/**
	 * 
	 * @param index of the user's click
	 * 
	 */
	public void onClick(int index){
		String buttonType = grid.get(index).getType();
		if (buttonType.equals("NC")){
			grid.get(index).setButton("NN");
		}
		else if (buttonType.equals("B")){
			// Set surrounding to NN
			if ( (int)(index/5)==0 ){
				if (index == 0){
					grid.get(index).setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index+5).setButton("NN");
					grid.get(index+6).setButton("NN");
				} else if (index == 4){
					grid.get(index).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index+5).setButton("NN");
					grid.get(index+4).setButton("NN");
				} else{
					grid.get(index).setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index+4).setButton("NN");
					grid.get(index+5).setButton("NN");
					grid.get(index+6).setButton("NN");
				}
			} else if ( (int)(index/5)==7 ){
				if (index == 30){
					grid.get(index).setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-4).setButton("NN");
					grid.get(index-5).setButton("NN");
				} else if (index == 34){
					grid.get(index).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index-5).setButton("NN");
					grid.get(index-6).setButton("NN");
				} else{
					grid.get(index).setButton("NN");
					grid.get(index+1).setButton("NN");
					grid.get(index-1).setButton("NN");
					grid.get(index-4).setButton("NN");
					grid.get(index-5).setButton("NN");
					grid.get(index-6).setButton("NN");
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				grid.get(index).setButton("NN");
				grid.get(index+1).setButton("NN");
				grid.get(index-5).setButton("NN");
				grid.get(index-4).setButton("NN");
				grid.get(index+5).setButton("NN");
				grid.get(index+6).setButton("NN");
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				grid.get(index).setButton("NN");
				grid.get(index-1).setButton("NN");
				grid.get(index-5).setButton("NN");
				grid.get(index-6).setButton("NN");
				grid.get(index+5).setButton("NN");
				grid.get(index+4).setButton("NN");
			} else{
				grid.get(index).setButton("NN");
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
	}
	
	/**
	 * 
	 * @param index of the opponent's click
	 * @param buttonType of button clicked
	 * @param newButtonType is the new type of button current is going to change into
	 * 
	 */
	public void onClick(int index, String buttonType, String newButtonType){
		if (buttonType.equals("NN")){
			grid.get(index).setButton("NC");
		} /*else if (buttonType.equals("B")){
			// Set surrounding to NN
			if ( (int)(index/5)==0 ){
				if (index == 0){
					String[] list = { "NC", "NC", "NC", "NC"};
					list[rand.nextInt(4)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index+1).setButton(list[1]);
					grid.get(index+5).setButton(list[2]);
					grid.get(index+6).setButton(list[3]);
				} else if (index == 4){
					String[] list = { "NC", "NC", "NC", "NC"};
					list[rand.nextInt(4)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index-1).setButton(list[1]);
					grid.get(index+5).setButton(list[2]);
					grid.get(index+4).setButton(list[3]);
				} else{
					String[] list =  {"NC", "NC", "NC", "NC", "NC", "NC"};
					list[rand.nextInt(6)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index+1).setButton(list[1]);
					grid.get(index-1).setButton(list[2]);
					grid.get(index+4).setButton(list[3]);
					grid.get(index+5).setButton(list[4]);
					grid.get(index+6).setButton(list[5]);
				}
			} else if ( (int)(index/5)==7 ){
				if (index == 30){
					String[] list = { "NC", "NC", "NC", "NC"};
					list[rand.nextInt(4)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index+1).setButton(list[1]);
					grid.get(index-4).setButton(list[2]);
					grid.get(index-5).setButton(list[3]);
				} else if (index == 34){
					String[] list = { "NC", "NC", "NC", "NC"};
					list[rand.nextInt(4)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index-1).setButton(list[1]);
					grid.get(index-5).setButton(list[2]);
					grid.get(index-6).setButton(list[3]);
				} else{
					String[] list =  {"NC", "NC", "NC", "NC", "NC", "NC"};
					list[rand.nextInt(6)] = newButtonType;
					grid.get(index).setButton(list[0]);
					grid.get(index+1).setButton(list[1]);
					grid.get(index-1).setButton(list[2]);
					grid.get(index-4).setButton(list[3]);
					grid.get(index-5).setButton(list[4]);
					grid.get(index-6).setButton(list[5]);
				}			
			} else if ( (index==5) || (index==10) || (index==15) || (index==20) || (index==25) ){
				String[] list =  {"NC", "NC", "NC", "NC", "NC", "NC"};
				list[rand.nextInt(6)] = newButtonType;
				grid.get(index).setButton(list[0]);
				grid.get(index+1).setButton(list[1]);
				grid.get(index-5).setButton(list[2]);
				grid.get(index-4).setButton(list[3]);
				grid.get(index+5).setButton(list[4]);
				grid.get(index+6).setButton(list[5]);
			} else if ( (index==9) || (index==14) || (index==19) || (index==24) || (index==29) ){
				String[] list =  {"NC", "NC", "NC", "NC", "NC", "NC"};
				list[rand.nextInt(6)] = newButtonType;
				grid.get(index).setButton(list[0]);
				grid.get(index-1).setButton(list[1]);
				grid.get(index-5).setButton(list[2]);
				grid.get(index-6).setButton(list[3]);
				grid.get(index+5).setButton(list[4]);
				grid.get(index+4).setButton(list[5]);
			} else{
				String[] list =  {"NC", "NC", "NC", "NC", "NC", "NC", "NC", "NC", "NC"};
				list[rand.nextInt(9)] = newButtonType;
				grid.get(index).setButton(list[0]);
				grid.get(index-1).setButton(list[1]);
				grid.get(index+1).setButton(list[2]);
				grid.get(index-4).setButton(list[3]);
				grid.get(index-5).setButton(list[5]);
				grid.get(index-6).setButton(list[6]);
				grid.get(index+4).setButton(list[7]);
				grid.get(index+5).setButton(list[8]);
				grid.get(index+6).setButton(list[9]);
			}
			
		} else if (buttonType.equals("42")){
			grid.get(index).setButton("NC");
		}*/
	}
}




