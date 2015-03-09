package com.init.Game99_AndroidLibrary;

import android.graphics.Color;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;

public class ScreenE_Results extends Screen{

	public ScreenE_Results(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.clearScreen(Color.LTGRAY);
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
