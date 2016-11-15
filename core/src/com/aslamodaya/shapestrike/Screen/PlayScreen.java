package com.aslamodaya.shapestrike.Screen;

import com.aslamodaya.shapestrike.Objects.Shape;
import com.aslamodaya.shapestrike.Objects.ShapeSlot;
import com.aslamodaya.shapestrike.ShapeStrike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;


import java.util.Random;

/**
 * Created by Aslam on 15/06/2016.
 */


public class PlayScreen implements Screen {

    private static final int INDEX_MAX = 3;
    private Texture background, slotBackground;
    private ShapeStrike game;
    private Shape triangle, diamond, hexagon;
    private final Vector2 TRIANGLE_POS, DIAMOND_POS, HEX_POS;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private float triangleGravity, diamondGravity, hexGravity;
    private Random random;
    private boolean renderObjects[], isRunOnceDia, isRunOnceTri, isRunOnceHex, isScoreShowable;
    private int renderIndex, diamondIndex, triangleIndex, hexIndex;
    private int previousRandNum, score;
    private String[] diamondColourArr, triangleColourArr, hexColourArr, diamondSlotColourArr,
            triangleSlotColourArr, hexSlotColourArr, colours;
    private String diamondColour, hexColour, triangleColour, diamondSlotName, triangleSlotName, hexSlotName;
    private Stage stage;
    private Button diamondButton, triangleButton, hexButton;
    private ShapeSlot diamondSlot, triangleSlot, hexSlot;
    private Rectangle diamondBox, diamondSltBox, triangleBox, triangleSltBox, hexBox, hexSltBox;
    private String scoreStr;
    private BitmapFont font;
    private final String RED_COLOUR = "red";
    private final String GREEN_COLOUR = "green";
    private final String BLUE_COLOUR = "blue";
    private final String MAGENTA_COLOUR = "magenta";
    private final String DIAMOND_FILE_STRING = "diamond.png";
    private final String TRIANGLE_FILE_STRING = "triangle.png";
    private final String HEXAGON_FILE_STRING = "hexagon.png";








    public PlayScreen(ShapeStrike game) {
        this.game = game;

        random = new Random();

        //set the camera position
        cam = new OrthographicCamera();
        viewport = new FitViewport(ShapeStrike.WIDTH, ShapeStrike.HEIGHT, cam);
        cam.translate(ShapeStrike.WIDTH / 2, ShapeStrike.HEIGHT / 2);

        stage = new Stage(viewport); // pass in "viewport" to resize objects on different resolution


        background = new Texture("background.png");
        slotBackground = new Texture("slotbg.png");
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"));


        // three shape positions
        DIAMOND_POS = new Vector2((ShapeStrike.WIDTH / 2) - 200, ShapeStrike.HEIGHT + 30);
        TRIANGLE_POS = new Vector2(ShapeStrike.WIDTH / 2 - 30, ShapeStrike.HEIGHT + 30);
        HEX_POS = new Vector2((ShapeStrike.WIDTH) - 100, ShapeStrike.HEIGHT + 30);

        colours = new String[4];
        colours[0] = RED_COLOUR;
        colours[1] = GREEN_COLOUR;
        colours[2] = BLUE_COLOUR;
        colours[3] = MAGENTA_COLOUR;


        // colour names into arrays.
        diamondColourArr = new String[4];
        diamondColourArr[0] = RED_COLOUR + " "+DIAMOND_FILE_STRING ;
        diamondColourArr[1] = GREEN_COLOUR + " "+DIAMOND_FILE_STRING;
        diamondColourArr[2] = BLUE_COLOUR+ " "+DIAMOND_FILE_STRING;
        diamondColourArr[3] = MAGENTA_COLOUR+" "+DIAMOND_FILE_STRING;

        triangleColourArr = new String[4];
        triangleColourArr[0] = RED_COLOUR+" "+TRIANGLE_FILE_STRING;
        triangleColourArr[1] = GREEN_COLOUR+" "+TRIANGLE_FILE_STRING;
        triangleColourArr[2] = BLUE_COLOUR+" "+TRIANGLE_FILE_STRING;
        triangleColourArr[3] = MAGENTA_COLOUR+" "+TRIANGLE_FILE_STRING;

        hexColourArr = new String[4];
        hexColourArr[0] = RED_COLOUR+" "+HEXAGON_FILE_STRING;
        hexColourArr[1] = GREEN_COLOUR+" "+HEXAGON_FILE_STRING;
        hexColourArr[2] = BLUE_COLOUR+" "+HEXAGON_FILE_STRING;
        hexColourArr[3] = MAGENTA_COLOUR+" "+HEXAGON_FILE_STRING;


        //colour names for slots
        diamondSlotColourArr = new String[4];
        diamondSlotColourArr[0] = RED_COLOUR+DIAMOND_FILE_STRING;
        diamondSlotColourArr[1] = GREEN_COLOUR+DIAMOND_FILE_STRING;
        diamondSlotColourArr[2] = BLUE_COLOUR+DIAMOND_FILE_STRING;
        diamondSlotColourArr[3] = MAGENTA_COLOUR+DIAMOND_FILE_STRING;

        triangleSlotColourArr = new String[4];
        triangleSlotColourArr[0] = RED_COLOUR+TRIANGLE_FILE_STRING;
        triangleSlotColourArr[1] = GREEN_COLOUR+TRIANGLE_FILE_STRING;
        triangleSlotColourArr[2] = BLUE_COLOUR+TRIANGLE_FILE_STRING;
        triangleSlotColourArr[3] = MAGENTA_COLOUR+TRIANGLE_FILE_STRING;

        hexSlotColourArr = new String[4];
        hexSlotColourArr[0] = RED_COLOUR+HEXAGON_FILE_STRING;
        hexSlotColourArr[1] = GREEN_COLOUR+HEXAGON_FILE_STRING;
        hexSlotColourArr[2] = BLUE_COLOUR+HEXAGON_FILE_STRING;
        hexSlotColourArr[3] = MAGENTA_COLOUR+HEXAGON_FILE_STRING;


        //choose a random colour from the array, also set it as current colour

        triangleColour = triangleColourArr[chooseRandomNum(4)];
        hexColour = hexColourArr[chooseRandomNum(4)];
        diamondColour = diamondColourArr[chooseRandomNum(4)];


        //choose random slot colour
        diamondIndex = chooseRandomNum(4);
        System.out.println("diamond index " + diamondIndex);
        diamondSlotName = diamondSlotColourArr[diamondIndex];
        triangleIndex = chooseRandomNum(4);
        System.out.println("triangle index " + triangleIndex);
        triangleSlotName = triangleSlotColourArr[triangleIndex];
        hexIndex = chooseRandomNum(4);
        System.out.println("hex index " + hexIndex);
        hexSlotName = hexSlotColourArr[hexIndex];


        //instatiate all three shapes
        diamond = new Shape(diamondColour, DIAMOND_POS.x, DIAMOND_POS.y, 0.35f);
        triangle = new Shape(triangleColour, TRIANGLE_POS.x, TRIANGLE_POS.y, 0.35f);
        hexagon = new Shape(hexColour, HEX_POS.x, HEX_POS.y, 0.35f);


        //set the original position of the objects
        diamondGravity = DIAMOND_POS.y;
        triangleGravity = TRIANGLE_POS.y;
        hexGravity = HEX_POS.y;

        renderObjects = new boolean[3];


        //assign randomm index number from 0-2;
        renderIndex = chooseRandomNum(3);

        //set that random index to to true to render a random object.
        renderObjects[renderIndex] = true;

        //dimensions for slots
        int slotWidth = 110;
        int slotHeight = slotWidth;

        //diamond slots
        diamondSlot = new ShapeSlot("diamondslots.pack", stage, diamondSlotName);
        diamondButton = diamondSlot.getButton();

        //diamond slot transformation
        diamondButton.setPosition(20, 0);
        diamondButton.setWidth(slotWidth);
        diamondButton.setHeight(slotHeight);

        //triangle slot
        triangleSlot = new ShapeSlot("triangleslot.pack", stage, triangleSlotName);
        triangleButton = triangleSlot.getButton();

        //triangle slot transformation
        triangleButton.setPosition(188, 0);
        triangleButton.setWidth(slotWidth);
        triangleButton.setHeight(slotHeight);

        //hex slot
        hexSlot = new ShapeSlot("hexagonslot.pack", stage, hexSlotName);
        hexButton = hexSlot.getButton();
        //hexagon slot transformation
        hexButton.setPosition(355, -10);
        hexButton.setWidth(125);
        hexButton.setHeight(125);

        //diamond and diamond slot collision boxes
        diamondBox = new Rectangle(diamond.getX(), diamond.getY(), diamond.getWidth(), diamond.getHeight());
        diamondSltBox = new Rectangle(diamondButton.getX(), diamondButton.getY(),
                diamondButton.getWidth(), diamondButton.getHeight());


        //triangle and triangle slot collision boxes
        triangleBox = new Rectangle(triangle.getX(), triangle.getY(), triangle.getWidth(), triangle.getHeight());
        triangleSltBox = new Rectangle(triangleButton.getX(), triangleButton.getY(),
                triangleButton.getWidth(), triangleButton.getHeight());


        //hexagon and hexagon slot collision detection
        hexBox = new Rectangle(hexagon.getX(), hexagon.getY(), hexagon.getWidth(), hexagon.getHeight());
        hexSltBox = new Rectangle(hexButton.getX(), hexButton.getY(), hexButton.getWidth(), hexButton.getHeight());


        //score system
        score = 0;


    }

    public void addScore() {
        score++;



    }


    //return a random number
    public int chooseRandomNum(int maxNum) {
        int randomNum = random.nextInt(maxNum);

        if (previousRandNum == randomNum) {
            randomNum = chooseRandomNum(maxNum);

        }

        previousRandNum = randomNum;

        return randomNum;
    }


    public void update(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int velocity = 300;

        //reset positon of collision boxes as shapes move down
        diamondBox.setPosition(diamond.getX(), diamond.getY() + 100);
        triangleBox.setPosition(triangle.getX(), triangle.getY() + 100);
        hexBox.setPosition(hexagon.getX(), hexagon.getY() + 100);


        boolean isDiamondOverlapping = diamondBox.overlaps(diamondSltBox);
        boolean isTriangleOverlapping = triangleBox.overlaps(triangleSltBox);
        boolean isHexOverlapping = hexBox.overlaps(hexSltBox);


        //let object fall if it is rendered.
        if (renderObjects[0]) {

            //decrease the position and set the position for each frame.
            diamondGravity -= delta * velocity;
            diamond.setY(diamondGravity);

            /**
             * when the shape overlaps the slot (collides), derender the object, reset position to top
             * and choose a random colour for the next object that will be rendered.
             * Finally render a random object.
             * CURRENTLY THERE IS A  BUG WHERE SHAPE IS SHOWN FOR A SPLIT SECOND FOR THE LAST COLLIDED SHAPE AFTER NEW SHAPE IS RENDERED
             */


            if (isDiamondOverlapping && !isRunOnceDia) {
                //split the texture name to only get colour i.e "red diamond";
                String[] colour = diamondColour.split(" ");

                isRunOnceDia = true;
                //diamond colour is the same as the shape. checks if it both shape names contain the same colour

                if (diamondSlotName.contains(colour[0])) {

                    diamondGravity = DIAMOND_POS.y;
                    renderObjects[0] = false;
                    renderIndex = chooseRandomNum(3);
                    renderObjects[renderIndex] = true;
                    addScore();


                }
                //if it's not same, show game over screen.
                else {

                    game.setScreen(new GameOverScreen(game));
                    this.dispose();
                }
            }
        }


        //let object fall if it is rendered.
        if (renderObjects[1]) {
            //decrease position of triangle and set the new position for each frame.
            triangleGravity -= delta * velocity;
            triangle.setY(triangleGravity);


            //triangle colour is the same as the shape. checks if it both shape names contain the same colour
            if (isTriangleOverlapping && !isRunOnceTri) {
                isRunOnceTri = true;


                //split the texture  to only get colour i.e "red diamond";
                String colour[] = triangleColour.split(" ");
                if (triangleSlotName.contains(colour[0])) {


                    triangleGravity = TRIANGLE_POS.y;
                    renderObjects[1] = false;
                    renderIndex = chooseRandomNum(3);
                    renderObjects[renderIndex] = true;
                    addScore();


                }
                //if it's not same, show game over screen.
                else {

                    game.setScreen(new GameOverScreen(game));
                    this.dispose();

                }
            }
        }
        //let object fall if it is rendered.
        if (renderObjects[2]) {

            hexGravity -= delta * velocity;
            hexagon.setY(hexGravity);


            if (isHexOverlapping && !isRunOnceHex) {
                String[] colour = hexColour.split(" ");
                isRunOnceHex = true;
                if (hexSlotName.contains(colour[0])) {


                    hexGravity = HEX_POS.y;
                    renderObjects[2] = false;
                    renderIndex = chooseRandomNum(3);
                    renderObjects[renderIndex] = true;
                    addScore();


                } else {

                    game.setScreen(new GameOverScreen(game));
                    this.dispose();
                }
            }

        }

        //change slot color when slot is pressed. "justTocuhed" is used to ensure its only ran once.
        if (Gdx.input.justTouched()) {

            //select a random colour for the slot every time it's pressed.
            if (diamondButton.isPressed()) {
                System.out.println(diamondIndex);
                if (diamondIndex < INDEX_MAX) {
                    diamondSlotName = diamondSlotColourArr[++diamondIndex];
                } else {
                    diamondIndex = 0;
                    diamondSlotName = diamondSlotColourArr[diamondIndex];
                }

                if (diamondSlotName != null) {
                    diamondSlot.setButtonStyle(diamondSlotName);
                }
            }

            if (triangleButton.isPressed()) {
                System.out.println(triangleIndex);
                if (triangleIndex < INDEX_MAX) {

                    triangleSlotName = triangleSlotColourArr[++triangleIndex];
                } else {
                    triangleIndex = 0;
                    triangleSlotName = triangleSlotColourArr[triangleIndex];
                }

                if (triangleSlotName != null) {
                    triangleSlot.setButtonStyle(triangleSlotName);
                }
            }

            if (hexButton.isPressed()) {
                System.out.println(hexIndex);
                if (hexIndex < INDEX_MAX) {
                    hexSlotName = hexSlotColourArr[++hexIndex];
                } else {
                    hexIndex = 0;
                    hexSlotName = hexSlotColourArr[hexIndex];
                }

                if (hexSlotName != null) {
                    hexSlot.setButtonStyle(hexSlotName);
                }

            }

        }
    }


    @Override
    public void render(float delta) {
        update(delta);
        scoreStr = String.format("%01d", score);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(cam.combined);


        //draw background, slot background, score font
        game.batch.begin();
        game.batch.draw(background, 0, 0, ShapeStrike.WIDTH, ShapeStrike.HEIGHT);
        font.draw(game.batch, scoreStr, 20, ShapeStrike.HEIGHT - 20);
        game.batch.draw(slotBackground, -20, -160);
        game.batch.end();

        stage.draw();


        game.batch.begin();
        //render if the value in the index is true (for diamond)
        if (renderObjects[0]) {

            diamond.render(game.batch);

            /*if the position is reset, choose a random colour for the next shape
            * condition set so that it doesn't reset when it collides (giving unwanted collision detection)
            * */
            if (diamondGravity < DIAMOND_POS.y && diamondGravity > DIAMOND_POS.y - 30) {
                isRunOnceDia = false;
                diamondColour = diamondColourArr[chooseRandomNum(4)];
                diamond.setTexture(diamondColour);


            }
        }

        //render if the value in the index is true (for triangle)
        else if (renderObjects[1]) {
            triangle.render(game.batch);

            if (triangleGravity < TRIANGLE_POS.y && triangleGravity > TRIANGLE_POS.y - 30) {
                isRunOnceTri = false;
                triangleColour = triangleColourArr[chooseRandomNum(4)];
                triangle.setTexture(triangleColour);


            }
        }
        //render if the value in the index is true (for hexagon)
        else if (renderObjects[2]) {
            hexagon.render(game.batch);


            if (hexGravity < HEX_POS.y && hexGravity > HEX_POS.y - 30) {
                isRunOnceHex = false;
                hexColour = hexColourArr[chooseRandomNum(4)];
                hexagon.setTexture(hexColour);

            }
        }


        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void dispose() {

        background.dispose();
        diamond.dispose();
        triangle.dispose();
        hexagon.dispose();
        stage.dispose();
        game.dispose();
    }

    //UNUSED INHERITED METHODS
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
    public void show() {

    }
}
