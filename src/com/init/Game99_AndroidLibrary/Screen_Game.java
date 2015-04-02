package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import com.init.Game99_AndroidLibrary.*;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;

public class Screen_Game extends Screen{
	private NNGame game;
	private Graphics g;

	private Paint painter = new Paint() //for the timer;
		, painter1 = new Paint(), //for numbers on the grid;
		painter2 = new Paint(),
		glowPainter = new Paint();

	private float GamerunTime = 0;
	private int gameWidth, smallestNo = 10;
	private boolean wholeWon = true, wholeLost = true;
	
	private Objects_Timer clock;
	private Objects_ButtonHandler buttonHandler;
	private Objects_GridButton buttontemp1, buttontemp0;
	
	private ArrayList<Objects_GridButton> gameGrid;
	private List<TouchEvent> touchEvents;
	
	public Screen_Game(NNGame game) {
		super(game);
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");

		//initialize the game and all its values
		this.game = game;
		g = game.getGraphics();
		clock = new Objects_Timer(10000);
		gameWidth = game.getGraphics().getWidth();
		gameGrid = new ArrayList<Objects_GridButton>();
		Assets.running = true;
		Assets.health = 10;
		//load images
		Assets.gridButtonNotMyPlanet = 
				g.newImage("turquoisedot.png", ImageFormat.RGB565, false);
		if(Assets.gridButtonMyPlanet==null){
			Assets.gridButtonMyPlanet = 
					g.newImage("reddot.png", ImageFormat.RGB565, false);
		}
		//painter: for clock 
		painter.setColor(clock.getColor());
		painter.setTextSize(clock.getTextSize());
		
		//painter1: for numbers on the grid
		painter1.setColor(Color.WHITE);
		painter1.setTextSize(80);
		painter1.setTextAlign(Paint.Align.CENTER);
		
		//glow painter for glowing
		glowPainter.setDither(true);
		glowPainter.setAntiAlias(true);
		glowPainter.setFilterBitmap(true);  
		ColorFilter colorFilterTint = new LightingColorFilter(Color.TRANSPARENT, Color.WHITE);
		glowPainter.setColorFilter(colorFilterTint);
		
		/*initialize gridButtons*/
		for (int i=0; i<35;i++){
			buttontemp0 = new Objects_GridButton(75+(i%5)*Assets.GRIDSIZE, 
					150+((int)(i/5))*Assets.GRIDSIZE, Assets.interGalaticaMapVector[i]);
			buttontemp0.pop(14); 
			//starting the pop action at the start
			gameGrid.add(buttontemp0);
		}
		buttonHandler =  new Objects_ButtonHandler(gameGrid, game);
	}

	@Override
	public void update(float deltaTime) {
		GamerunTime += deltaTime;
		// check clock
		/*if(Assets.health==0) {
			Assets.socketIO.getSocket().emit("gameover");
			game.setScreen(new Screen_Result(game, gameGrid, "life")); 
		}*/
		if(Assets.otherQuit)//check if opponent has quit
			game.setScreen(new Screen_Result(game, gameGrid, Assets.OTHER));
		if (Integer.valueOf(clock.getValue(GamerunTime))<=0){
			//check time
			game.setScreen(new Screen_Result(game, gameGrid, Assets.TIME));
		}
		// receive data and change color
		if(Assets.otherPlayerPress >= 0){
			gameGrid.get(Assets.otherPlayerPress).setNormalClickable();
			Assets.otherPlayerPress = -1;
		}
		if(Assets.bombedLoc >= 0){
			buttonHandler.Click(Assets.bombedLoc, Assets.BOMBED);
			Assets.bombedLoc = -1; 
		}
		if(Assets.freeze) return;
		// Find smallest Number
		smallestNo = 10;
		wholeWon = true;
		wholeLost = true;
		if(Assets.bombLoc>=0){
			gameGrid.get(Assets.bombLoc).setBomb();
			Assets.bombLoc = -1;
		}
		if(Assets.hintLoc>=0){
			gameGrid.get(Assets.hintLoc).setHint();
			Assets.hintLoc = -1;
		}
		if(Assets.smallestLoc>=0){
			gameGrid.get(Assets.smallestLoc).setSmallest();
			Assets.smallestLoc = -1;
		}
		if(Assets.smallestLocs!=null){
			for(int i: Assets.smallestLocs){
				gameGrid.get(i).setNormalClickable();
			}
			Assets.smallestLocs = null;
		}
		for (Objects_GridButton i : gameGrid){
			if (i.getInt()>=0){
				wholeWon = false;
				if (i.getInt() < smallestNo){
					smallestNo = i.getInt();
				}
			}else if(i.getBomb()){}
			else{ wholeLost = false;}
		}
		if(wholeWon) {
			game.setScreen(new Screen_Result(game, gameGrid, Assets.WON));
			Assets.socketIO.getSocket().emit("gameover", "iwon");
		} else if(wholeLost){
			game.setScreen(new Screen_Result(game, gameGrid, Assets.LOST));
		}
		//getting touch information and perform player operation
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				for (int index=0;index<35;index++){
					buttontemp1 = gameGrid.get(index);
					if (utils.inBounds(event, buttontemp1.getX(), buttontemp1.getY(), 130, 130)){
						buttonHandler.Click(index, smallestNo);
					if (utils.inBounds(event, 90, 1070, 100, 100)){
						game.setScreen(new Screen_Result(game, gameGrid, Assets.TIME));
						Assets.socketIO.getSocket().emit("gameover");
					}
				}
			}
			}
		}
	}
	
	@Override
	public void paint(float deltaTime) {
		//Log.i("deltatime",deltaTime+"");
		glowPainter.setAlpha( utils.accelerateDeccelerateCurve(75, 0.01, GamerunTime, 0).intValue()  );
		// White Background for the entire screen
		g.clearScreen(Color.parseColor("#2c3e50"));
		// Gray Background for health and timer
		g.drawRect(5, 5, gameWidth-10, 140, Color.parseColor("#2c3e50"));
		// Magenta background for power ups
		g.drawRect(5, 1065, gameWidth-10, 210, Color.parseColor("#2c3e50"));
		// Paint timer
		g.drawString(clock.getValue(GamerunTime), 669, 120, painter);
		// Draw Grids
		for (Objects_GridButton i : gameGrid){
			if(i.getShake()){
				i.updateFrame();
				g.drawImage(i.getImageDisplay(),0,0,
						i.getX()+i.getxchange(), i.getY()+i.getychange(), Assets.GRIDSIZE,
						Assets.GRIDSIZE, painter2);
			} else if(i.getShrink()){
				i.updateFrame();
				//Log.i("shrink", i.getychange() +" "+i.getw()+" ");
				g.drawImage(Assets.gridButtonNotMyPlanet, 0, 0, 
						i.getX()+i.getxchange(), i.getY()+i.getychange(), 
						i.getw(), i.geth(), painter2);
			} else if(i.getPop()){
				i.updateFrame();
				//Log.i("pop", i.getychange() +" "+i.getw()+" ");
				g.drawImage(i.getImageDisplay(), 0, 0, 
						i.getX()+i.getxchange(), i.getY()+i.getychange(), 
						i.getw(), i.geth(), painter2);
			} else {
				if(Assets.glow){
					Assets.glowRunTime+=deltaTime;
					if(Assets.glowRunTime>Assets.HINTTIME){
						Assets.glow = false;
						Assets.glowRunTime = 0;
					}
					if (i.getInt()==smallestNo){
						g.drawImage(i.getImageDisplay(), 0,0,i.getX(), i.getY(), Assets.GRIDSIZE,
								Assets.GRIDSIZE, glowPainter);
					} else g.drawImage(i.getImageDisplay(), 0,0,i.getX(), i.getY(), Assets.GRIDSIZE,
									Assets.GRIDSIZE, painter2);
				} else g.drawImage(i.getImageDisplay(), 0,0,i.getX(), i.getY(), Assets.GRIDSIZE,
						Assets.GRIDSIZE, painter2);
			}
			
			if (i.getNormalClickable()){
				if(i.getShake()){
					g.drawString(i.getRandomInt(), i.getX()+60+i.getxchange(), 
							i.getY()+90+i.getychange() ,painter1);
				}
				else {
					if(!i.getPop())
						g.drawString(i.getRandomInt(), i.getX()+60, i.getY()+90 ,painter1);
				}
		}
		}
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