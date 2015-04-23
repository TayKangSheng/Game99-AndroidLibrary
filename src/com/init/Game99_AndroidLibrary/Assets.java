package com.init.Game99_AndroidLibrary;
import com.init.framework.Image;
import com.init.framework.Music;
import com.init.framework.Sound;

public class Assets {
	/*game properties state related! => must be reset in result page*/
	public static boolean running, 
				ready = false, 
				gameover = false, 
			    Imready = false, //player is ready
			    otherQuit = false, 
			    //other quit => server gets signal => this player gets signal => otherQuit is set true
			   freeze = false, 
			   glow = false, won = false, lost = false;
			   //bombsent = false
			   ;
	public static float runTime, 
			glowRunTime = 0;
	public static SocketIO socketIO;
	public static int[] smallestLocs;
	public static int otherPlayerPress = -1, 
			avatar,
			id, 
			health,
			bombLoc = -1, 
			bombedLoc = -1,
			hintLoc = -1,
			smallestLoc = -1,
			nosmallest = -1,
			//nobomb = -1,
			nohint = -1
			//yesbomb = -1
			;
	
    public static int OTHER = 1, TIME=2, WON = 3, LOST = 0, 
    		QUIT = 4,
    		GRIDSIZE=120,
    		GRID_INTERVAL = 7,
    		IBOMB = -3,
			BOMBED = -2, HINTTIME = 15000,
			SHAKE_FRAME = 50, VIBRATION_TIME = 600,
			FONT_SIZE = 70, SENTBOMB = -4;
    public static String[][] colors = {
    	{"#1ACBA7","#2aa198"}, //green
    	{"#FB7027","#2aa198"}, //pink
    	{"#F6CB74","#2aa198"}, 
    	{"#D3D9BD","#2aa198"}, 
    	{"#73B8D0","#2aa198"}, 
    	{"#DD352E","#2aa198"},
    	{"#26A65B","#2aa198"}, 
    	{"#EE6851","#2aa198"}, 
    	{"#22A7F0","#2aa198"}
    };
    
	public static boolean[] interGalaticaMapVector = null;
	//public static Sound click;
	
	/*game images*/
	public static Image space,
		powerupinstruction,
		instruction,
	    chosen,//space background with 42
		readyButton,
		waitingButton1,
		waitingButton2,
		waitingButton3,
		waitingButton4,
		you_won,
		you_lost,
		avatar_page, //starry night
		start,
		end_page,
		restart,
		bomb,
		smallest,
		hint,
		avatar_page_blinkingstars,
		message_good,
		message_nice,
		message_cool,
		message_excellent,
		message_great,
		message_keepgoing,
		message_perfect,
		message_wrong,
		message_stillwrong,
		message_omg,
		message_canyounot,
		message_lastwarning,
		message_nomorechance,
		message_kthxbye,
		message_faint,
		initlogo
		;
	
	/* game BGM */
	public static Music 
		startScreenBGM,
		gameScreenBGM
		;
	
	public static Sound 
		click,
		popping,
		explosion,
		welcome,
		victoryBGM,
		loseBGM,
		spell,
		bubble;
	
	
	//planet avatars
	public static Image planet1, planet2, planet3, planet4, planet5, planet6, planet7,
	planet8, planet0;
	//game dots
	public static Image gridButtonMyPlanet, gridButtonNotMyPlanet;
	public static int sentbombLoc = -1;
}
