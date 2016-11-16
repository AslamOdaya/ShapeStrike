package com.aslamodaya.shapestrike;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.aslamodaya.shapestrike.Screens.MenuScreen;

public class ShapeStrike extends Game {
    public SpriteBatch batch;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Shape Strike";
    public MenuScreen menu;

    @Override
    public void create() {
        batch = new SpriteBatch();
        menu = new MenuScreen(this);

        setScreen(menu);

    }

    @Override
    public void render() {

        super.render();

    }
}
