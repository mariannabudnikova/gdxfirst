package com.whimsied.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by marianna on 6/14/15.
 */
public class BasketActor extends Actor {
    Texture texture = new Texture(Gdx.files.internal("basket.png"));

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    @Override
    public float getWidth(){
        return texture.getWidth();
    }

    @Override
    public float getHeight(){
        return texture.getHeight();
    }
}
