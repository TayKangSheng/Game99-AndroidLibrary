package com.init.Game99_AndroidLibrary;

import java.net.URISyntaxException;
import java.util.ArrayList;

import android.util.Log;
import com.init.framework.implementation.*;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.init.framework.Audio;
import com.init.framework.FileIO;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Input;
import com.init.framework.Screen;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.init.framework.Game;
import com.init.framework.Graphics;
import com.init.framework.Image;
import com.init.framework.Screen;
import com.init.framework.Graphics.ImageFormat;

public class ScreenA_Initialisation extends Screen {
	Socket socket;

	public ScreenA_Initialisation(Game game) {
		super(game);
		Log.i("ScreenA_Initialisation", "A_Initialisation_screen");
		try {
    	socket = IO.socket("http://128.199.225.246:8080");
    	socket.connect(); // initiate connection to socket server

    	} catch (URISyntaxException e) {
    	e.printStackTrace();
    	}
    
    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            Log.d("socketio", "socket connected");
            socket.emit("button",
                    "2");
            socket.emit("landed", "I am android, and I just landed!");
            // socket.disconnect(); // why is there a disconnect here?
        }        
        
    })
    .on(Socket.EVENT_MESSAGE, new Emitter.Listener() {

        @Override
        public void call(Object... arg0) {
            // TODO Auto-generated method stub
            Log.d("socketio", "socket event message" + arg0);
            socket.emit("button",
                    "2");
        }
    });

    socket.on("grid", new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            // TODO Auto-generated method stub
            Log.d("socketio", "socket event connect error");
            //there is a grid!
            JSONObject data = (JSONObject) args[0];
            boolean[] tmp = new boolean[35];
            try {
				JSONArray array = data.getJSONArray("grid");
				for(int i=0;i<array.length();i++) tmp[i]=array.getBoolean(i);
				Assets.interGalaticaMapVector = tmp;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });
	}

	/**
	 *  Initialize all assets in game here.
	 *  After all assets are initialized, screen is set to main menu. 
	 */
	@Override
	public void update(float deltaTime) {
		Log.i("ScreenA_Initialisation", "update");
		
		// Initialization of ALL game assets.
		Assets.runTime=0;
		
		Graphics g = game.getGraphics();
		Assets.start = g.newImage("startbutton.png", ImageFormat.RGB565);
		Assets.space = g.newImage("space6.png", ImageFormat.RGB565);
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.bird1 = g.newImage("Bird1.png", ImageFormat.RGB565);
		Assets.bird2 = g.newImage("Bird2.png", ImageFormat.RGB565);
		Assets.bird3 = g.newImage("Bird3.png", ImageFormat.RGB565);
		Assets.gridButtonMyPlanet = g.newImage("turquoisedot.png", ImageFormat.RGB565);
		Assets.gridButtonNotMyPlanet = g.newImage("reddot.png", ImageFormat.RGB565);
		
		ArrayList<Image> birdAnimationImages = new ArrayList<Image>();
		birdAnimationImages.add(Assets.bird1);
		birdAnimationImages.add(Assets.bird2);
		birdAnimationImages.add(Assets.bird3);
		
		String[] loadingStrings = {"loading","loading.","loading..","loading..."};
		
		Assets.birdAnimation = new Objects_Animation(birdAnimationImages);
		Assets.loadingStringAnimation = new Objects_Animation(loadingStrings);
		
		// Set GameScreen to ScreenB_MainMenu.
        game.setScreen(new ScreenB_MainMenu(game));
	}

	@Override
	public void paint(float deltaTime) {
		Log.i("ScreenA_Initialisation", "paint");
		
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
