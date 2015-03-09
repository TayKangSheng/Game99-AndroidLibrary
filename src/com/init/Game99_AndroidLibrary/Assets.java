package com.init.Game99_AndroidLibrary;

import com.init.framework.Image;
import com.init.framework.Sound;

public class Assets {
	
	public static boolean running;
	public static float runTime;
	public static Image menu;
	public static Sound click;
	public static Objects_Animation birdAnimation;
	public static Image space;
	public static Image bird1;
	public static Image bird2;
	public static Image bird3;
	public static Image gridButtonMyPlanet;
	public static Image gridButtonNotMyPlanet;
	public static Objects_Animation loadingStringAnimation;
	public static boolean[] interGalaticaMapVector = 
		{false, true, true, false, false, true, true,
		false, false, true, true, false, false, true,
		true, false, false, true, true, false, false,
		true, true, false, true, false, true, false,
		true, false, true, false, false, true, false};
//	public static Objects_GridButton[] interGalacticaMap = new Objects_GridButton[35];
}
