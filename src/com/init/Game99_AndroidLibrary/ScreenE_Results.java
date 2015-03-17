package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Paint;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;

public class ScreenE_Results extends Screen{
	
	private int gameHeight = game.getGraphics().getHeight();
	Paint painter = new Paint();
	int scoreCount = 0;
	int opponentScoreCount = 0;
	
	/// This is the results screen
	public ScreenE_Results(Game game, ArrayList<Objects_GridButton> list) {
		super(game);
		for ( Objects_GridButton i : list){
			if (i.getClickable() == true){
				opponentScoreCount++;
			} else{
				scoreCount++;
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
		
		painter.setColor(Color.BLACK);
		painter.setTextSize(300);
		if (scoreCount > opponentScoreCount){
			g.drawString("WIN", 50 , gameHeight/2, painter);
		} else{
			g.drawString("LOSE", 50 , gameHeight/2, painter);
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
