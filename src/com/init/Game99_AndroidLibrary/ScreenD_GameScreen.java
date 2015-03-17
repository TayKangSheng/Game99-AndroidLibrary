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
import com.init.framework.Input.TouchEvent;

public class ScreenD_GameScreen extends Screen{

	private int gameWidth = game.getGraphics().getWidth();
	private Graphics g = game.getGraphics();
	private Paint painter = new Paint();
	private float runTime = 0;
	private Objects_Timer clock;
	private int health;
	private int smallestNo = 10;
	
	private Objects_ButtonHandler buttonHandler;
	private ArrayList<Objects_GridButton> gameGrid = new ArrayList<Objects_GridButton>();
	private List<TouchEvent> touchEvents;
	private Objects_GridButton buttontemp1;
	private Objects_GridButton buttontemp0;
	
	public ScreenD_GameScreen(Game game) {
		super(game);
		Log.i("ScreenD_GameScreen", "ScreenD_GameScreen");
		// Initialization of the Game Grid, Game Buttons and Timer, Health bar.
		for (int i=0; i<35;i++){
			buttontemp0 = new Objects_GridButton(75+(i%5)*130, 
					150+((int)(i/5))*130, Assets.interGalaticaMapVector[i]);
			gameGrid.add(buttontemp0);
		}
		// Initialization of clock
		clock = new Objects_Timer();
		// Initialization of health
		health = 10;
		// Initialization of Game
		Assets.running = true;
		buttonHandler =  new Objects_ButtonHandler(gameGrid);
	}

	/* (non-Javadoc)
	 * @see com.init.framework.Screen#update(float)
	 * click
				1. Sort list of values
				2. Check whether the click is valid.
					2a. If it is, change image, send coordinates
					2b. If it is not, health-1
	 */
	@Override
	public void update(float deltaTime) {
		Log.i("ScreenD_GameScreen", "update");
		runTime += deltaTime;
		// check clock
		if(health==0) {
			game.setScreen(new ScreenE_Results(game, gameGrid, "life")); 
		}
		if(Assets.gameover)
			game.setScreen(new ScreenE_Results(game, gameGrid, "quit"));
		
		if (Integer.valueOf(clock.getValue(runTime))<=0){
			game.setScreen(new ScreenE_Results(game, gameGrid, "time"));
		}
		// receive data and change color
		if(Assets.otherPlayerPress>=0){
			change(Assets.otherPlayerPress);
			//buttonHandler.onClick(Assets.otherPlayerPress);
			//buttonHandler.onClick(Assets.otherPlayerPress, buttonType, newButtonType);
			Assets.otherPlayerPress = -1;
		}
		// Find smallest Number
		smallestNo = 10;
		for (Objects_GridButton i : gameGrid){
			if (i.getRandomInt()!=null){
				if (Integer.valueOf(i.getRandomInt()) < smallestNo){
					smallestNo = Integer.valueOf(i.getRandomInt());
				}
			}
		}
		//getting touch information and perform player operation
		touchEvents = game.getInput().getTouchEvents();
		if (health > 0){
			for (TouchEvent event: touchEvents) {
				if (event.type == TouchEvent.TOUCH_UP) {
					//System.out.println(event.x+", "+event.y);
					for (int index=0;index<35;index++){
						buttontemp1 = gameGrid.get(index);
						//grid-button
						if (inBounds(event, buttontemp1.getX(), buttontemp1.getY(), 130, 130))
							buttonHandler.onClick(index);
							Assets.socketIO.getSocket().emit("button", index);
					}
				}
			}
		}
	}
	
//	public void click(Objects_GridButton button_click, int index){
//		if(button_click.getClickable()){
//			if (Integer.valueOf(button_click.getRandomInt()) == smallestNo){
//				//clicking is successful;
//				button_click.setImage(false);
//				Assets.socketIO.getSocket().emit("button", index);
//			} 
//			else{
//				health--;
//			}
//		}
//		else{
//			health--;
//		}
//	}
	
    public void change(int index){
		gameGrid.get(index).setButton("NC");
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
		g.drawString(clock.getValue(runTime), 669, 120, painter);

		// Paint health
		for (int i=0 ; i<health ; i++){
			g.drawRect(50+(i*50), 50, 50, 50, Color.parseColor("#2ecc71"));
		}
		if (health==0){
			painter.setColor(Color.BLACK);
			painter.setTextSize(50);
			g.drawString("DEAD", 50, 50, painter);
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
				} else{
					//painter.setTypeface(Typeface.SERIF);
					//g.drawString(i.getType(), i.getX()+60, i.getY()+90 ,painter);
				}
			}
		}
	}

	/**
	 * 
	 * @param event = touch event
	 * @param x = top left hand x coordinate
	 * @param y = top right hand y coordinate
	 * @param width = width of button
	 * @param height = height of button
	 * @return true = inBounds, false = outBounds.
	 */
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