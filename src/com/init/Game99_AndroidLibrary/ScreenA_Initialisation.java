package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import android.util.Log;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Image;
import com.init.framework.Graphics.ImageFormat;

public class ScreenA_Initialisation extends Screen {

	public ScreenA_Initialisation(Game game) {
		super(game);
		Log.i("ScreenA_Initialisation", "A_Initialisation_screen");
	}

	/**
	 *  Initialize all assets in Assets.game here.
	 *  After all assets are initialized, screen is set to main menu. 
	 */
	@Override
	public void update(float deltaTime) {
		Log.i("ScreenA_Initialisation", "update");
		
		// Initialization of ALL game assets.
		Assets.runTime=0;
		
		Graphics g = game.getGraphics();
		Assets.start = g.newImage("startbutton.png", ImageFormat.RGB565);
		Assets.space = g.newImage("space6.png", ImageFormat.RGB565);
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.bird1 = g.newImage("Bird1.png", ImageFormat.RGB565);
		Assets.bird2 = g.newImage("Bird2.png", ImageFormat.RGB565);
		Assets.bird3 = g.newImage("Bird3.png", ImageFormat.RGB565);
		Assets.backgroundanimearth0 = g.newImage("backgroundanimearth1-0.png", ImageFormat.RGB565);
		Assets.backgroundanimearth1 = g.newImage("backgroundanimearth1-1.png", ImageFormat.RGB565);
		Assets.backgroundanimearth2 = g.newImage("backgroundanimearth1-2.png", ImageFormat.RGB565);
		Assets.backgroundanimearth3 = g.newImage("backgroundanimearth1-3.png", ImageFormat.RGB565);
		Assets.backgroundanimearth4 = g.newImage("backgroundanimearth1-4.png", ImageFormat.RGB565);
		Assets.backgroundanimearth5 = g.newImage("backgroundanimearth1-5.png", ImageFormat.RGB565);
		Assets.backgroundanimearth6 = g.newImage("backgroundanimearth1-6.png", ImageFormat.RGB565);
		Assets.backgroundanimearth7 = g.newImage("backgroundanimearth1-7.png", ImageFormat.RGB565);
		Assets.backgroundanimearth8 = g.newImage("backgroundanimearth1-8.png", ImageFormat.RGB565);
		Assets.backgroundanimearth9 = g.newImage("backgroundanimearth1-9.png", ImageFormat.RGB565);
		Assets.planet1 = g.newImage("planet-10.png", ImageFormat.RGB565);
		Assets.planet2 = g.newImage("planet-02.png", ImageFormat.RGB565);
		Assets.planet3 = g.newImage("planet-03.png", ImageFormat.RGB565);
		Assets.planet4 = g.newImage("planet-04.png", ImageFormat.RGB565);
		Assets.planet5 = g.newImage("planet-05.png", ImageFormat.RGB565);
		Assets.planet6 = g.newImage("planet-06.png", ImageFormat.RGB565);
		Assets.planet7 = g.newImage("planet-07.png", ImageFormat.RGB565);
		Assets.planet8 = g.newImage("planet-08.png", ImageFormat.RGB565);
		Assets.planet9 = g.newImage("planet-09.png", ImageFormat.RGB565);
		
		Assets.loadingscreen = g.newImage("loadinscreenblank.png", ImageFormat.RGB565);

		Assets.socketIO = new SocketIO();
		Assets.gridButtonMyPlanet = g.newImage("turquoisedot.png", ImageFormat.RGB565);
		Assets.gridButtonNotMyPlanet = g.newImage("reddot.png", ImageFormat.RGB565);
		
		ArrayList<Image> birdAnimationImages = new ArrayList<Image>();
		birdAnimationImages.add(Assets.bird1);
		birdAnimationImages.add(Assets.bird2);
		birdAnimationImages.add(Assets.bird3);

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
		Assets.movingEarth = new Objects_Animation(movingEarthImages);
		
		
		//Assets.birdAnimation = new Objects_Animation(birdAnimationImages);
		//Assets.loadingStringAnimation = new Objects_Animation(loadingStrings);
		
		// Set GameScreen to ScreenB_MainMenu.
        game.setScreen(new ScreenJ_AvatarChooser(game));
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
