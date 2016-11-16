package com.aslamodaya.shapestrike.Screens;

import com.aslamodaya.shapestrike.Objects.Shape;
import com.aslamodaya.shapestrike.ShapeStrike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by Aslam on 08/06/2016.
 */
public class MenuScreen implements Screen {



    private Texture background;
    private Shape blueDiamond;
    private Shape magentaHexagon;
    private Shape redTriangle;
    private ShapeStrike game;
    private float diamondGravity, triangleGravity, hexGravity;
    private Vector2 diamondPos;
    private Vector2 trianglePos;
    private Vector2 hexPos;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private boolean startGamePressed = false;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Button.ButtonStyle buttonStyle;
    private  Button startButton;
    private float buttonPosX, buttonPosY;


    public MenuScreen(ShapeStrike game) {
        this.game = game;
        cam = new OrthographicCamera();

        viewport = new FitViewport(ShapeStrike.WIDTH, ShapeStrike.HEIGHT, cam);
        background = new Texture("background.png");
        stage = new Stage(viewport);

        //instatiate each position shape
        diamondPos = new Vector2(ShapeStrike.WIDTH/2-130, ShapeStrike.HEIGHT/2-70);
        trianglePos = new Vector2((ShapeStrike.WIDTH/2)-35, (ShapeStrike.HEIGHT/2)-70);
        hexPos = new Vector2((ShapeStrike.WIDTH/2)+60, (ShapeStrike.HEIGHT/2)-75);

        //instatiate each shape
        blueDiamond = new Shape("blue diamond.png",diamondPos.x, diamondPos.y, 0.35f);
        redTriangle = new Shape("red triangle.png",trianglePos.x, trianglePos.y,0.35f);
        magentaHexagon = new Shape("magenta hexagon.png",hexPos.x, hexPos.y , 0.38f);


        diamondGravity = diamondPos.y;
        triangleGravity = trianglePos.y;
        hexGravity = hexPos.y;

        skin = new Skin();
        atlas = new TextureAtlas("startbutton.pack");
        skin.addRegions(atlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("startbutton");
        buttonStyle.down = skin.getDrawable("startbuttonpressed");



        startButton = new Button(null, buttonStyle);
        stage.addActor(startButton);
        buttonPosX = ShapeStrike.WIDTH/2 - (startButton.getWidth()/2);
        buttonPosY = ShapeStrike.HEIGHT/2;
        Gdx.input.setInputProcessor(stage);
    }



    public void handleInputs() {
        if(startButton.isPressed())
            startGamePressed = true;
    }


    public void update(float delta) {

        handleInputs();
        if(startGamePressed) {
            diamondGravity -= delta * 1000;
            blueDiamond.setY(diamondGravity);
            if(blueDiamond.getY() < 0) {
                triangleGravity -= delta * 1000;
                redTriangle.setY(triangleGravity);
                blueDiamond.dispose();
            }
            if(redTriangle.getY() < 0) {
                hexGravity -= delta * 1000;
                magentaHexagon.setY(hexGravity);
                redTriangle.dispose();
            }
            if(magentaHexagon.getY() < 0){
                buttonPosX -= delta * 1000;
                magentaHexagon.dispose();

            }
            if(buttonPosX < -100){
                game.setScreen(new PlayScreen(game));
                //this.dispose();
                //game.dispose();
            }

        }
    }



    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(cam.combined);
        //draw all the other textures

        game.batch.begin();
        game.batch.draw(background,0,0, ShapeStrike.WIDTH, ShapeStrike.HEIGHT);
        magentaHexagon.render(game.batch);
        blueDiamond.render(game.batch);
        redTriangle.render(game.batch);
        game.batch.end();
        //draw the start button
        stage.draw();
        startButton.setPosition(buttonPosX, buttonPosY);
    }


    public  void dispose(){
        background.dispose();
        blueDiamond.dispose();
        redTriangle.dispose();
        magentaHexagon.dispose();
        stage.dispose();
        System.out.println("textures disposed");
    }

    @Override
    public void resize(int width, int height) {
       viewport.update(width, height);
    }


    @Override
    public void show() {
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


}
