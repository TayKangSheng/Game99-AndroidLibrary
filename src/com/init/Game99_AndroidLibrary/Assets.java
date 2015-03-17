package com.init.Game99_AndroidLibrary;

import com.github.nkzawa.socketio.client.Socket;
import com.init.framework.Image;
import com.init.framework.Sound;

public class Assets {
	
	public static boolean running;
	public static float runTime;
	public static Image menu;
	public static Sound click;
	public static int health = 10;
	//public static Objects_Animation birdAnimation;
	public static Image space;
	public static Image start;
	public static Image bird1;
	public static Image bird2;
	public static Image bird3;
	public static Objects_Animation movingEarth;
	public static Image backgroundanimearth0;
	public static Image backgroundanimearth1;
	public static Image backgroundanimearth2;
	public static Image backgroundanimearth3;
	public static Image backgroundanimearth4;
	public static Image backgroundanimearth5;
	public static Image backgroundanimearth6;
	public static Image backgroundanimearth7;
	public static Image backgroundanimearth8;
	public static Image backgroundanimearth9;
	public static Image loadingscreen;
	public static Image planet1;
	public static Image planet2;
	public static Image planet3;
	public static Image planet4;
	public static Image planet5;
	public static Image planet6;
	public static Image planet7;
	public static Image planet8;
	public static Image planet9;
	public static Objects_GeneralAvatar Avatar1;
	public static Objects_GeneralAvatar Avatar2;
	public static Objects_GeneralAvatar Avatar3;
	public static Objects_GeneralAvatar Avatar4;
	public static Objects_GeneralAvatar Avatar5;
	public static Objects_GeneralAvatar Avatar6;
	public static Objects_GeneralAvatar Avatar7;
	public static Objects_GeneralAvatar Avatar8;
	public static Objects_GeneralAvatar Avatar9;
	public static Image gridButtonMyPlanet;
	public static Image gridButtonNotMyPlanet;
	public static SocketIO socketIO;
	public static boolean ready = false;
	public static String msg = "";
	public static int otherPlayerPress = -1;
	public static int id;
	
	public static boolean gameover = false;
	public static boolean[] interGalaticaMapVector = null;
		/*{false, true, true, false, false, true, true,
		false, false, true, true, false, false, true,
		true, false, false, true, true, false, false,
		true, true, false, true, false, true, false,
		true, false, true, false, false, true, false};*/
//	public static Objects_GridButton[] interGalacticaMap = new Objects_GridButton[35];
}
