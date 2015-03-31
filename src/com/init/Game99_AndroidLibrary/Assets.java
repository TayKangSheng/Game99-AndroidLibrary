package com.init.Game99_AndroidLibrary;
import com.init.framework.Image;

public class Assets {
	/*game properties*/
	public static boolean running, ready = false, gameover = false, Imready = false
			 , otherQuit = false, freeze = false;
	public static float runTime;
	public static SocketIO socketIO;
	public static int otherPlayerPress = -1, id, health
			, OTHER = 1, TIME=2, WON = 3, LOST = 0, GRIDSIZE=130;
	public static boolean[] interGalaticaMapVector = null;
	//public static Sound click;
	
	/*game images*/
	public static Image space,
	    chosen,//space background with 42
		start, //start button 
		readyButton,
		waitingButton,
		chooseplanet,
		loadingscreen,
		avatar_page,
		cheese130
		;
	
	//for mainmenu animation;
	public static Image backgroundanimearth0, backgroundanimearth1, backgroundanimearth2, 
	backgroundanimearth3, backgroundanimearth4, backgroundanimearth5, backgroundanimearth6, 
	backgroundanimearth7, backgroundanimearth8, backgroundanimearth9
	, bomb, smallest;
	
	//planet avatars
	public static Image planet1, planet2, planet3, planet4, planet5, planet6, planet7,
	planet8, planet0;
	//game dots
	public static Image gridButtonMyPlanet, gridButtonNotMyPlanet;
}
