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

public class ScreenD_GameScreen extends Screen{

	private int gameWidth = game.getGraphics().getWidth();
	private Graphics g = game.getGraphics();
	private Paint painter = new Paint();
	private float GamerunTime = 0;
	private Objects_Timer clock;
	private int smallestNo = 10;
	
	
	private Objects_ButtonHandler buttonHandler;
	private ArrayList<Objects_GridButton> gameGrid = new ArrayList<Objects_GridButton>();
	private List<TouchEvent> touchEvents;
	private Objects_GridButton buttontemp1;
	private Objects_GridButton buttontemp0;
	
	public ScreenD_GameScreen(Game game) {
		super(game);
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");
		
		nullify();
		if(Assets.gridButtonMyPlanet==null){
			Assets.gridButtonMyPlanet = g.newImage("turquoisedot.png", ImageFormat.RGB565, false);
			Assets.gridButtonNotMyPlanet = g.newImage("reddot.png", ImageFormat.RGB565, false);
		}
		Assets.running = true;
		Assets.health = 10;
		clock = new Objects_Timer();
		/*initialize gridButtons*/
		for (int i=0; i<35;i++){
			buttontemp0 = new Objects_GridButton(75+(i%5)*130, 
					150+((int)(i/5))*130, Assets.interGalaticaMapVector[i]);
			gameGrid.add(buttontemp0);
		}
		buttonHandler =  new Objects_ButtonHandler(gameGrid);
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
		Log.i("ScreenD_GameScreen", "update");
		nullify();
		GamerunTime += deltaTime;
		// check clock
		if(Assets.health==0) {
			Assets.socketIO.getSocket().emit("gameover");
			game.setScreen(new ScreenE_Results(game, gameGrid, "life")); 
		}
		if(Assets.gameover){ //other side lost/quit
			game.setScreen(new ScreenE_Results(game, gameGrid, "other"));
		}
		if (Integer.valueOf(clock.getValue(GamerunTime))<=0){
			game.setScreen(new ScreenE_Results(game, gameGrid, "time"));
		}
		// receive data and change color
		if(Assets.otherPlayerPress>=0){
			gameGrid.get(Assets.otherPlayerPress).setNormalClickable();
			Assets.otherPlayerPress = -1;
		}
		// Find smallest Number
		smallestNo = 10;
		for (Objects_GridButton i : gameGrid){
			if (i.getInt()>=0){
				if (i.getInt() < smallestNo){
					smallestNo = i.getInt();
				}
			}
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
		Log.i("ScreenD_GameScreen", "paint");
		
		// White Background for the entire screen
		g.clearScreen(Color.parseColor("#2c3e50"));
		
		// Gray Background for health and timer
		g.drawRect(5, 5, gameWidth-10, 140, Color.parseColor("#2c3e50"));
		
		// Magenta background for power ups
		g.drawRect(5, 1065, gameWidth-10, 210, Color.parseColor("#2c3e50"));
		
		// Paint timer
		painter.setColor(clock.getColor());
		painter.setTextSize(clock.getTextSize());
		g.drawString(clock.getValue(GamerunTime), 669, 120, painter);

		// Paint health
		for (int i=0 ; i<Assets.health ; i++){
			g.drawRect(50+(i*50), 50, 50, 50, Color.parseColor("#2ecc71"));
		}
		// Paint Power ups
		for (int i=0 ; i<3 ; i++){
			g.drawRect(90+(i*210), 1070, 100, 100, Color.parseColor("#e67e22"));
		}
		Log.i("ScreenD_GameScreen", "TRACKER");
		
		// Draw Grids
		painter.setColor(Color.WHITE);
		painter.setTextSize(80);
		painter.setTextAlign(Paint.Align.CENTER);
		for (Objects_GridButton i : gameGrid){
			g.drawImage(i.getImageDisplay(), i.getX(), i.getY());
			if (i.getClickable()){
				if (i.getType().equals("NC")){
					g.drawString(i.getRandomInt(), i.getX()+60, i.getY()+90 ,painter);
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
	private void nullify(){
		Assets.planet0 = null;
		Assets.planet1 = null;
		Assets.planet2 = null;
		Assets.planet3 = null;
		Assets.planet4 = null;
		Assets.planet5 = null;
		Assets.planet6 = null;
		Assets.planet7 = null;
		Assets.planet8 = null;
		System.gc();
	}

}