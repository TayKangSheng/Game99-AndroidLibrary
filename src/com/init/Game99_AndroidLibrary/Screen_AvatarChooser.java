package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
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
	private NNGame game;
	private String avatarChosen;
	private List<TouchEvent> touchEvents;
	private Paint painter, painter1;
	private ArrayList<Objects_GeneralAvatar> avatarList = 
			new ArrayList<Objects_GeneralAvatar>();
	private int gameWidth;
	private float runTime;
	private boolean chosen = false;
	
	public Screen_AvatarChooser(NNGame game) {
		super(game);
		this.game = game;
		gameWidth = game.getGraphics().getWidth();
		
		painter = new Paint();
		painter.setColor(Color.WHITE);
		painter.setTextAlign(Align.CENTER);
		painter.setTextSize(30);
		
		painter1 = new Paint();
		painter1.setColor(Color.WHITE);
		painter1.setTextSize(46);
		painter1.setTextAlign(Paint.Align.CENTER);
		
		this.avatarChosen = null;
		this.runTime = 0;
		
		avatarList.add(new Objects_GeneralAvatar("Avatar1", 50+0*((gameWidth-100)/3), 	70, 	Assets.planet1));
		avatarList.add(new Objects_GeneralAvatar("Avatar0", 50+2*((gameWidth-100)/3), 	70+500, Assets.planet0));
		avatarList.add(new Objects_GeneralAvatar("Avatar6", 50+2*((gameWidth-100)/3), 	70+250, Assets.planet6));
		avatarList.add(new Objects_GeneralAvatar("Avatar3", 50+2*((gameWidth-100)/3), 	70, 	Assets.planet3));
		avatarList.add(new Objects_GeneralAvatar("Avatar8", 50+1*((gameWidth-100)/3), 	70+500, Assets.planet8));
		avatarList.add(new Objects_GeneralAvatar("Avatar5", 50+1*((gameWidth-100)/3), 	70+250, Assets.planet5));
		avatarList.add(new Objects_GeneralAvatar("Avatar2", 50+1*((gameWidth-100)/3), 	70, 	Assets.planet2));
		avatarList.add(new Objects_GeneralAvatar("Avatar7", 50+0*((gameWidth-100)/3), 	70+500, Assets.planet7));
		avatarList.add(new Objects_GeneralAvatar("Avatar4", 50+0*((gameWidth-100)/3), 	70+250, Assets.planet4));

		Log.i("ScreenB_MainMenu", "ScreenB_MainMenu");
	}

	@Override
	public void update(float deltaTime) {
		this.runTime += deltaTime;
		for (int i=0 ; i<avatarList.size() ; i++){
			if (i%2 == 0)
				avatarList.get(i).addY((float)(Math.cos((runTime/30)+(i*(Math.PI/7.0)/3.0) )/2.5));
			else
				avatarList.get(i).addY((float)(Math.sin((runTime/30)+(i*(Math.PI/7.0)/3.0) )/2.5));
		}
		
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				Log.i("Coor",event.x + ", " +event.y);
				// Detect avatar chosen :)
				for (Objects_GeneralAvatar i : avatarList){
					if (utils.inBounds(event, i.getX(), i.getY(), i.getWidth(), i.getHeight())){
						Assets.gridButtonMyPlanet = i.getImage();
						chosen = true;
						i.setchosen();
					}
				}
				
				// starting the game :) 
				if(utils.inBounds(event, 100, 1100, 
						Assets.readyButton.getWidth(), Assets.readyButton.getHeight()) && chosen){
					Assets.socketIO.getSocket().emit("ready", "");
					Assets.Imready = true;
				}
			}
		} 
		if(Assets.ready) {
			nullify();
			game.setScreen(new Screen_Instruction(game));
		}
		
		//Log.i("Screen_LoadingScreenJ", "update");
		Assets.runTime += deltaTime;
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("Screen_LoadingScreenJ", "paint");
		Graphics g = game.getGraphics();
		g.clearScreen(-12303292);
		g.drawImage(Assets.avatar_page, 0,0);
		
		//g.drawImage(Assets.start, 120, 1050);
		if(!chosen) {
			g.drawString("please choose your avatar planet", game.getGraphics().getWidth()/2, 
					1050, painter1);
			//g.drawImage(Assets.chooseplanet, 100, 1100);
		} else if(!Assets.ready && Assets.Imready && chosen){
			g.drawImage(Assets.waitingButton, 100, 1100);
		}else{
			g.drawImage(Assets.readyButton, 100, 1100);
		}
//			g.drawString("Waiting for another player...", 200, 80, painter);
		
		// Use for loop to draw all avatars.
		for (Objects_GeneralAvatar i: avatarList){
			//g.drawImage(i.getImage(),0,0,i.getX(),i.getY(), 150, 150);
			g.drawImage(i.getImage(), i.getX(), i.getY());
		}

	}
	public void nullify(){
		for(int i=0;i<9;i++){
			if(!avatarList.get(i).getchosen())
				avatarList.get(i).getImage().dispose();
		}
		/*Assets.readyButton = null;
		Assets.waitingButton = null;
		Assets.space = null;
		Assets.avatar_page = null;*/
		
		System.gc();
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
