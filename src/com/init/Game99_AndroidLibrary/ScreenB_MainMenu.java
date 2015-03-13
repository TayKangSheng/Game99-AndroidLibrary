package com.init.Game99_AndroidLibrary;

import java.util.List;

import android.R;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
	private List<TouchEvent> touchEvents;
	private Paint paint = new Paint();
	//private Objects_Animation birdAnimation;

	public ScreenB_MainMenu(Game game) {
		super(game);
		Log.i("ScreenB_MainMenu", "ScreenB_MainMenu");
		//birdAnimation = Assets.birdAnimation;
	}

	@Override
	public void update(float deltaTime) {
		Log.i("ScreenB_MainMenu", "update");
		Assets.runTime += deltaTime;
		touchEvents = game.getInput().getTouchEvents();
		for (TouchEvent event: touchEvents) {
			if (event.type == TouchEvent.TOUCH_UP) {
				//System.out.println(event.x + ", " +event.y);
				if(inBounds(event,120,1045,551,127)) {
					Assets.socketIO.getSocket().emit("ready", "");
				}
			}
		}
		if(Assets.ready) game.setScreen(new ScreenD_GameScreen(game));
		//game.setScreen(new ScreenC_LoadingScreen(game));
	}
	

//				if (inBounds(event, 0, gameHeight-blockWidth , blockWidth, blockWidth+5 ) ){
//					blocksColour[0]=!blocksColour[0];
//				} else if (inBounds(event, blockWidth, gameHeight-blockWidth , blockWidth, blockWidth+5 ) ){
//					blocksColour[1]=!blocksColour[1];
//				} else if (inBounds(event, blockWidth*2, gameHeight-blockWidth , blockWidth, blockWidth+5)){
//					blocksColour[2]=!blocksColour[2];
//				} else if (inBounds(event, blockWidth*3, gameHeight-blockWidth , blockWidth+5, blockWidth+5)){
//					blocksColour[3]=!blocksColour[3];
//				}
//			}
//		}
//		if (allTrue(blocksColour)){
//			clearScreenBool=true; 
//		}
//		
//		if (clearScreenBool){
//			clearScreen+=20;
//			if (clearScreen > gameHeight){
//				game.setScreen(new ScreenC_LoadingScreen(game));
//			}
//		}
	
//	public boolean allTrue(boolean[] x){
//		int count = 0;
//		for (int i=0 ; i<x.length ; i++){
//			if (x[i])
//				count+=1;
//		}
//		if (count == x.length)
//			return true;
//		else
//			return false;
//	}
	
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
	public void paint(float deltaTime) {
		Log.i("ScreenB_MainMenu", "paint");
		
		Graphics g = game.getGraphics();
		
		// Draw background Image
		g.drawImage(Assets.space, 0, 0);
		g.drawImage(Assets.start, 120, 1050);
		g.drawString(Assets.msg, 50,50,paint);
		
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
