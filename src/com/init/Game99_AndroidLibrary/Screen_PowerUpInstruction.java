package com.init.Game99_AndroidLibrary;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Graphics;
import com.init.framework.Screen;

public class Screen_PowerUpInstruction extends Screen {
	private NNGame game;
	private Graphics g;
	private int alpha, runTime;
	private Paint painter;

	public Screen_PowerUpInstruction(NNGame game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		painter = new Paint();
		
	}

	@Override
	public void update(float deltaTime) {
		runTime += deltaTime;
		
		if (alpha < 255){
			alpha += 5;
			painter.setAlpha(alpha);
		} 
		if (runTime > 400)
			game.setScreen(new Screen_Instruction(game));
		
	}

	@Override
	public void paint(float deltaTime) {
		g.clearScreen(Color.parseColor("#142b42"));
		g.drawImage(Assets.powerupinstruction, 0, 0, 0, 0, 800, 1280,painter);
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		while (Assets.gameScreenBGM.isPlaying()){
			Assets.gameScreenBGM.pause();
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		while (!Assets.gameScreenBGM.isPlaying()){
			Assets.gameScreenBGM.play();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "PowerUpInstruction";
	}

}
