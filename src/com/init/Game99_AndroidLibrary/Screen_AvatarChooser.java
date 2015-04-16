package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_AvatarChooser extends Screen {
	private NNGame game;
	private String avatarChosen;
	private List<TouchEvent> touchEvents;
	private Paint painter, painter1, painter2, glowPainter, whiteGlowPainter, blinkingstarsPainter;
	private ArrayList<Objects_GeneralAvatar> avatarList = 
			new ArrayList<Objects_GeneralAvatar>();
	private int gameWidth;
	private float runTime;
	private boolean chosen = false;
	private boolean waiting = false;
	private int[] planetSizeOffset = { 55, 30, 25, 30, 50, 15, 40, -10, 25};
	
	public Screen_AvatarChooser(NNGame game) {
		super(game);
		this.game = game;
		gameWidth = game.getGraphics().getWidth();
		
		painter = new Paint();
		painter.setColor(Color.WHITE);
		painter.setTextAlign(Align.CENTER);
		painter.setTextSize(30);
		

		glowPainter = new Paint();
		glowPainter.setDither(true);
		glowPainter.setAntiAlias(true);
		glowPainter.setFilterBitmap(true);  
		ColorFilter colorFilterTint = new LightingColorFilter(Color.TRANSPARENT, Color.YELLOW );
		glowPainter.setColorFilter(colorFilterTint);

		painter1 = new Paint();
		painter1.setColor(Color.WHITE);
		painter1.setTextSize(46);
		painter1.setTextAlign(Paint.Align.CENTER);

		painter2 = new Paint();
		
		whiteGlowPainter = new Paint();
		whiteGlowPainter .setDither(true);
		whiteGlowPainter .setAntiAlias(true);
		whiteGlowPainter .setFilterBitmap(true);  
		ColorFilter whiteFilterTint = new LightingColorFilter(Color.TRANSPARENT, Color.WHITE );
		whiteGlowPainter .setColorFilter(whiteFilterTint);
		
		blinkingstarsPainter = new Paint();
		
		this.avatarChosen = null;
		this.runTime = 0;
		
		
		avatarList.add(new Objects_GeneralAvatar("Avatar0", 525, 	70+500, Assets.planet0)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar1", 25, 	70, 	Assets.planet1)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar2", 275,  	70, 	Assets.planet2)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar3", 525, 	70, 	Assets.planet3)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar4", 25, 	70+250, Assets.planet4));  
		avatarList.add(new Objects_GeneralAvatar("Avatar5", 275, 	70+250, Assets.planet8)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar6", 525, 	70+250, Assets.planet6)); 
		avatarList.add(new Objects_GeneralAvatar("Avatar7", 25,		70+500, Assets.planet7));
		avatarList.add(new Objects_GeneralAvatar("Avatar8", 275, 	70+500, Assets.planet5)); 
		
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
				for (int i=0 ; i<avatarList.size() ; i++){
//				for (Objects_GeneralAvatar i : avatarList){
					if (utils.inBounds(event, avatarList.get(i).getX()+planetSizeOffset[i], 
							avatarList.get(i).getY()+planetSizeOffset[i],
							250-2*planetSizeOffset[i], 250-2*planetSizeOffset[i]) 
							&& Assets.Imready==false){
						Assets.click.play(1f);
						Assets.gridButtonMyPlanet = avatarList.get(i).getImage();
						chosen = true;
						for (Objects_GeneralAvatar j : avatarList)
							j.setchosen(false);
						avatarList.get(i).setchosen(true);
						Assets.avatar = i;
					}
				}
				// starting the game :) 
				if(utils.inBounds(event, gameWidth/2-Assets.readyButton.getWidth()/2-25, 1010, 
						Assets.readyButton.getWidth()+50, Assets.readyButton.getHeight()+50) && chosen){
				//if(utils.inBounds(event, 50, 900, 
				//		Assets.readyButton.getWidth(), Assets.readyButton.getHeight()) && chosen){
					if (waiting == false){
						Assets.click.play(1f);
						waiting = true;
					}
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
		g.drawImage(Assets.avatar_page_blinkingstars, 0, 0, 0, 0,800, 1280,blinkingstarsPainter);
		blinkingstarsPainter.setAlpha((int) (utils.accelerateDeccelerateCurve(127, 0.01, runTime, 0)+0.5d));
		
		whiteGlowPainter.setAlpha( utils.accelerateDeccelerateCurve(110, 0.01, runTime, 30).intValue()  );
		//g.drawImage(Assets.start, 120, 1050);
		if(!chosen) {
			g.drawString("please choose your avatar planet", 
					game.getGraphics().getWidth()/2, 
					1000, painter1);
			//g.drawImage(Assets.chooseplanet, 100, 1100);
		} else if(!Assets.ready && Assets.Imready && chosen){
			//g.drawImage(Assets.waitingButton, 100, 1000);
			g.drawImage(Assets.waitingButton, 
					gameWidth/2-Assets.waitingButton.getWidth()/2-25, 
					1010);
		}else{
			g.drawImage(Assets.readyButton, 
					gameWidth/2-Assets.readyButton.getWidth()/2-25, 
					1010);
			//g.drawImage(Assets.readyButton, 0, 0, 50, 900, Assets.readyButton.getWidth(), Assets.readyButton.getHeight(),whiteGlowPainter);
		}
		
		// Use for loop to draw all avatars.
		glowPainter.setAlpha( utils.accelerateDeccelerateCurve(70, 0.03, runTime, 5).intValue()  );
		for (int i=0 ; i<avatarList.size() ; i++ ){
//		for (Objects_GeneralAvatar i: avatarList){
			//g.drawImage(i.getImage(),0,0,i.getX(),i.getY(), 150, 150);
			g.drawImage(avatarList.get(i).getImage(), 0, 0, avatarList.get(i).
					getX()+planetSizeOffset[i], avatarList.get(i).getY()+planetSizeOffset[i],
					250-2*planetSizeOffset[i], 250-2*planetSizeOffset[i], painter2);
			if (avatarList.get(i).getchosen()){
				g.drawImage(avatarList.get(i).getImage(), 0,0, 
						avatarList.get(i).getX()+planetSizeOffset[i], 
						avatarList.get(i).getY()+planetSizeOffset[i], 
						250-2*planetSizeOffset[i], 250-2*planetSizeOffset[i], glowPainter);
			}
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
		Assets.gameScreenBGM.stop();
		Assets.gameScreenBGM.dispose();
		Assets.gameScreenBGM = null;
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
	}
	
}
