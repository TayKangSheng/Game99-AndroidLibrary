package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Image;
import com.init.framework.Screen;
import com.init.framework.Graphics.ImageFormat;

public class ScreenA_Initialisation extends Screen {
	

	public ScreenA_Initialisation(Game game) {
		super(game);
		Log.i("NNGame", "A_Initialisation_screen");
	}

	/**
	 *  Initialize all assets in game here.
	 *  After all assets are initialized, screen is set to main menu. 
	 */
	@Override
	public void update(float deltaTime) {
		
		// Initialization of ALL game assets.
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.bird1 = g.newImage("Bird1.png", ImageFormat.RGB565);
		Assets.bird2 = g.newImage("Bird2.png", ImageFormat.RGB565);
		Assets.bird3 = g.newImage("Bird3.png", ImageFormat.RGB565);
		
		ArrayList<Image> birdAnimationImages = new ArrayList<Image>();
		birdAnimationImages.add(Assets.bird1);
		birdAnimationImages.add(Assets.bird2);
		birdAnimationImages.add(Assets.bird3);
		
		Assets.birdAnimation = new Objects_Animation(birdAnimationImages);
		
		// Set GameScreen to ScreenB_MainMenu.
        game.setScreen(new ScreenB_MainMenu(game));
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}

}
