package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.init.framework.Audio;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class Screen_Result extends Screen{
	private NNGame game;
	private int gameHeight,gameWidth;
	private Paint painter = new Paint();
	private boolean won = false, lost = false, draw = false, music = false;
	private Graphics g;
	private int scoreCount = 0, opponentScoreCount = 0, reason;
	private List<TouchEvent> touchEvents;

	public Screen_Result(NNGame game, ArrayList<Objects_GridButton> list, int reason)  {
		super(game);
		Log.i("Screen_Results", "Constructor");

		this.game = game;
		gameHeight = game.getGraphics().getHeight();
		gameWidth = game.getGraphics().getWidth();
		
		nullifyE();
		g = game.getGraphics();

		painter.setColor(Color.WHITE);
		painter.setTextSize(30);
		painter.setTextAlign(Paint.Align.CENTER);

		this.reason = reason;
		if(this.reason==Assets.WON || this.reason==Assets.LOST) return;
		for (Objects_GridButton i : list){
			if (i.getNormalClickable()) opponentScoreCount++;
			else  scoreCount++;
		}

		if(scoreCount>opponentScoreCount){ 
			this.won = true;
		} else if(scoreCount==opponentScoreCount){ 
			this.draw = true;
		}else {
			this.lost = true;
		}

		Assets.avatar_page = g.newImage("starrynight.png", ImageFormat.RGB565, false);

	}

	private void nullifyE(){
		Assets.gridButtonMyPlanet = null;
		System.gc();
	}

	@Override
	public void update(float deltaTime) {

		if (!music){
			if(this.reason==Assets.WON){
				Assets.victoryBGM.play(1f);
			}
			else if(this.reason==Assets.LOST) 
				Assets.loseBGM.play(1f);
			else if(this.reason==Assets.OTHER){
				if(this.won) Assets.victoryBGM.play(1f);
				else if(this.lost) Assets.loseBGM.play(1f);
			} 
			else if(this.reason==Assets.TIME){
				if(this.won) Assets.victoryBGM.play(1f);
				else if(this.lost) Assets.loseBGM.play(1f);
			}
			music = true;
		}

		Log.i("Screen_Results", "update!!!!");
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event,120,1045,551,127)) {
					Assets.socketIO.getSocket().emit("joined");
					utils.restoreGame();
					game.setScreen(new Screen_Initialize(game));
				}
			}
		}
	}

	@Override
	public void paint(float deltaTime) {
		String msg = "";
		g.clearScreen(Color.LTGRAY);
		g.drawImage(Assets.end_page, 0, 0);
		g.drawImage(Assets.restart, game.getGraphics().getWidth()/2-Assets.restart.getWidth()/2-25, 
				1010);
		int h = gameHeight/2 - Assets.you_won.getHeight()/2-150;
		int w = gameWidth/2 - Assets.you_won.getWidth()/2;
		if(this.reason==Assets.WON){
			g.drawImage(Assets.you_won,w,h);
		}
		else if(this.reason==Assets.LOST) 
			g.drawImage(Assets.you_lost,w,h);
		else if(this.reason==Assets.OTHER){
			msg = "Other player quit.";
			if(this.won) g.drawImage(Assets.you_won,w,h);
			else if(this.lost) g.drawImage(Assets.you_lost,w,h);
			else msg += " and it's a draw";
		} 
		else if(this.reason==Assets.TIME){
			if(this.won) g.drawImage(Assets.you_won,w,h);
			else if(this.lost) g.drawImage(Assets.you_lost,w,h);
			else msg += "we have a draw";
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Result";
	}
}
