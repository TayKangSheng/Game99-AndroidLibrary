package com.init.Game99_AndroidLibrary.screeens;

import java.util.List;

import android.util.Log;

import com.init.Game99_AndroidLibrary.assets.Assets;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class MainMenuScreen extends Screen {
	private float count=0;
	private int countINT;
	private float sin;
	private int clearScreen;
	private boolean clearScreenBool;
	private int gameWidth = game.getGraphics().getWidth();
	private int gameHeight = game.getGraphics().getHeight();
	private boolean[] blocksColour = {true,false,true,false};
	private int blockWidth = gameWidth/4;

	public MainMenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		Log.i("NNGame", "MainMenuScreen");
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.i("MainMenuScreen", "update");
		count+=0.1;
		if (count > 3){
			count=0;
		}
		countINT= (int) count;
		sin += 0.1;
		if (sin>Math.PI){
			sin = 0;
		}

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, gameHeight-blockWidth , blockWidth, blockWidth+5 ) ){
					blocksColour[0]=!blocksColour[0];
				} else if (inBounds(event, blockWidth, gameHeight-blockWidth , blockWidth, blockWidth+5 ) ){
					blocksColour[1]=!blocksColour[1];
				} else if (inBounds(event, blockWidth*2, gameHeight-blockWidth , blockWidth, blockWidth+5)){
					blocksColour[2]=!blocksColour[2];
				} else if (inBounds(event, blockWidth*3, gameHeight-blockWidth , blockWidth+5, blockWidth+5)){
					blocksColour[3]=!blocksColour[3];
				}
			}
		}
		if (allTrue(blocksColour)){
			clearScreenBool=true; 
		}
		
		if (clearScreenBool){
			clearScreen+=10;
			if (clearScreen > game.getGraphics().getHeight()){
				game.setScreen(new LoadingScreenTwo(game));
			}
		}
	}
	
	public boolean allTrue(boolean[] x){
		int count = 0;
		for (int i=0 ; i<x.length ; i++){
			if (x[i])
				count+=1;
		}
		if (count == x.length)
			return true;
		else
			return false;
	}
	
    public boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
		//		g.drawRect((game.getGraphics().getWidth()/2)-(width/2), (game.getGraphics().getHeight()/2)-(height/2), width, height, -16777216);
		g.drawImage(Assets.birdAnimation.get(countINT)
				, (gameWidth/2)-(Assets.birdAnimation.get(countINT).getWidth()/2) 
				, (int)((game.getGraphics().getHeight()/2)-(Assets.birdAnimation.get(countINT).getHeight()/2)+(Math.sin(sin)*10)) );
		
		g.drawRect(0, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[0]));
		g.drawRect(blockWidth, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[1]));
		g.drawRect(blockWidth*2, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[2]));
		g.drawRect(blockWidth*3, gameHeight-blockWidth , blockWidth+5, blockWidth+5, getColour(blocksColour[3]));
		
		g.drawRect(0, 0, gameWidth, clearScreen, -16711681);
	}
	
	public int getColour(boolean x){
		if (x)
			return -1;
		else
			return  -16777216;
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
