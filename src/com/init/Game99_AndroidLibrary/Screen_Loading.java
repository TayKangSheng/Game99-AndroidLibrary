package com.init.Game99_AndroidLibrary;

import android.graphics.Paint;
import android.util.Log;

import com.init.framework.Graphics;
import com.init.framework.Screen;

public class Screen_Loading extends Screen {
	NNGame game;
	Paint loading = new Paint();
	String loadingCurrent;
	float volume;

	public Screen_Loading(NNGame game) {
		super(game);
		this.game = game;
		// TODO Auto-generated constructor stub
		Log.i("ScreenC_LoadingScreen", "ScreenC_LoadingScreen");
		volume = 1f;
	}

	@Override
	public void update(float deltaTime) {
		Log.i("ScreenC_LoadingScreen", "update");
		Assets.runTime += deltaTime;
		
		volume -= 0.1;
		Assets.gameScreenBGM.setVolume(volume);
		game.setScreen(new Screen_Game(game));
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("ScreenC_LoadingScreen", "paint");
		
		Graphics g = game.getGraphics();
		g.clearScreen(-12303292);
		loading.setColor(2899536);
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
        game.setScreen(new Screen_Initialize(game));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		while (Assets.gameScreenBGM.isPlaying()){
			Assets.gameScreenBGM.stop();
		}
		Assets.gameScreenBGM.dispose();
		Assets.gameScreenBGM = null;

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Loading";
	}

}
