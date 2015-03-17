package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class ScreenF_AvatarChooser extends Screen {

	private int gameWidth = game.getGraphics().getWidth();
	private int gameHeight = game.getGraphics().getHeight();
	private Graphics g = game.getGraphics();
	private Objects_StartButton startButton;
	private ArrayList<String> avatarsChosen = new ArrayList<String>();
	private List<TouchEvent> touchEvents;
	private boolean ready = false;
	private ArrayList<Objects_GeneralAvatar> avatarList = new ArrayList<Objects_GeneralAvatar>();

	public ScreenF_AvatarChooser(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		startButton = new Objects_StartButton(gameWidth, gameHeight, Assets.StartButtonPressed, Assets.start);
		avatarList.add(new Objects_GeneralAvatar("Avatar1", 30, 30, Assets.Avatar1);
		avatarList.add(new Objects_GeneralAvatar("Avatar2", 30, 130, Assets.Avatar2);
		avatarList.add(new Objects_GeneralAvatar("Avatar3", 30, 230, Assets.Avatar3);

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

		//getting touch information and perform player operation
		touchEvents = game.getInput().getTouchEvents();

		if (!ready || avatarsChosen.size()<3){
			for (TouchEvent event: touchEvents) {
				if (event.type == TouchEvent.TOUCH_UP) {
					for (Objects_GeneralAvatar i : avatarList){
						if (inBounds(event, i.getX(), i.getY(), i.getWidth(), i.getHeight())){
							avatarsChosen.add(i.toString());
						}
					}

					if (inBounds(event, startButton.getX(), startButton.getY(), startButton.getWidth(), startButton.getHeight())){
					}
				}
			}
		}
		
		if (ready){
//			game.setScreen();
		}


	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		g.drawRect(10, 10, gameWidth-20, gameHeight-20, Color.BLUE);
		g.drawImage(startButton.getDisplay(), startButton.getX(), startButton.getY());

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
