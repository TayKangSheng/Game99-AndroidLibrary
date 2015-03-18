package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_ButtonBomb implements Interface_Button{

	@Override
	public boolean getClickable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getButtonType() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public String getRandomInt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImageDisplay() {
		// TODO Auto-generated method stub
		return Assets.gridButtonNotMyPlanet;
	}

	@Override
	public String getContentDisplay() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
