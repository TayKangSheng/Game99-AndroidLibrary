package com.init.Game99_AndroidLibrary;


import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;



public class ScreenG_LoadingPage extends Screen {
	Paint load = new Paint();
	String loading;
	int gameHeight = game.getGraphics().getHeight();
	//This is the loading page
	public ScreenG_LoadingPage(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics graph = game.getGraphics();
		graph.clearScreen(Color.LTGRAY);
		load.setColor(Color.BLACK);
		load.setTextSize(300);
		graph.drawString("LOADING...",80 , gameHeight/2, load);
		
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Assets.runTime += deltaTime;
		game.setScreen(new Screen_Initialize(game));
		
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
