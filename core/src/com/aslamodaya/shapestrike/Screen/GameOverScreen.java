package com.aslamodaya.shapestrike.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.aslamodaya.shapestrike.ShapeStrike;

/**
 * Created by Aslam on 29/09/2016.
 */

public class GameOverScreen implements Screen {
    private ShapeStrike game;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Stage stage;
    private Texture background;
    private Label gameOver;

    public GameOverScreen(ShapeStrike game) {
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(ShapeStrike.WIDTH, ShapeStrike.HEIGHT);
        cam.translate(ShapeStrike.WIDTH / 2, ShapeStrike.HEIGHT / 2);
        stage = new Stage(viewport);
        background = new Texture("background.png");
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        gameOver = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOver.setScale(400, 400);

        table.add(gameOver).expandX().padTop(ShapeStrike.HEIGHT / 2);
        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0, ShapeStrike.WIDTH, ShapeStrike.HEIGHT);
        game.batch.end();

        stage.draw();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PlayScreen(game));
            this.dispose();
        }


    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
        background.dispose();
        stage.dispose();
    }
}
