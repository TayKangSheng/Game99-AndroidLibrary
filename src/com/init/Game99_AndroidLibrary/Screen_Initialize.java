package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import android.R;
import android.util.Log;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Image;
import com.init.framework.Graphics.ImageFormat;

public class Screen_Initialize extends Screen {
	private NNGame game;
	public Screen_Initialize(NNGame game) {
		super(game);
		this.game = game;
		Log.i("Screen_Initialize", "A_Initialisation_screen");
	}

	/**
	 *  Initialize all assets in Assets.game here.
	 *  After all assets are initialized, screen is set to main menu. 
	 */
	@Override
	public void update(float deltaTime) {
		Log.i("ScreenA_Initialisation", "update");
		//runtime and socketio;
		Assets.runTime=0;
		Assets.socketIO = new SocketIO();
		//loads images;
		Graphics g = game.getGraphics();
		
		Assets.bomb = g.newImage("bomb.png", ImageFormat.RGB565, false);
		Assets.smallest = g.newImage("bomb.png", ImageFormat.RGB565, false);
		Assets.loadingscreen = g.newImage("starrynight.png", ImageFormat.RGB565, false);
		Assets.start = g.newImage("startbutton.png", ImageFormat.RGB565, false);
		Assets.readyButton = g.newImage("readybutton1.png", ImageFormat.RGB565, false);
		Assets.waitingButton = g.newImage("waitingbutton1.png", ImageFormat.RGB565, false);
		Assets.chooseplanet = g.newImage("chooseplanetbutton.png", ImageFormat.RGB565, false);
		Assets.space = g.newImage("space6.png", ImageFormat.RGB565, false);
		Assets.avatar_page = g.newImage("starrynight.png", ImageFormat.RGB565, false);
		
		
		//let's go to the next screen;
        game.setScreen(new Screen_First(game));
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("ScreenA_Initialisation", "paint");
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
