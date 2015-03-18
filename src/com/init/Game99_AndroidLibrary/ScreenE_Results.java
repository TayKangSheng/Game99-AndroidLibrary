package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Input.TouchEvent;

public class ScreenE_Results extends Screen{
	
	private int gameHeight = game.getGraphics().getHeight();
	Paint painter = new Paint();
	int scoreCount = 0;
	int opponentScoreCount = 0;
	String reason;
	private List<TouchEvent> touchEvents;
	/// This is the results screen
	public ScreenE_Results(Game game, ArrayList<Objects_GridButton> list
			,String reason) {
		super(game);
		this.reason = reason;
		if(reason.equals("life")) return;
		for (Objects_GridButton i : list){
			if (i.getClickable()) opponentScoreCount++;
			else  scoreCount++;
		}
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				//System.out.println(event.x + ", " +event.y);
				if(inBounds(event,120,1045,551,127)) {
					Assets.socketIO.getSocket().emit("ready");
					game.setScreen(new ScreenB_MainMenu(game));
					//Assets.socketIO.getSocket().emit("ready", "");
				}
			}
		}

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.clearScreen(Color.LTGRAY);
		
		painter.setColor(Color.WHITE);
		painter.setTextSize(300);
		g.drawImage(Assets.space, 0, 0);
		g.drawImage(Assets.start, 120, 1050);
		if(this.reason.equals("life"))
			g.drawString("Life",50 , gameHeight/2, painter);
		else if(this.reason.equals("other"))
			g.drawString("Win",50 , gameHeight/2, painter);
		else if (scoreCount > opponentScoreCount){
			g.drawString("WIN", 50 , gameHeight/2, painter);
		} else{
			g.drawString("LOSE", 50 , gameHeight/2, painter);
		}
	}

	@Override
	public void pause() {
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
