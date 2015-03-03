package com.init.Game99_AndroidLibrary.screeens;

import java.util.ArrayList;

import com.init.Game99_AndroidLibrary.assets.Assets;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Image;
import com.init.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.bird1 = g.newImage("Bird1.png", ImageFormat.RGB565);
		Assets.bird2 = g.newImage("Bird2.png", ImageFormat.RGB565);
		Assets.bird3 = g.newImage("Bird3.png", ImageFormat.RGB565);
		
		Assets.birdAnimation = new ArrayList<Image>();
		Assets.birdAnimation.add(Assets.bird1);
		Assets.birdAnimation.add(Assets.bird2);
		Assets.birdAnimation.add(Assets.bird3);
        
        game.setScreen(new MainMenuScreen(game));
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
