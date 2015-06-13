package com.whimsied.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.whimsied.game.MyFirstGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "camera";
		cfg.width = 500;
		cfg.height = 500;
		new LwjglApplication(new MyFirstGdxGame(), cfg);
	}
}
