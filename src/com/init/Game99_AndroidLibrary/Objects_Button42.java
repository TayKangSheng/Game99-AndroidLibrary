package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;

public class Objects_Button42 implements Interface_Button {

	@Override
	public boolean getClickable() {
		return true;
	}

	@Override
	public String getButtonType() {
		return "42";
	}

	@Override
	public String getRandomInt() {
		return null;
	}

	@Override
	public Image getImageDisplay() {
		return Assets.gridButtonNotMyPlanet;
	}

	@Override
	public void onClick() {
		
	}

	@Override
	public String getContentDisplay() {
		return "42";
	}

}
