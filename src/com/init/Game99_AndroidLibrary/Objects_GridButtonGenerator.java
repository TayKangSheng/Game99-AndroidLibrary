package com.init.Game99_AndroidLibrary;


public class Objects_GridButtonGenerator {
	/**
	 *  
	 *  Decides what kind of button is this suppose to be in this slot.
	 *  
	 */
	
	private static int count = 0; // The number of count till powerup appears

	public Objects_GridButtonGenerator(){
		
	}

	public Objects_GridButton getButton(boolean bool){
		// bool == click-able
		// if click-able
		//		if count == 5, Button42
		//		else, normal click-able button
		// else if not click-able
		//		normal non-click-able button
		if (bool){
			if (count==5){
				count = 0;
				return new Objects_Button42();
			} else{
				count++;
				return new Objects_ButtonNormalClickable();
			}
		} else{
			return new Objects_ButtonNormalNotClickable();
		}
	}
}
