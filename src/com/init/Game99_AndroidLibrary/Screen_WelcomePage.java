package com.init.Game99_AndroidLibrary;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_WelcomePage extends Screen {
	private List<TouchEvent> touchEvents;
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

		Assets.welcome.play(0.5f);
	}

	@Override
	public void update(float deltaTime) {
		runTime += deltaTime;
		if (alpha<255)
			alpha += 5;

		painter.setAlpha(alpha);
		
		if (runTime > 180){
			if (lock){
				Assets.gunshots.play(0.5f);
				lock=false;
			}
		}
		
		if (runTime > 450)
			game.setScreen(new Screen_First(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);

		g.drawImage(Assets.initlogo, 0, 0, (800/2)-(Assets.initlogo.getWidth()/2), (1280/2)-(Assets.initlogo.getHeight()/2)-50, 
				Assets.initlogo.getWidth(), Assets.initlogo.getHeight(), painter);
		
		if (runTime>190){
			g.drawImage(Assets.slogan_we, 120, 700);
		}
		
		if (runTime>240){
			g.drawImage(Assets.slogan_the, 140+Assets.slogan_we.getWidth(), 700);
		}
		
		if (runTime>290){
			g.drawImage(Assets.slogan_momentum, 165+Assets.slogan_we.getWidth()+Assets.slogan_the.getWidth(), 700);
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
