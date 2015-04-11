package com.init.Game99_AndroidLibrary;
import java.util.List;

import android.util.Log;

import com.init.framework.Audio;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_First extends Screen {
	private List<TouchEvent> touchEvents;
	private Graphics g;
	private Audio music;
	private NNGame game;
	public Screen_First(NNGame game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		Log.i("Screen_First", "ScreenB_First");
		
		Assets.planet0 = g.newImage("130px-blueplanet.png", ImageFormat.RGB565, false);
		Assets.planet1 = g.newImage("130-cheese.png", ImageFormat.RGB565, false);
		Assets.planet2 = g.newImage("130-cyanstripesplanet.png", ImageFormat.RGB565, false);
		Assets.planet3 = g.newImage("130-earth.png", ImageFormat.RGB565, false);
		Assets.planet4 = g.newImage("130-jupiter.png", ImageFormat.RGB565, false);
		Assets.planet5 = g.newImage("130-moon.png", ImageFormat.RGB565, false);
		Assets.planet6 = g.newImage("saturn.png", ImageFormat.RGB565, false);
		Assets.planet7 = g.newImage("130-sun.png", ImageFormat.RGB565, false);
		Assets.planet8 = g.newImage("130-watermelon.png", ImageFormat.RGB565, false);
		//earthAnimation = Assets.movingEarth;
		Assets.startScreenBGM.setVolume(0.5f);
		Assets.startScreenBGM.setLooping(true);
		if (!Assets.startScreenBGM.isPlaying())
			Assets.startScreenBGM.play();
		
	}

	@Override
	public void update(float deltaTime) {
		//Log.i("ScreenB_MainMenu", "update");
		Assets.runTime += deltaTime;
		
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				System.out.println(event.x + ", " +event.y);
				if(utils.inBounds(event,150,1045,300,127)) {
					Assets.click.play(1f);
					game.setScreen(new Screen_AvatarChooser(game));
				}
			}
		}
	}

	
    

	@Override
	public void paint(float deltaTime) {
		//Log.i("ScreenB_MainMenu", "paint");
		
		Graphics g = game.getGraphics();
		
		// Draw background Image
		g.drawImage(Assets.space, 0, 0);
		g.drawImage(Assets.start, game.getGraphics().getWidth()/2-Assets.restart.getWidth()/2-25, 
				1010);
		//g.drawImage(Assets.start, 120, 1050);
		//g.drawString(Assets.msg, 50,50,paint);
		
		//g.drawImage(earthAnimation.getImageFrame(Assets.runTime/20),0,0);
		
	}
	
	public int getColour(boolean x){
		if (x)
			return -1;
		else
			return  -16777216;
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
