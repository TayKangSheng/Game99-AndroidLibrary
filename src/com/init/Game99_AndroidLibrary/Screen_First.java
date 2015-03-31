package com.init.Game99_AndroidLibrary;

import java.util.List;

import android.R;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_First extends Screen {
	private List<TouchEvent> touchEvents;
	//private Objects_Animation earthAnimation;

	public Screen_First(Game game) {
		super(game);
		Log.i("Screen_First", "ScreenB_First");
		//earthAnimation = Assets.movingEarth;
	}

	@Override
	public void update(float deltaTime) {
		Log.i("ScreenB_MainMenu", "update");
		Assets.runTime += deltaTime;
		
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				System.out.println(event.x + ", " +event.y);
				if(utils.inBounds(event,120,1045,551,127)) {
					game.setScreen(new Screen_AvatarChooser(game));
					//Assets.socketIO.getSocket().emit("ready", "");
				}
			}
		}
		
		if(Assets.ready) game.setScreen(new Screen_AvatarChooser(game));
		//game.setScreen(new ScreenC_LoadingScreen(game));
	}

	
    

	@Override
	public void paint(float deltaTime) {
		Log.i("ScreenB_MainMenu", "paint");
		
		Graphics g = game.getGraphics();
		
		// Draw background Image
		g.drawImage(Assets.space, 0, 0);
		g.drawImage(Assets.start, 120, 1050);
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
