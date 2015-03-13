package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_ButtonNormalNotClickable implements Interface_Button{

	@Override
	public boolean getClickable() {
		return false;
	}

	@Override
	public String getButtonType() {
		return "NN";
	}

	@Override
	public String getRandomInt() {
		return null;
	}

	@Override
	public Image getImageDisplay() {
		return Assets.gridButtonMyPlanet;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContentDisplay() {
		// TODO Auto-generated method stub
		return "";
	}

}
