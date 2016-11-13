package com.aslamodaya.shapestrike.desktop;

import com.aslamodaya.shapestrike.ShapeStrike;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		 config.title = ShapeStrike.TITLE;
		 config.width = ShapeStrike.WIDTH;
		 config.height = ShapeStrike.HEIGHT;
		new LwjglApplication(new ShapeStrike(), config);
	}
}
