package com.aslamodaya.shapestrike.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Aslam on 09/06/2016.
 */
public class ShapeObject {

    private Texture texture;
    private float x;
    private float y;
    private float scaleRatio;


    public ShapeObject(String textureName, float x, float y, float scaleRatio) {
        texture = new Texture(textureName);
        this.x = x;
        this.y = y;
        this.scaleRatio = scaleRatio;


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

    public void setY(float y)
    {
        this.y = y;
    }

    public float getY()
    {
        return y;
    }

    public float getX()
    {
        return x;
    }

}

