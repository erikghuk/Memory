package com.games.memory;

import com.badlogic.gdx.Game;

public class MemoryGame extends Game {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 700;
	
	@Override
	public void create () {
		this.setScreen(new GameMainScreen(this));
	}

	

}
