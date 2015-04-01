package com.init.Game99_AndroidLibrary;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Screen;
import com.init.framework.Image;
import com.init.framework.Graphics.ImageFormat;

public class Screen_Instruction extends Screen {
	private NNGame game;
	private Graphics g;
	private int gameWidth;
	private Objects_Timer timer;
	private float runtime;
	private int s, y, h, w, hIndex, index =0, haltIndex = 0, haltnum;
	private boolean bounce = false, down = true, 
			bounce2 = false, halt = false,
			up = false, stay = false;
	private int[] hData = {0,0,0,12,23,32,38,32,23,12
						,0,0,0,0,-12,-23,-32,-38,-32,-23,-12
						,0,0,0,0,12,23,32,38,32,23,12}; 
	public Screen_Instruction(NNGame game) {
		super(game);
		this.game = game;
		g = game.getGraphics();
		gameWidth = g.getWidth();
		g = game.getGraphics();
		timer = new Objects_Timer(500);
		runtime = 0;
		s = 0; 
		if(Assets.id==0) haltnum = 5;
		else haltnum = 4;
		h = g.getHeight()/2-Assets.instruction.getHeight()/2;
		w = gameWidth/2-Assets.instruction.getWidth()/2;
		
	}

	@Override
	public void update(float deltaTime) {
		//runtime and socketio;
		if(timer.getIntValue(runtime)<=0)
			game.setScreen(new Screen_Game(game));
	}

	@Override
	public void paint(float deltaTime) {
		runtime += deltaTime;
		g.clearScreen(Color.parseColor("#2c3e50"));
		g.drawRect(5, 5, gameWidth-10, 140, Color.parseColor("#2c3e50"));
		
		y = (int)(5+1.6*s*s);
		if(down){
			g.drawImage(Assets.instruction, w, y);
			s ++;
			if(y>h){
				down = false; bounce = true;
				g.drawImage(Assets.instruction, w, y);
			}
		}else if(bounce){
			if(hIndex>=hData.length){
				bounce = false; stay = true;
				g.drawImage(Assets.instruction, w, h);
			} else
				g.drawImage(Assets.instruction, w, h+hData[hIndex++]);
			
		}else if(bounce2){
			if(hIndex>=hData.length){
				up = true; bounce2 = false;
				g.drawImage(Assets.instruction, w, h);
			} else
				g.drawImage(Assets.instruction, w, h+hData[hIndex++]);
			
		}else if(stay){
			index ++;
			g.drawImage(Assets.instruction, w, h);
			if(index > 80){
				g.drawImage(Assets.instruction, w, h);
				stay = false; bounce2 = true; hIndex = 0;
			}
		}else if(up){
			g.drawImage(Assets.instruction, w, y);
			s++;
			if(y>g.getHeight()+50) {
				up = false; halt = true; haltIndex = 0;
			}
		}else if(halt){
			haltIndex ++;
			g.drawImage(Assets.instruction, w, y);
			if(haltIndex>haltnum)
			game.setScreen(new Screen_Game(game));
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
