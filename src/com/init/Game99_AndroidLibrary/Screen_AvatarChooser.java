package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.Image;
import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Input.TouchEvent;

public class Screen_AvatarChooser extends Screen {
	
	private Image avatarChosen;
	private List<TouchEvent> touchEvents;
	private Paint painter;
	private ArrayList<Objects_GeneralAvatar> avatarList = new ArrayList<Objects_GeneralAvatar>();
	
	public Screen_AvatarChooser(Game game) {
		super(game);
		painter = new Paint();
		painter.setColor(Color.WHITE);
		painter.setTextAlign(Align.CENTER);
		painter.setTextSize(30);
		
		avatarList.add(new Objects_GeneralAvatar("Avatar1", 30, 30, Assets.planet1));
		avatarList.add(new Objects_GeneralAvatar("Avatar2", 30, 130, Assets.planet2));
		avatarList.add(new Objects_GeneralAvatar("Avatar3", 30, 230, Assets.planet3));
		avatarList.add(new Objects_GeneralAvatar("Avatar4", 30, 230, Assets.planet4));
		avatarList.add(new Objects_GeneralAvatar("Avatar5", 30, 230, Assets.planet5));
		avatarList.add(new Objects_GeneralAvatar("Avatar6", 30, 230, Assets.planet6));
		avatarList.add(new Objects_GeneralAvatar("Avatar7", 30, 230, Assets.planet7));
		avatarList.add(new Objects_GeneralAvatar("Avatar8", 30, 230, Assets.planet8));
		avatarList.add(new Objects_GeneralAvatar("Avatar0", 30, 230, Assets.planet0));
		Log.i("ScreenB_MainMenu", "ScreenB_MainMenu");
	}

	@Override
	public void update(float deltaTime) {
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				Log.i("Coor",event.x + ", " +event.y);
				if(utils.inBounds(event, 120,1045, 551, 127)){
					Assets.socketIO.getSocket().emit("ready", "");
					Assets.Imready = true;
				}
			}
		} 
		if(Assets.ready)game.setScreen(new Screen_Game(game));
		
/*		touchEvents = game.getInput().getTouchEvents();
		if (avatarsChosen.size()<3){
			for (TouchEvent event: touchEvents) {
				if (event.type == TouchEvent.TOUCH_UP) {
					for (Objects_GeneralAvatar i : avatarList){
						if (inBounds(event, i.getX(), i.getY(), i.getWidth(), i.getHeight())){
							avatarsChosen.add(i.toString());
						}
						}
				}
			}
		}
*/
		Log.i("Screen_LoadingScreenJ", "update");
		Assets.runTime += deltaTime;
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("Screen_LoadingScreenJ", "paint");
		Graphics g = game.getGraphics();
		g.drawImage(Assets.avatar_page, 0,0);
		//g.drawImage(Assets.start, 120, 1050);
		if(!Assets.ready && Assets.Imready) 
			g.drawString("Waiting for another player...", 200, 80, painter);
		
		/*g.drawImage(Assets.planet0, 65, 150);
		g.drawImage(Assets.planet1, 69, 350);
		g.drawImage(Assets.planet2, 75, 570);
		g.drawImage(Assets.planet3, 350, 150);
		g.drawImage(Assets.planet4, 340, 350);
		g.drawImage(Assets.planet5, 320, 550);
		g.drawImage(Assets.planet6, 600, 150);
		g.drawImage(Assets.planet7, 560, 350);
		g.drawImage(Assets.planet8, 590, 550);*/
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
