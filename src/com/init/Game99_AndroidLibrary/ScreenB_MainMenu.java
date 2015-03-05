package com.init.Game99_AndroidLibrary;

import java.util.List;

import android.util.Log;

import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

public class ScreenB_MainMenu extends Screen {
	private int clearScreen;
	private boolean clearScreenBool;
	private int gameWidth = game.getGraphics().getWidth();
	private int gameHeight = game.getGraphics().getHeight();
	private boolean[] blocksColour = {true,false,true,false};
	private int blockWidth = gameWidth/4;
	private float runTime;
	private Objects_Animation birdAnimation;

	public ScreenB_MainMenu(Game game) {
		super(game);
		
		Log.i("NNGame", "ScreenB_MainMenu");
		runTime = 0;
		birdAnimation = Assets.birdAnimation;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		runTime += deltaTime;
		Log.i("MainMenuScreen", "update");

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
			if (clearScreen > gameHeight){
				game.setScreen(new ScreenC_LoadingScreen(game));
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
		
		Graphics g = game.getGraphics();
		
		// Draw background Image
		g.drawImage(Assets.menu, 0, 0);
		
		// Draw BirdAnimation
		g.drawImage(birdAnimation.getFrame(runTime/10), 
				((gameWidth/2)-(birdAnimation.getWidth()/2)), 
				(int) ((gameHeight/2)-(birdAnimation.getHeight()/2)+(3*(Math.sin(runTime/10)))) );
		
		// Draw bottom blocks
		g.drawRect(0, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[0]));
		g.drawRect(blockWidth, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[1]));
		g.drawRect(blockWidth*2, gameHeight-blockWidth , blockWidth, blockWidth+5, getColour(blocksColour[2]));
		g.drawRect(blockWidth*3, gameHeight-blockWidth , blockWidth+5, blockWidth+5, getColour(blocksColour[3]));
		
		// Draw white background for blocks
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
