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

	public Screen_Initialize(Game game) {
		super(game);
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
		Assets.gridButtonMyPlanet = g.newImage("turquoisedot.png", ImageFormat.RGB565, false);
		Assets.gridButtonNotMyPlanet = g.newImage("reddot.png", ImageFormat.RGB565, false);
	
		Assets.loadingscreen = g.newImage("starrynight.png", ImageFormat.RGB565, false);
		Assets.start = g.newImage("startbutton.png", ImageFormat.RGB565, false);
		Assets.readyButton1 = g.newImage("readybutton1.png", ImageFormat.RGB565, false);
		Assets.readyButton2 = g.newImage("readybutton2.png", ImageFormat.RGB565, false);
		Assets.readyButton3 = g.newImage("readybutton3.png", ImageFormat.RGB565, false);
		Assets.readyButton4 = g.newImage("readybutton4.png", ImageFormat.RGB565, false);
		Assets.readyButton5 = g.newImage("readybutton5.png", ImageFormat.RGB565, false);
		Assets.readyButton6 = g.newImage("readybutton6.png", ImageFormat.RGB565, false);
		Assets.waitingButton1 = g.newImage("waitingbutton1.png", ImageFormat.RGB565, false);
		Assets.waitingButton2 = g.newImage("waitingbutton2.png", ImageFormat.RGB565, false);
		Assets.waitingButton3 = g.newImage("waitingbutton3.png", ImageFormat.RGB565, false);
		Assets.waitingButton4 = g.newImage("waitingbutton4.png", ImageFormat.RGB565, false);
		Assets.chooseplanet = g.newImage("chooseplanetbutton.png", ImageFormat.RGB565, false);
		Assets.space = g.newImage("space6.png", ImageFormat.RGB565, false);
		Assets.avatar_page = g.newImage("avatar_page.png", ImageFormat.RGB565, false);
		Assets.planet0 = g.newImage("blueplanet.png", ImageFormat.RGB565, false);
		Assets.planet1 = g.newImage("cheese.png", ImageFormat.RGB565, false);
		Assets.planet2 = g.newImage("cyanstripesplanet.png", ImageFormat.RGB565, false);
		Assets.planet3 = g.newImage("earth.png", ImageFormat.RGB565, false);
		Assets.planet4 = g.newImage("jupiter.png", ImageFormat.RGB565, false);
		Assets.planet5 = g.newImage("moon.png", ImageFormat.RGB565, false);
		Assets.planet6 = g.newImage("saturn.png", ImageFormat.RGB565, false);
		Assets.planet7 = g.newImage("sun.png", ImageFormat.RGB565, false);
		Assets.planet8 = g.newImage("watermelon.png", ImageFormat.RGB565, false);
		Assets.cheese130 = g.newImage("cheese130px.png", ImageFormat.RGB565, false);
		
		ArrayList<Image> blinkingReady = new ArrayList<Image>();
		blinkingReady.add(Assets.readyButton1);
		blinkingReady.add(Assets.readyButton2);
		blinkingReady.add(Assets.readyButton3);
		blinkingReady.add(Assets.readyButton4);
		blinkingReady.add(Assets.readyButton5);
		blinkingReady.add(Assets.readyButton6);
		blinkingReady.add(Assets.readyButton5);
		blinkingReady.add(Assets.readyButton4);
		blinkingReady.add(Assets.readyButton3);
		blinkingReady.add(Assets.readyButton2);
		blinkingReady.add(Assets.readyButton1);
		blinkingReady.add(Assets.readyButton1);
		Assets.blinkingReadyButton = new Objects_Animation(blinkingReady, 100, 1100);
		
		ArrayList<Image> blinkingWaiting = new ArrayList<Image>();
		blinkingWaiting.add(Assets.waitingButton1);
		blinkingWaiting.add(Assets.waitingButton1);
		blinkingWaiting.add(Assets.waitingButton2);
		blinkingWaiting.add(Assets.waitingButton2);
		blinkingWaiting.add(Assets.waitingButton3);
		blinkingWaiting.add(Assets.waitingButton3);
		blinkingWaiting.add(Assets.waitingButton4);
		blinkingWaiting.add(Assets.waitingButton4);
		Assets.blinkingWaitingButton = new Objects_Animation(blinkingWaiting, 100, 1100);

		/*Assets.backgroundanimearth0 = g.newImage("backgroundanimearth1-0.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth1 = g.newImage("backgroundanimearth1-1.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth2 = g.newImage("backgroundanimearth1-2.png", ImageFormat.RGB565, false);
		//Assets.backgroundanimearth3 = g.newImage("backgroundanimearth1-3.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth4 = g.newImage("backgroundanimearth1-4.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth5 = g.newImage("backgroundanimearth1-5.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth6 = g.newImage("backgroundanimearth1-6.png", ImageFormat.RGB565, false);
		//Assets.backgroundanimearth7 = g.newImage("backgroundanimearth1-7.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth8 = g.newImage("backgroundanimearth1-8.png", ImageFormat.RGB565, false);
		Assets.backgroundanimearth9 = g.newImage("backgroundanimearth1-9.png", ImageFormat.RGB565, false);
		
		//initialize animations
		ArrayList<Image> movingEarthImages = new ArrayList<Image>();
		movingEarthImages.add(Assets.backgroundanimearth0);
		movingEarthImages.add(Assets.backgroundanimearth1);
		movingEarthImages.add(Assets.backgroundanimearth2);
		movingEarthImages.add(Assets.backgroundanimearth3);
		movingEarthImages.add(Assets.backgroundanimearth4);
		movingEarthImages.add(Assets.backgroundanimearth5);
		movingEarthImages.add(Assets.backgroundanimearth6);
		movingEarthImages.add(Assets.backgroundanimearth7);
		movingEarthImages.add(Assets.backgroundanimearth8);
		Assets.movingEarth = new Objects_Animation(movingEarthImages); */
		
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
