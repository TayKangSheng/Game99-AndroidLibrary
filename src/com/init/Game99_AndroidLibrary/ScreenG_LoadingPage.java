package com.init.Game99_AndroidLibrary;


import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;



public class ScreenG_LoadingPage extends Screen {
	Paint load = new Paint();
	String loading;
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
		painter.setColor(Color.BLACK);
		painter.setTextSize(300);
		graph.drawString("LOADING...",80 , gameHeight/2, painter);
		
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Assets.runTime += deltaTime;
		game.setScreen(new ScreenD_GameScreen(game));
		
	}
	
	
	
	

}
