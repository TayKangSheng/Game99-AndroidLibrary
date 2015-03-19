package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;

public class ScreenE_Results extends Screen{
	
	private int gameHeight = game.getGraphics().getHeight();
	private int gameWidth = game.getGraphics().getWidth();
	private Paint painter = new Paint();
	private Graphics g;
	private int scoreCount = 0, opponentScoreCount = 0;
	private String reason;
	private List<TouchEvent> touchEvents;
	
	public ScreenE_Results(Game game, ArrayList<Objects_GridButton> list, String reason) {
		super(game);
		nullifyE();
		g = game.getGraphics();
		
		painter.setColor(Color.WHITE);
		painter.setTextSize(100);
		painter.setTextAlign(Paint.Align.CENTER);
		
		this.reason = reason;
		if(reason.equals("life")) return;
		for (Objects_GridButton i : list){
			if (i.getClickable()) opponentScoreCount++;
			else  scoreCount++;
		}
		Assets.loadingscreen = g.newImage("starrynight.png", ImageFormat.RGB565, false);
	}
	private void nullifyE(){
		
		Assets.gridButtonMyPlanet = null;
		Assets.gridButtonNotMyPlanet = null;
		System.gc();
	}
	@Override
	public void update(float deltaTime) {
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event,120,1045,551,127)) {
					Assets.socketIO.getSocket().emit("joined");
					nullify();
					game.setScreen(new ScreenB_MainMenu(game));
				}
			}
		}
	}
	
	@Override
	public void paint(float deltaTime) {
		g.clearScreen(Color.LTGRAY);
		g.drawImage(Assets.loadingscreen, 0, 0);
		g.drawImage(Assets.start, 120, 1050);

		if(this.reason.equals("life"))
			g.drawString("No lives",gameWidth/2 , gameHeight/2, painter);
		else if(this.reason.equals("other"))
			g.drawString("Won", gameWidth/2, gameHeight/2, painter);
		else if (scoreCount > opponentScoreCount){
			g.drawString("Won", gameWidth/2, gameHeight/2, painter);
		} else{
			g.drawString("Lost",gameWidth/2, gameHeight/2, painter);
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
	
	public boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x 
        		&& event.x < x + width - 1 
        		&& event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

	public static void nullify(){
		Assets.health = 10;
		Assets.running = true;
		Assets.gameover = false;
		Assets.ready = false;
		Assets.Imready = false;
		Assets.runTime = 0;
	}
}
