package com.init.Game99_AndroidLibrary;

import android.util.Log;

import com.init.Game99_AndroidLibrary.screeens.LoadingScreen;
import com.init.framework.Screen;
import com.init.framework.implementation.AndroidGame;

public class NNGame extends AndroidGame {

	@Override
	public Screen getInitScreen() {
		// TODO Auto-generated method stub
		Log.i("NNGame", "getInitScreen");
		return new LoadingScreen(this);
	}

}
