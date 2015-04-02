package com.init.Game99_AndroidLibrary;
import com.init.framework.Image;

public class Assets {
	/*game properties*/
	public static boolean running, ready = false, gameover = false, Imready = false
			 , otherQuit = false, //in Game
			 freeze = false;
	public static float runTime;
	public static SocketIO socketIO;
	
	public static int otherPlayerPress = -1, 
			id, 
			health,
			bombLoc = -1, 
			bombedLoc = -1;
	
    public static int OTHER = 1, TIME=2, WON = 3, LOST = 0, 
    		QUIT = 4,
    		GRIDSIZE=130,
			BOMBED = -2;
    
	public static boolean[] interGalaticaMapVector = null;
	//public static Sound click;
	
	/*game images*/
	public static Image space,
		instruction,
	    chosen,//space background with 42
		readyButton,
		waitingButton,
		you_won,
		you_lost,
		avatar_page, //starry night
		start,
		end_page,
		restart,
		bomb,
		smallest,
		hint
		;
	
	//planet avatars
	public static Image planet1, planet2, planet3, planet4, planet5, planet6, planet7,
	planet8, planet0;
	//game dots
	public static Image gridButtonMyPlanet, gridButtonNotMyPlanet;
}
