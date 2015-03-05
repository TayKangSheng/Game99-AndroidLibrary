package com.init.Game99_AndroidLibrary;

import android.graphics.Paint;
import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;

public class ScreenC_LoadingScreen extends Screen {
	Paint loading = new Paint();
	String loadingCurrent;

	public ScreenC_LoadingScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		Log.i("NNGame", "MainMenuScreen");

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Assets.runTime += deltaTime;
		loadingCurrent = Assets.loadingStringAnimation.getStringFrame(Assets.runTime/20);
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.clearScreen(-12303292);
		loading.setColor(-16711936);
		loading.setTextSize(150);
		g.drawString(loadingCurrent, game.getGraphics().getWidth()/6, game.getGraphics().getHeight()/2, loading);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
        game.setScreen(new ScreenA_Initialisation(game));
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
