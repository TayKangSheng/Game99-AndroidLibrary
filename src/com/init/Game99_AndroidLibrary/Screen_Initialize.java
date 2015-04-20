package com.init.Game99_AndroidLibrary;

import android.util.Log;

import com.init.framework.Audio;
import com.init.framework.Graphics;
import com.init.framework.Graphics.ImageFormat;
import com.init.framework.Screen;

public class Screen_Initialize extends Screen {
	private NNGame game;
	public Screen_Initialize(NNGame game) {
		super(game);
		this.game = game;
		Log.i("Screen_Initialize", "A_Initialisation_screen");
	}

	/**
	 *  Initialize all assets in Assets.game here.
	 *  After all assets are initialized, screen is set to main menu. 
	 */
	@Override
	public void update(float deltaTime) {
		Assets.runTime=0;
		Assets.socketIO = new SocketIO();

		// Load Music
		Audio music = game.getAudio();
		if (Assets.startScreenBGM==null)
			Assets.startScreenBGM = music.createMusic("audio-Parks-and-Recreation-Theme-Song.mp3");
		if (Assets.gameScreenBGM==null)
			Assets.gameScreenBGM = music.createMusic("audio-noragami-harmony.mp3");
		Assets.victoryBGM = music.createSound("audio-victory.mp3");
		Assets.loseBGM = music.createSound("audio-gameover.mp3");
		Assets.click = music.createSound("audio-click.mp3");
		Assets.popping = music.createSound("audio-popping.mp3");
		Assets.explosion = music.createSound("audio-explosion.mp3");
		Assets.spell = music.createSound("audio-spell.mp3");
		Assets.bubble = music.createSound("audio-bubble.mp3");

		//loads images;
		Graphics g = game.getGraphics();
		Assets.bomb = g.newImage("130-rainbowCrystal.png", ImageFormat.RGB565, false);
		Assets.start = g.newImage("yellowstartbutton.png", ImageFormat.RGB565, false);
		Assets.restart = g.newImage("replaybtn.png", ImageFormat.RGB565, false);
		Assets.readyButton = g.newImage("readybutton.png", ImageFormat.RGB565, false);
		Assets.waitingButton1 = g.newImage("dot1.png", ImageFormat.RGB565, false);
		Assets.waitingButton2 = g.newImage("dot2.png", ImageFormat.RGB565, false);
		Assets.waitingButton3 = g.newImage("dot3.png", ImageFormat.RGB565, false);
		Assets.waitingButton4 = g.newImage("dot4.png", ImageFormat.RGB565, false);
		Assets.space = g.newImage("startpage.png", ImageFormat.RGB565, false);
		Assets.avatar_page = g.newImage("starrynight.png", ImageFormat.RGB565, false);
		Assets.end_page = g.newImage("endpage.png", ImageFormat.RGB565, false);
		Assets.instruction = g.newImage("instruction.png",ImageFormat.RGB565, false);
		Assets.you_lost = g.newImage("youlose.png", ImageFormat.RGB565, false);
		Assets.you_won = g.newImage("youwin.png", ImageFormat.RGB565, false);
		Assets.hint = g.newImage("130-bluecrystal.png",ImageFormat.RGB565, false);
		Assets.smallest = g.newImage("130-hintcrystal.png",ImageFormat.RGB565, false);
		Assets.avatar_page_blinkingstars = g.newImage("starrynight-blinkingstars.png", ImageFormat.RGB565, false);
		Assets.message_good = g.newImage("Message-Good.png", ImageFormat.RGB565, false);
		Assets.message_nice = g.newImage("Message-Nice.png", ImageFormat.RGB565, false);
		Assets.message_cool = g.newImage("Message-Cool.png", ImageFormat.RGB565, false);
		Assets.message_excellent = g.newImage("Message-Excellent.png", ImageFormat.RGB565, false);
		Assets.message_great = g.newImage("Message-Great.png", ImageFormat.RGB565, false);
		Assets.message_keepgoing = g.newImage("Message-KeepGoing.png", ImageFormat.RGB565, false);
		Assets.message_perfect = g.newImage("Message-Perfect.png", ImageFormat.RGB565, false);
		Assets.message_wrong = g.newImage("Message-Wrong.png", ImageFormat.RGB565, false);
		Assets.message_stillwrong = g.newImage("Message-StillWrong.png", ImageFormat.RGB565, false);
		Assets.message_omg = g.newImage("Message-OMG.png", ImageFormat.RGB565, false);
		Assets.message_canyounot = g.newImage("Message-CanYouNot.png", ImageFormat.RGB565, false);
		Assets.message_lastwarning = g.newImage("Message-LastWarning.png", ImageFormat.RGB565, false);
		Assets.message_nomorechance = g.newImage("Message-NoMoreChance.png", ImageFormat.RGB565, false);
		Assets.message_kthxbye = g.newImage("Message-KThxBye.png", ImageFormat.RGB565, false);
		Assets.message_faint = g.newImage("Message-Faint.png", ImageFormat.RGB565, false);
		Assets.powerupinstruction = g.newImage("powerupinstruction.png", ImageFormat.RGB565, false);


		//let's go to the next screen;
		game.setScreen(new Screen_First(game));
	}

	@Override
	public void paint(float deltaTime) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
