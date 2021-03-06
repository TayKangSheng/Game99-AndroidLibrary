package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.init.framework.Audio;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_Game extends Screen{
	private NNGame game;
	private Graphics g;
	private int yourScore = 0, lifeBar = 0, lastyourScore = 0;
	private Paint painter = new Paint() //for the timer;
	, painter1 = new Paint(), //for numbers on the grid;
	painter2 = new Paint(),
	glowPainter = new Paint(),
	bombPainter = new Paint();

	private float GamerunTime = 0;
	private int gameWidth, smallestNo = 10;
	private boolean wholeWon = true, wholeLost = true, barChanging = false;
	private String hexcolor1, hexcolor2;
	private Objects_Timer clock;
	private Objects_ButtonHandler buttonHandler;
	private Objects_GridButton buttontemp1, buttontemp0;

	public ArrayList<Objects_GridButton> gameGrid;
	private List<TouchEvent> touchEvents;

	private long lastSavedTime;

	public Screen_Game(NNGame game) {
		super(game);
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");

		//initialize the game and all its values
		this.game = game;
		g = game.getGraphics();
		clock = new Objects_Timer(8000);
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
		painter1.setTextSize(Assets.FONT_SIZE);
		painter1.setTextAlign(Paint.Align.CENTER);
		Typeface tf = Typeface.create("Helvetica", Typeface.NORMAL);
		painter1.setTypeface(tf);

		//glow painter for glowing
		glowPainter.setDither(true);
		glowPainter.setAntiAlias(true);
		glowPainter.setFilterBitmap(true);  
		ColorFilter colorFilterTint = new LightingColorFilter(Color.TRANSPARENT, Color.YELLOW);
		glowPainter.setColorFilter(colorFilterTint);

		bombPainter.setDither(true);
		bombPainter.setAntiAlias(true);
		bombPainter.setFilterBitmap(true);  
		ColorFilter bombFilterTint = new LightingColorFilter(Color.TRANSPARENT, Color.rgb(239, 234, 96));
		bombPainter.setColorFilter(bombFilterTint);

		hexcolor1 = Assets.colors[Assets.avatar][0];
		hexcolor2 = Assets.colors[Assets.avatar][1];

		lastSavedTime = System.currentTimeMillis();

		/*initialize gridButtons*/
		for (int i=0; i<35;i++){
			buttontemp0 = new Objects_GridButton(90+(i%5)*(Assets.GRIDSIZE+Assets.GRID_INTERVAL), 
					180+((int)(i/5))*(Assets.GRIDSIZE+Assets.GRID_INTERVAL), 
					Assets.interGalaticaMapVector[i]);
			buttontemp0.pop(14); 
			//starting the pop action at the start
			gameGrid.add(buttontemp0);
		}
		buttonHandler =  new Objects_ButtonHandler(gameGrid, game);

		/* initialise audio */
		Assets.startScreenBGM.setLooping(true);
		Assets.startScreenBGM.setVolume(0.5f);
		Assets.startScreenBGM.play();
		
		game.getInput().clearTouchEvents();
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
		if (Integer.valueOf(clock.getValue(GamerunTime))<=10){
			Assets.glow = true;
		}
		// receive data and change color
		/* Receive opponent's click */
		if(Assets.otherPlayerPress >= 0){
			Assets.popping.play(1f);
			gameGrid.get(Assets.otherPlayerPress).setNormalClickable();
			Assets.otherPlayerPress = -1;
		}
		if(Assets.nosmallest >= 0){
			if(gameGrid.get(Assets.nosmallest).getlastclickable())
				gameGrid.get(Assets.nosmallest).setNormalNotClickable();
			else gameGrid.get(Assets.nosmallest).setNormalClickable();
			Assets.nosmallest = -1;
		}
		if(Assets.nohint >= 0){
			if(gameGrid.get(Assets.nohint).getlastclickable())
				gameGrid.get(Assets.nohint).setNormalNotClickable();
			else gameGrid.get(Assets.nohint).setNormalClickable();
			Assets.nohint = -1;
		}
		/*if(Assets.yesbomb>=0){
			buttonHandler.Click(Assets.yesbomb, Assets.IBOMB);
			Assets.yesbomb = -1;
		}*/
		if(Assets.bombedLoc >= 0){
			buttonHandler.Click(Assets.bombedLoc, Assets.BOMBED);
			Assets.bombedLoc = -1; 
		}
		if(Assets.sentbombLoc >= 0){
			buttonHandler.Click(Assets.sentbombLoc, Assets.SENTBOMB);
			Assets.sentbombLoc = -1; 
		}
		// if(Assets.freeze) return;
		// Find smallest Number
		smallestNo = 10;
		wholeWon = true;
		wholeLost = true;
		if(Assets.bombLoc>=0){
			gameGrid.get(Assets.bombLoc).setBomb();
			Assets.bombLoc = -1;
		}
		if(Assets.hintLoc>=0){ //location of hint button
			gameGrid.get(Assets.hintLoc).setHint();
			Assets.hintLoc = -1;
		}
		if(Assets.smallestLoc>=0){
			gameGrid.get(Assets.smallestLoc).setSmallest();
			Assets.smallestLoc = -1;
		}
		if(Assets.smallestLocs!=null){
			Assets.spell.play(1f);
			for(int i: Assets.smallestLocs){
				gameGrid.get(i).setNormalClickable();
			}
			Assets.smallestLocs = null;
		}
		lastyourScore = yourScore;
		yourScore = 0;
		for (Objects_GridButton i : gameGrid){
			if(i.getNormalClickable()){}
			else yourScore ++;
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
				if(Assets.freeze){
					break;
				}
				for (int index=0;index<35;index++){
					buttontemp1 = gameGrid.get(index);
					if (utils.inBounds(event, buttontemp1.getX(), buttontemp1.getY(), 130, 130)){
						buttonHandler.Click(index, smallestNo);
						//						if (utils.inBounds(event, 90, 1070, 100, 100)){
						//							game.setScreen(new Screen_Result(game, gameGrid, Assets.TIME));
						//							Assets.socketIO.getSocket().emit("gameover");
						//						}
					}
				}

			}
		}
	}
	public int lifeBar(){
		if(barChanging){
			if(lifeBar>20) {
				lifeBar = 0;
				barChanging = false;
			}
			else{lifeBar++;}
			return (int)(gameWidth/35.0 * lastyourScore + (yourScore-lastyourScore)*(gameWidth/20.0)*lifeBar/20.0);
		}else return (int)(gameWidth/35.0 * yourScore)+5;

	}
	@Override
	public void paint(float deltaTime) {
		//Log.i("deltatime",deltaTime+"");

		glowPainter.setAlpha( utils.accelerateDeccelerateCurve(75, 0.03, 
				GamerunTime, 0).intValue());

		// White Background for the entire screen
		g.clearScreen(Color.parseColor("#2c3e50"));

		// Gray Background for health and time (left, top, right, bottom)
		g.drawImage(Assets.avatar_page, 0, 0);
		//draw the life bar
		//other planet
		g.drawRect(0, 5, gameWidth, 40, Color.parseColor(hexcolor2));
		if(yourScore!=lastyourScore){
			barChanging = true;
			int right = lifeBar();
			Log.i("color", hexcolor1);
			g.drawRect(0, 5, right, 40, Color.parseColor(hexcolor1));
		} else if(barChanging){
			int right = lifeBar();
			g.drawRect(0, 5, right, 40, Color.parseColor(hexcolor1)); 
		} else g.drawRect(0, 5, (int)(gameWidth/35.0*yourScore), 40, Color.parseColor(hexcolor1)); 

		// Paint timer
		g.drawString(clock.getValue(GamerunTime), gameWidth/2-50, 140, painter);

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
				if (i.getBombed()){
					g.drawImage(Assets.gridButtonNotMyPlanet, 0, 0, 
							i.getX()+i.getxchange(), i.getY()+i.getychange(), 
							i.getw(), i.geth(), bombPainter);
				} else{
					g.drawImage(Assets.gridButtonNotMyPlanet, 0, 0, 
							i.getX()+i.getxchange(), i.getY()+i.getychange(), 
							i.getw(), i.geth(), painter2);
				}
			} else if(i.getPop()){
				i.updateFrame();
				//Log.i("pop", i.getychange() +" "+i.getw()+" ");
				if (i.getBombed()){
					g.drawImage(Assets.gridButtonNotMyPlanet, 0, 0, 
							i.getX()+i.getxchange(), i.getY()+i.getychange(), 
							i.getw(), i.geth(), glowPainter);
				} else{
					g.drawImage(i.getImageDisplay(), 0, 0, 
							i.getX()+i.getxchange(), i.getY()+i.getychange(), 
							i.getw(), i.geth(), painter2);
				}
			} else {
				if(Assets.glow){ //when hint is pressed
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
					if (i.getBombed());
					else if(!i.getPop())
						g.drawString(i.getRandomInt(), i.getX()+Assets.GRIDSIZE/2, i.getY()+Assets.GRIDSIZE/2+25,
								painter1);
				}
			}
		}
		//draw messages
		for (Objects_GridButton i : gameGrid){
			if (i.getMessageCountDown()!=0){
				if (i.getMessage()==1)
					g.drawImage(Assets.message_nice, i.getX(), i.getY());
				else if (i.getMessage()==2)
					g.drawImage(Assets.message_good, i.getX(), i.getY());
				else if (i.getMessage()==3)
					g.drawImage(Assets.message_great, i.getX(), i.getY());
				else if (i.getMessage()==4)
					g.drawImage(Assets.message_cool, i.getX(), i.getY());
				else if (i.getMessage()==5)
					g.drawImage(Assets.message_excellent, i.getX(), i.getY());
				else if (i.getMessage()==6)
					g.drawImage(Assets.message_keepgoing, i.getX(), i.getY());
				else if (i.getMessage()>=7)
					g.drawImage(Assets.message_perfect, i.getX(), i.getY());
				else if (i.getMessage()==-1)
					g.drawImage(Assets.message_wrong, i.getX(), i.getY());	
				else if (i.getMessage()==-2)
					g.drawImage(Assets.message_stillwrong, i.getX(), i.getY());
				else if (i.getMessage()==-3)
					g.drawImage(Assets.message_omg, i.getX(), i.getY());
				else if (i.getMessage()==-4)
					g.drawImage(Assets.message_canyounot, i.getX(), i.getY());
				else if (i.getMessage()==-5)
					g.drawImage(Assets.message_lastwarning, i.getX(), i.getY());
				else if (i.getMessage()==-6)
					g.drawImage(Assets.message_nomorechance, i.getX(), i.getY());
				else if (i.getMessage()==-7)
					g.drawImage(Assets.message_kthxbye, i.getX(), i.getY());
				else if (i.getMessage()==-8)
					g.drawImage(Assets.message_faint, i.getX(), i.getY());
				else
					g.drawImage(Assets.message_faint, i.getX(), i.getY());
				i.updateFrame();
			}
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		lastSavedTime = System.currentTimeMillis();
		while (Assets.startScreenBGM.isPlaying()){
			Assets.startScreenBGM.pause();
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		while (!Assets.startScreenBGM.isPlaying()){
			Assets.startScreenBGM.play();
		}
		GamerunTime += ((System.currentTimeMillis()-lastSavedTime)/10);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Assets.startScreenBGM.stop();
		Assets.startScreenBGM.dispose();
		Assets.startScreenBGM = null;
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Game";
	}
	
}