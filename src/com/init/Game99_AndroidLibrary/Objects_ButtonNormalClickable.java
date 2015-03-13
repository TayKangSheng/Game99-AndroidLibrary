package com.init.Game99_AndroidLibrary;

import java.util.Random;

import com.init.framework.Image;

public class Objects_ButtonNormalClickable implements Interface_Button{
	private static Random rand = new Random(); // Shared Random Number Generator

	@Override
	public boolean getClickable() {
		return true;
	}

	@Override
	public String getButtonType() {
		return "NC";
	}

	@Override
	public String getRandomInt() {
		return String.valueOf(rand.nextInt(10));
	}

	@Override
	public Image getImageDisplay() {
		return Assets.gridButtonNotMyPlanet;
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContentDisplay() {
		return String.valueOf(rand.nextInt(10));
	}
	
}
