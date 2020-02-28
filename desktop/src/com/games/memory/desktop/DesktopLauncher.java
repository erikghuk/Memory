package com.games.memory.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.games.memory.MemoryGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 250;
        config.y = 30;
        config.title = "Memory";
        config.resizable = false;
        config.width = MemoryGame.WIDTH;
        config.height = MemoryGame.HEIGHT;

		new LwjglApplication(new MemoryGame(), config);
	}
}
