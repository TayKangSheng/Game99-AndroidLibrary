package com.init.Game99_AndroidLibrary;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Audio;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_WelcomePage extends Screen {
	private Graphics g;
	private NNGame game;
	private int runTime;
	private Paint painter;
	private int alpha;
	private boolean lock = true;
	public Screen_WelcomePage(NNGame game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		painter = new Paint();
		alpha = 0;
		
		Audio music = game.getAudio();
		Assets.initlogo = g.newImage("initlogo.png", ImageFormat.RGB565, false);
		Assets.welcome = music.createSound("audio-welcome.mp3");
		
		Assets.welcome.play(0.5f);
	}

	@Override
	public void update(float deltaTime) {
		
		runTime += deltaTime;
		if (alpha<255)
			alpha += 5;

		painter.setAlpha(alpha);
		
		if (runTime > 250)
			game.setScreen(new Screen_Initialize(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);

		g.drawImage(Assets.initlogo, 0, 0, (800/2)-(Assets.initlogo.getWidth()/2), (1280/2)-(Assets.initlogo.getHeight()/2)-50, 
				Assets.initlogo.getWidth(), Assets.initlogo.getHeight(), painter);
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
		Assets.initlogo = null;
		Assets.welcome = null;

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
