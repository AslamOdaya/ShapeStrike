package com.aslamodaya.shapestrike.Objects;


import com.aslamodaya.shapestrike.ShapeStrike;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Aslam on 09/06/2016.
 */
public class Shape {

    public enum ShapeType {
        DIAMOND, TRIANGLE, HEXAGON

    }

    private Texture texture;
    private float x;
    private float y;
    private float scaleRatio;
    private ShapeType shapeType;

    private final Vector2 TRIANGLE_POS = new Vector2((ShapeStrike.WIDTH / 2) - 200, ShapeStrike.HEIGHT + 30);
    private final Vector2 DIAMOND_POS = new Vector2(ShapeStrike.WIDTH / 2 - 30, ShapeStrike.HEIGHT + 30);
    private final Vector2 HEX_POS = new Vector2((ShapeStrike.WIDTH) - 100, ShapeStrike.HEIGHT + 30);


    public Shape(String textureName, float x, float y, float scaleRatio) {


        texture = new Texture(textureName);
        this.x = x;
        this.y = y;
        this.scaleRatio = 0.35f;


    }


    public void selectShapeType() {
        switch(shapeType){
            case DIAMOND:

        }

    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, (texture.getWidth() * scaleRatio), (texture.getHeight() * scaleRatio));

    }

    public void setTexture(String textureName) {

        texture = new Texture(textureName);
    }

    public void dispose() {

        texture.dispose();
    }

    public float getWidth() {

        return texture.getWidth();
    }

    public float getHeight() {

        return texture.getHeight();
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

}

