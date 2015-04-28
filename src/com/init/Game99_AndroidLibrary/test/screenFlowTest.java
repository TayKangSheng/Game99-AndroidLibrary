package com.init.Game99_AndroidLibrary.test;

import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.test.ActivityInstrumentationTestCase2;

import com.init.Game99_AndroidLibrary.Assets;
import com.init.Game99_AndroidLibrary.NNGame;
import com.init.Game99_AndroidLibrary.Objects_ButtonHandler;
import com.init.Game99_AndroidLibrary.Objects_GridButton;
import com.init.Game99_AndroidLibrary.Objects_Timer;
import com.init.Game99_AndroidLibrary.Screen_First;
import com.init.Game99_AndroidLibrary.Screen_Game;
import com.init.Game99_AndroidLibrary.Screen_Initialize;
import com.init.framework.Input.TouchEvent;
import com.init.framework.Screen;

// IMPORTANT: All test cases MUST have a suffix "Test" at the end
//
// THAN:
// Define this in your manifest outside your application tag:
//  < instrumentation 
//    android:name="android.test.InstrumentationTestRunner"
//    android:targetPackage="com.treslines.ponto" 
//  / >
//
// AND:
// Define this inside your application tag:
//  < uses-library android:name="android.test.runner" / >
//
// The activity you want to test will be the "T" type of ActivityInstrumentationTestCase2
@RunWith(PowerMockRunner.class)
@PrepareForTest(Screen.class)
public class screenFlowTest extends ActivityInstrumentationTestCase2 < NNGame > {

	private NNGame newGame;
	private Objects_Timer clock;
	private Objects_GridButton button;
	private ArrayList<Objects_GridButton> testgameGrid;
	private Objects_ButtonHandler buttonHandler;
	private Assets assets;
	private Screen_Initialize initialise; 
	private Screen_First first;


	public screenFlowTest() {
		// create a default constructor and pass the activity class
		// you want to test to the super() constructor
		super(NNGame.class);
	}

	@Override
	// here is the place to setup the var types you want to test
	protected void setUp() throws Exception {
		super.setUp();

		// because i want to test the UI in the method testAlertasOff()
		// i must set this attribute to true
		setActivityInitialTouchMode(true);

		// init variables
		newGame = getActivity();
		clock = new Objects_Timer(8000);
		button = new Objects_GridButton(0, 0, true);

		assets = new Assets();
		initialise = new Screen_Initialize(newGame);

	}


	public void testGameFlow(){
		assertNotNull("screen initalised", newGame.getCurrentScreen());
		assertSame("WelcomePage", newGame.getCurrentScreen().getName());

		newGame.getCurrentScreen().update(10);
		newGame.getCurrentScreen().update(300);
		assertSame("Initialise", newGame.getCurrentScreen().getName());

		newGame.getCurrentScreen().update(10);
		assertSame("First", newGame.getCurrentScreen().getName());

		newGame.getCurrentScreen().update(10);

		TouchEvent click = new TouchEvent();
		click.x = 160;
		click.y = 1055;
		click.type = TouchEvent.TOUCH_UP;
		newGame.getInput().getTouchHandler().setTouchEvents(click);
		newGame.getCurrentScreen().update(10);
		assertSame("AvatarChooser", newGame.getCurrentScreen().getName());

		click.x = 75;
		click.y = 120;
		click.type = TouchEvent.TOUCH_UP;
		newGame.getInput().getTouchHandler().setTouchEvents(click);
		newGame.getCurrentScreen().update(10);

		click.x = 800/2;
		click.y = 1020;
		click.type = TouchEvent.TOUCH_UP;
		newGame.getInput().getTouchHandler().setTouchEvents(click);
		newGame.getCurrentScreen().update(10);

		assets.ready = true;
		newGame.getCurrentScreen().update(10);

		assertSame("PowerUpInstruction", newGame.getCurrentScreen().getName());

		newGame.getCurrentScreen().update(500);
		assertSame("Instruction", newGame.getCurrentScreen().getName());		

		boolean[] a = {true, false, true, false, true,
				true, false, true, false, true,
				true, false, true, false, true,
				true, false, true, false, true,
				true, false, true, false, true,
				true, false, true, false, true,
				true, false, true, false, true,
		};
		assets.interGalaticaMapVector = a;

		newGame.getCurrentScreen().paint(600);
		newGame.getCurrentScreen().update(600);
		assertSame("Game", newGame.getCurrentScreen().getName());

		newGame.getCurrentScreen().update(10);

		Screen_Game screen = (Screen_Game) newGame.getCurrentScreen();

		buttonHandler = new Objects_ButtonHandler(screen.gameGrid, newGame);

		int smallestNo = 10;
		for (int j=0 ; j<10 ; j++){

			for (int i=0 ; i<screen.gameGrid.size() ; i++){
				if (screen.gameGrid.get(i).getNormalClickable()){
					if (screen.gameGrid.get(i).getInt()==j){
						buttonHandler.Click(i, j);
					}
				}
			}
		}
		newGame.getCurrentScreen().update(10);

	}
}