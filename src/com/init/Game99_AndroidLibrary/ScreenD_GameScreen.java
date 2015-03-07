package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;

public class ScreenD_GameScreen extends Screen{

	private int gameWidth = game.getGraphics().getWidth();
	Paint paintClock = new Paint();
	float runTime = 0;
	boolean running = true;
	ArrayList<Objects_GridButton> gameGrid = new ArrayList<Objects_GridButton>();
	Objects_Timer clock;
	int health;

	public ScreenD_GameScreen(Game game) {
		super(game);
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");
		
		// Initialization of the Game Grid, Game Buttons and Timer, Health bar.
		for (int i=0 ; i<35; i++){
			Objects_GridButton temp = new Objects_GridButton(75+(i%5)*130, 150+((int)(i/5))*130, Assets.interGalaticaMapVector[i]);
			gameGrid.add(temp);
		}
		// Initialization of clock
		clock = new Objects_Timer();
		// Initialization of health
		health = 5;

	}

	@Override
	public void update(float deltaTime) {
		Log.i("ScreenD_GameScreen", "update");
		runTime += deltaTime;
		// receive data
		// check clock
		// send data
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("ScreenD_GameScreen", "paint");

		Graphics g = game.getGraphics();
		// White Background for the entire screen
		g.clearScreen(Color.WHITE);
		// Yellow Background for health and timer
		g.drawRect(5, 5, gameWidth-10, 140, Color.YELLOW);
		// Magenta background for power ups
		g.drawRect(5, 1065, gameWidth-10, 210, Color.MAGENTA);
		// Paint timer
		paintClock.setColor(clock.getColor());
		paintClock.setTextSize(clock.getTextSize());
		g.drawString(clock.getValue(runTime), (gameWidth/2)-80, 120, paintClock);
		// Paint health
		for (int i=0 ; i<health ; i++){
			g.drawRect(50+(i*50), 50, 50, 50, Color.BLACK);
		}
		// Paint Power ups
		for (int i=0 ; i<3 ; i++){
			g.drawRect(90+(i*210), 1070, 200, 200, Color.GRAY);
		}
		// Draw Grids
		for (int i=0 ; i<gameGrid.size() ; i++){
			Objects_GridButton temp = gameGrid.get(i);
			g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getColor());
		}
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
