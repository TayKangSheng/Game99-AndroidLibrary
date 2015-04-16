package com.init.Game99_AndroidLibrary;

import android.util.Log;

import com.init.framework.Screen;
import com.init.framework.implementation.AndroidGame;

public class NNGame extends AndroidGame {

	@Override
	public Screen getInitScreen() {
		// TODO Auto-generated method stub
		Log.i("Game99-AndroidLibrary", "A_Initialisation_screen");
		return new Screen_WelcomePage(this);
	}
}
