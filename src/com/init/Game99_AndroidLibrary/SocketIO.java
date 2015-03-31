package com.init.Game99_AndroidLibrary;

import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class SocketIO {
	private static Socket socket;
	private String ip = "http://128.199.225.246:8080";
	public Socket getSocket(){
		if(socket==null){
			try {
				socket = IO.socket("http://128.199.225.246:8080");
				socket.connect(); // initiate connection to socket server
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return socket;
		}
		return socket;
	}
	public SocketIO() {
		if(socket==null){
			try {
				socket = IO.socket(ip);
				socket.connect(); // initiate connection to socket server
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Log.d("socketio", "socket connected");
				socket.emit("joined", "I am android, and I just landed!");
				// socket.disconnect(); // why is there a disconnect here?
			}        
		});
		socket.on("ready", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					Assets.id = data.getInt("id");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				// socket.disconnect(); // why is there a disconnect here?
			}        
		});
		socket.on("button", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Assets.otherPlayerPress = (Integer) args[0];
				// socket.disconnect(); // why is there a disconnect here?
			}        
		});
		socket.on("gameover", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Assets.gameover = true;
				if(args.length>0){
					String result = (String) args[0];
					//if(result.equals("won")) //Assets.won = true;
					//else if(result.equals("lost")) //Assets.lost = true;
					if(result.equals("quit")) Assets.otherQuit = true;
				}
			}        
		});
		
		socket.on("gameover", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Assets.gameover = true;
			}        
		});
		
		socket.on("grid", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				// TODO Auto-generated method stub
				Log.d("socketio", "socket event connect error");
				//there is a grid!
				JSONObject data = (JSONObject) args[0];
				try {
					boolean[] tmp = new boolean[35];
					JSONArray array = data.getJSONArray("grid");
					if(Assets.id==0){
						for(int i=0;i<35;i++) 
							tmp[i] = array.getBoolean(i);
					} else{
						for(int i=0;i<35;i++) 
							tmp[i] = !array.getBoolean(i);
					}
					Assets.interGalaticaMapVector = tmp;
					Assets.ready = true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
