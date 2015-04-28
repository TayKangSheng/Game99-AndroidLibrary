package com.init.Game99_AndroidLibrary.test;

import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.content.res.Configuration;
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
public class AndroidActivityTest extends ActivityInstrumentationTestCase2 < NNGame > {

	private NNGame newGame;
	private Objects_Timer clock;
	private Objects_GridButton button;
	private ArrayList<Objects_GridButton> testgameGrid;
	private Objects_ButtonHandler buttonHandler;
	private Assets assets;
	private Screen_Initialize initialise; 
//	private Screen_First first;
	

	public AndroidActivityTest() {
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
		
//		game = new Screen_Game(newGame);
		
		testgameGrid = new ArrayList<Objects_GridButton>();
		for (int i=0; i<35;i++){
			boolean bool = true;
			Objects_GridButton buttontemp0 = new Objects_GridButton(0, 0, bool);
			buttontemp0.pop(14); 
			bool = !bool;
			//starting the pop action at the start
			testgameGrid.add(buttontemp0);
		}

		buttonHandler =  new Objects_ButtonHandler(testgameGrid, newGame);


	}

	// usually we test some pre-conditions. This method is provided
	// by the test framework and is called after setUp()

	/* Test: new game is initialized after onCreate() is called. */
	public void testPreconditions() {
		assertNotNull("newGame Activity is null", newGame);
		assertNotNull("Graphics is not null", newGame.getGraphics());
		assertNotNull("FileIO is not null", newGame.getFileIO());
		assertNotNull("audio is not null", newGame.getAudio());
		assertNotNull("Input is not null", newGame.getInput());
		assertNotNull("rendView is not null", newGame.getRenderView());

		assertEquals(Configuration.ORIENTATION_PORTRAIT, newGame.getResources().getConfiguration().orientation);

	}

	/* Test: welcomepage is the init screen set. */
	public void testSplashScreenInitialised() {
		assertNotNull("screen initalised", newGame.getCurrentScreen());
		assertSame("WelcomePage", newGame.getCurrentScreen().getName());
	}

	/* Test: Timer condition is correct when runTime is larger than countdown timer set for clock */
	public void testTimer(){

		// runTime smaller than countdown time set.
		float GamerunTime = 4000;

		if (Integer.valueOf(clock.getValue(GamerunTime))<=0)
			fail();
		else
			assertTrue(true);

		// runTime larger than countdown time set.
		GamerunTime = 8000;
		if (Integer.valueOf(clock.getValue(GamerunTime))<=0)
			assertTrue(true);
		else
			fail();
	}

	public void testNormalClickableButton(){

		button.setNormalClickable();
		assertEquals(true, button.getNormalClickable());
		assertEquals(false, button.getBomb());
		assertEquals(false, button.getSmallest());
		assertEquals(false, button.getHint());
		assertFalse(button.getInt() == -1);

	}

	public void testNormalNotClickableButton(){

		button.setNormalNotClickable();
		assertEquals(false, button.getNormalClickable());
		assertEquals(false, button.getBomb());
		assertEquals(false, button.getSmallest());
		assertEquals(false, button.getHint());
		assertEquals(-1, button.getInt());

	}

	public void testBombButton(){

		button.setBomb();
		assertEquals(false, button.getNormalClickable());
		assertEquals(true, button.getBomb());
		assertEquals(false, button.getSmallest());
		assertEquals(false, button.getHint());
		assertEquals(-1, button.getInt());

	}

	public void testHintButton(){

		button.setHint();
		assertEquals(false, button.getNormalClickable());
		assertEquals(false, button.getBomb());
		assertEquals(false, button.getSmallest());
		assertEquals(true, button.getHint());
		assertEquals(-1, button.getInt());
	}

	public void testSmallestButton(){

		button.setSmallest();
		assertEquals(false, button.getNormalClickable());
		assertEquals(false, button.getBomb());
		assertEquals(true, button.getSmallest());
		assertEquals(false, button.getHint());
		assertEquals(-1, button.getInt());

	}

	public void testGameGrid(){
		
		assertTrue(testgameGrid.size()==35);

		for (Objects_GridButton i : testgameGrid){
			button.setNormalClickable();
			assertEquals(true, button.getNormalClickable());
			assertEquals(false, button.getBomb());
			assertEquals(false, button.getSmallest());
			assertEquals(false, button.getHint());
			assertFalse(button.getInt() == -1);
		}

	}

	public void testButtonHandler(){
		initialise.update(0f);
		int smallestNo = 10;
		for (Objects_GridButton i : testgameGrid){
			if (i.getInt()>=0){
				if (i.getInt() < smallestNo){
					smallestNo = i.getInt();
				}
			}
		}

		/* click clickable and is smallest number */
		for (int i=0 ; i<35 ; i++){
			if (testgameGrid.get(i).getInt()== smallestNo && testgameGrid.get(i).getInt()!= -1 ){

				buttonHandler.Click(i, smallestNo);
				
				assertEquals(false, testgameGrid.get(i).getNormalClickable());
				assertEquals(false, testgameGrid.get(i).getBomb());
				assertEquals(false, testgameGrid.get(i).getSmallest());
				assertEquals(false, testgameGrid.get(i).getHint());
				assertEquals(-1, testgameGrid.get(i).getInt());
				break;
			}
		}
		
		/* click clickable but not smallest number */
		for (int i=0 ; i<35 ; i++){
			if (testgameGrid.get(i).getInt()!= smallestNo && testgameGrid.get(i).getInt()!= -1 ){

				buttonHandler.Click(i, smallestNo);

				assertEquals(true, testgameGrid.get(i).getNormalClickable());
				assertEquals(false, testgameGrid.get(i).getBomb());
				assertEquals(false, testgameGrid.get(i).getSmallest());
				assertEquals(false, testgameGrid.get(i).getHint());
				assertFalse(testgameGrid.get(i).getInt() == -1);
				break;
			}
		}
		
		/* click bomb */
		testgameGrid.get(11).setBomb();
		
		buttonHandler.Click(11, Assets.SENTBOMB); 
		
		assertEquals(false, testgameGrid.get(5).getNormalClickable());
		assertEquals(false, testgameGrid.get(5).getBomb());
		assertEquals(false, testgameGrid.get(5).getSmallest());
		assertEquals(false, testgameGrid.get(5).getHint());
		assertEquals(-1, testgameGrid.get(5).getInt());
		
		assertEquals(false, testgameGrid.get(6).getNormalClickable());
		assertEquals(false, testgameGrid.get(6).getBomb());
		assertEquals(false, testgameGrid.get(6).getSmallest());
		assertEquals(false, testgameGrid.get(6).getHint());
		assertEquals(-1, testgameGrid.get(6).getInt());
		
		assertEquals(false, testgameGrid.get(7).getNormalClickable());
		assertEquals(false, testgameGrid.get(7).getBomb());
		assertEquals(false, testgameGrid.get(7).getSmallest());
		assertEquals(false, testgameGrid.get(7).getHint());
		assertEquals(-1, testgameGrid.get(7).getInt());
		
		assertEquals(false, testgameGrid.get(10).getNormalClickable());
		assertEquals(false, testgameGrid.get(10).getBomb());
		assertEquals(false, testgameGrid.get(10).getSmallest());
		assertEquals(false, testgameGrid.get(10).getHint());
		assertEquals(-1, testgameGrid.get(10).getInt());
		
		assertEquals(false, testgameGrid.get(11).getNormalClickable());
		assertEquals(false, testgameGrid.get(11).getBomb());
		assertEquals(false, testgameGrid.get(11).getSmallest());
		assertEquals(false, testgameGrid.get(11).getHint());
		assertEquals(-1, testgameGrid.get(11).getInt());
		
		assertEquals(false, testgameGrid.get(12).getNormalClickable());
		assertEquals(false, testgameGrid.get(12).getBomb());
		assertEquals(false, testgameGrid.get(12).getSmallest());
		assertEquals(false, testgameGrid.get(12).getHint());
		assertEquals(-1, testgameGrid.get(12).getInt());
		
		assertEquals(false, testgameGrid.get(15).getNormalClickable());
		assertEquals(false, testgameGrid.get(15).getBomb());
		assertEquals(false, testgameGrid.get(15).getSmallest());
		assertEquals(false, testgameGrid.get(15).getHint());
		assertEquals(-1, testgameGrid.get(15).getInt());
		
		assertEquals(false, testgameGrid.get(16).getNormalClickable());
		assertEquals(false, testgameGrid.get(16).getBomb());
		assertEquals(false, testgameGrid.get(16).getSmallest());
		assertEquals(false, testgameGrid.get(16).getHint());
		assertEquals(-1, testgameGrid.get(16).getInt());
		
		assertEquals(false, testgameGrid.get(17).getNormalClickable());
		assertEquals(false, testgameGrid.get(17).getBomb());
		assertEquals(false, testgameGrid.get(17).getSmallest());
		assertEquals(false, testgameGrid.get(17).getHint());
		assertEquals(-1, testgameGrid.get(17).getInt());
		
		/* click hint */
		testgameGrid.get(15).setHint();
		buttonHandler.Click(15, smallestNo);
		assertEquals(true, Assets.glow);
		
		/* click smallest */
		testgameGrid.get(22).setSmallest();
		buttonHandler.Click(22, smallestNo);
		
		for (int i=0 ; i<35 ; i++){
			if (testgameGrid.get(i).getInt()== smallestNo){

				assertEquals(false, testgameGrid.get(i).getNormalClickable());
				assertEquals(false, testgameGrid.get(i).getBomb());
				assertEquals(false, testgameGrid.get(i).getSmallest());
				assertEquals(false, testgameGrid.get(i).getHint());
				assertEquals(-1, testgameGrid.get(i).getInt());
				break;
			}
		}
		
	}
}