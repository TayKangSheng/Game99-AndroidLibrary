package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Input.TouchEvent;

public class ScreenJ_AvatarChooser extends Screen {
	private Graphics g = game.getGraphics();
	private Objects_StartButton startButton;
	private ArrayList<String> avatarsChosen = new ArrayList<String>();
	private List<TouchEvent> touchEvents;
	private boolean ready = false;
	private ArrayList<Objects_GeneralAvatar> avatarList = new ArrayList<Objects_GeneralAvatar>();
	public ScreenJ_AvatarChooser(Game game) {
		super(game);
		//startButton = new Objects_StartButton(gameWidth, gameHeight, Assets.StartButtonPressed, Assets.start);
		avatarList.add(new Objects_GeneralAvatar("Avatar1", 30, 30, Assets.planet1));
		avatarList.add(new Objects_GeneralAvatar("Avatar2", 30, 130, Assets.planet2));
		avatarList.add(new Objects_GeneralAvatar("Avatar3", 30, 230, Assets.planet3));
		avatarList.add(new Objects_GeneralAvatar("Avatar4", 30, 230, Assets.planet4));
		avatarList.add(new Objects_GeneralAvatar("Avatar5", 30, 230, Assets.planet5));
		avatarList.add(new Objects_GeneralAvatar("Avatar6", 30, 230, Assets.planet6));
		avatarList.add(new Objects_GeneralAvatar("Avatar7", 30, 230, Assets.planet7));
		avatarList.add(new Objects_GeneralAvatar("Avatar8", 30, 230, Assets.planet8));
		avatarList.add(new Objects_GeneralAvatar("Avatar8", 30, 230, Assets.planet9));


		Log.i("ScreenB_MainMenu", "ScreenB_MainMenu");
		//birdAnimation = Assets.birdAnimation;

	}

	@Override
	public void update(float deltaTime) {
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				Log.i("Coor",event.x + ", " +event.y);
			}
		}
				//		touchEvents = game.getInput().getTouchEvents();
				//
				//		if (!ready || avatarsChosen.size()<3){
				//			for (TouchEvent event: touchEvents) {
				//				if (event.type == TouchEvent.TOUCH_UP) {
				//					for (Objects_GeneralAvatar i : avatarList){
				//						if (inBounds(event, i.getX(), i.getY(), i.getWidth(), i.getHeight())){
				//							avatarsChosen.add(i.toString());
				//						}
				Log.i("Screen_LoadingScreenJ", "update");
				Assets.runTime += deltaTime;
				// TODO Auto-generated method stub

			}

			@Override
			public void paint(float deltaTime) {
				// TODO Auto-generated method stub
				Log.i("Screen_LoadingScreenJ", "paint");
				Graphics g = game.getGraphics();
				g.drawImage(Assets.loadingscreen, 0,0);
			}

			public boolean inBounds(TouchEvent event, int x, int y, int width,
					int height) {
				if (event.x > x && event.x < x + width - 1 && event.y > y
						&& event.y < y + height - 1)
					return true;
				else
					return false;
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
