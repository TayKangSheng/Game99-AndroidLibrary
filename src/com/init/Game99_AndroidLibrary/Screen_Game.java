package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;

public class Screen_Game extends Screen{
	private NNGame game;
	private int gameWidth, smallestNo = 10;
	private Graphics g;
	private Paint painter = new Paint() //for the timer;
		, painter1 = new Paint(), //for numbers on the grid;
		painter2 = new Paint(),
		painter3 = new Paint();
	private float GamerunTime = 0;
	private Objects_Timer clock;
	private boolean wholeWon = true, wholeLost = true;
	private Objects_ButtonHandler buttonHandler;
	private ArrayList<Objects_GridButton> gameGrid = 
			new ArrayList<Objects_GridButton>();
	private List<TouchEvent> touchEvents;
	private Objects_GridButton buttontemp1, buttontemp0;
	
	public Screen_Game(NNGame game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		gameWidth = game.getGraphics().getWidth();
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");
		Assets.gridButtonNotMyPlanet = g.newImage("turquoisedot.png", ImageFormat.RGB565, false);
		if(Assets.gridButtonMyPlanet==null){
			Assets.gridButtonMyPlanet = g.newImage("reddot.png", ImageFormat.RGB565, false);
		}
		Assets.running = true;
		Assets.health = 10;
		clock = new Objects_Timer();
		painter.setColor(clock.getColor());
		painter.setTextSize(clock.getTextSize());
		
		painter1.setColor(Color.WHITE);
		painter1.setTextSize(80);
		painter1.setTextAlign(Paint.Align.CENTER);
		/*initialize gridButtons*/
		for (int i=0; i<35;i++){
			buttontemp0 = new Objects_GridButton(75+(i%5)*130, 
					150+((int)(i/5))*130, Assets.interGalaticaMapVector[i]);
			gameGrid.add(buttontemp0);
		}
		buttonHandler =  new Objects_ButtonHandler(gameGrid, game);
	}

	/* (non-Javadoc)
	 * @see com.init.framework.Screen#update(float)
	 * click
				1. Sort list of values
				2. Check whether the click is valid.
					2a. If it is, change image, send coordinates
					2b. If it is not, health-1
		lose:
		1. timeout--> no socket
		2. lose all your life: --> gameover o
	 */
	@Override
	public void update(float deltaTime) {
		//Log.i("ScreenD_GameScreen", "update");
		GamerunTime += deltaTime;
		// check clock
		/*if(Assets.health==0) {
			Assets.socketIO.getSocket().emit("gameover");
			game.setScreen(new Screen_Result(game, gameGrid, "life")); 
		}*/
		if(Assets.otherQuit) game.setScreen(new Screen_Result(game, gameGrid, Assets.OTHER));
		if (Integer.valueOf(clock.getValue(GamerunTime))<=0){
			game.setScreen(new Screen_Result(game, gameGrid, Assets.TIME));
		}
		// receive data and change color
		if(Assets.otherPlayerPress>=0){
			gameGrid.get(Assets.otherPlayerPress).setNormalClickable();
			Assets.otherPlayerPress = -1;
		}
		if(Assets.freeze) return;
		// Find smallest Number
		smallestNo = 10;
		wholeWon = true;
		wholeLost = true;
		for (Objects_GridButton i : gameGrid){
			if (i.getInt()>=0){
				wholeWon = false;
				if (i.getInt() < smallestNo){
					smallestNo = i.getInt();
				}
			}else{ wholeLost = false;}
		}
		if(wholeWon) {
			//Assets.socketIO.getSocket().emit("gameover", "won");
			game.setScreen(new Screen_Result(game, gameGrid, Assets.WON));
		}
		else if(wholeLost){
			//Assets.socketIO.getSocket().emit("gameover", "lost"); 
			game.setScreen(new Screen_Result(game, gameGrid, Assets.LOST));
		}
		//getting touch information and perform player operation
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				for (int index=0;index<35;index++){
					buttontemp1 = gameGrid.get(index);
					//grid-button
					if (utils.inBounds(event, buttontemp1.getX(), buttontemp1.getY(), 130, 130)){
						buttonHandler.Click(index, smallestNo);
						//click(buttontemp1, index);
				}
			}
			}
		}
	}
	
	@Override
	public void paint(float deltaTime) {
		//Log.i("ScreenD_GameScreen", "paint");
		// White Background for the entire screen
		g.clearScreen(Color.parseColor("#2c3e50"));
		// Gray Background for health and timer
		g.drawRect(5, 5, gameWidth-10, 140, Color.parseColor("#2c3e50"));
		// Magenta background for power ups
		g.drawRect(5, 1065, gameWidth-10, 210, Color.parseColor("#2c3e50"));
		// Paint timer
		g.drawString(clock.getValue(GamerunTime), 669, 120, painter);
		/*// Paint health
		for (int i=0 ; i<Assets.health ; i++){
			g.drawRect(50+(i*50), 50, 50, 50, Color.parseColor("#2ecc71"));
		}
		
		// Paint Power ups
		for (int i=0 ; i<3 ; i++){
			g.drawRect(90+(i*210), 1070, 100, 100, Color.parseColor("#e67e22"));
		} */
		
		// Draw Grids
		for (Objects_GridButton i : gameGrid){
			if(i.getShake()){
				i.updateFrame();
				g.drawImage(i.getImageDisplay(),0,0,
						i.getX()+i.getxchange(), i.getY()+i.getychange(), Assets.GRIDSIZE,
						Assets.GRIDSIZE, painter2);
			} else if(i.getShrink()){
				i.updateFrame();
				Log.i("shrink", i.getychange() +" "+i.getw()+" ");
						
				g.drawImage(Assets.gridButtonNotMyPlanet, 0, 0, 
						i.getX()+i.getxchange(), i.getY()+i.getychange(), 
						i.getw(), i.geth(), painter2);
			} else if(i.getPop()){
				i.updateFrame();
				
				Log.i("pop", i.getychange() +" "+i.getw()+" ");
				g.drawImage(i.getImageDisplay(), 0, 0, 
						i.getX()+i.getxchange(), i.getY()+i.getychange(), 
						i.getw(), i.geth(), painter2);
			}else g.drawImage(i.getImageDisplay(), 0,0,i.getX(), i.getY(), Assets.GRIDSIZE,
					Assets.GRIDSIZE, painter2);
			
			if (i.getClickable()){
				if (i.getNormalClickable()){
					if(i.getShake()){
						g.drawString(i.getRandomInt(), i.getX()+60+i.getxchange(), 
								i.getY()+90+i.getychange() ,painter1);
					}
					else g.drawString(i.getRandomInt(), i.getX()+60, i.getY()+90 ,painter1);
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