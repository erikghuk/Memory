package com.games.memory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MemoryGame extends Game {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 700;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		this.setScreen(new GameMainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}