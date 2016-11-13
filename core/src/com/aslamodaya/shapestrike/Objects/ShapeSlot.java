package com.aslamodaya.shapestrike.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Aslam on 27/08/2016.
 */
public class ShapeSlot {

    private Skin skin;
    private TextureAtlas atlas;
    private Button.ButtonStyle buttonStyle;
    private String packName, slotShape;
    private Stage stage;
    private Button button;

    public ShapeSlot(String pack, Stage stage, String slot) {
        this.stage = stage;
        slotShape = slot;
        packName = pack;

        skin = new Skin();
        atlas = new TextureAtlas(packName);
        skin.addRegions(atlas);
        buttonStyle = new TextButton.TextButtonStyle();
        button = new Button(null, buttonStyle);
        setButtonStyle(slotShape);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

    }


    public void setButtonStyle(String slot) {
        buttonStyle.up = skin.getDrawable(slot);

    }

    public Button.ButtonStyle getButtonStyle() {

        return buttonStyle;
    }

    public Stage getStage() {

        return stage;
    }


    public Button getButton() {
        return button;
    }

    public void dispose() {
        atlas.dispose();
        stage.dispose();
        skin.dispose();
    }

}
