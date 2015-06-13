package com.whimsied.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.Random;

public class CatActor extends Actor {
    Texture texture = new Texture(Gdx.files.internal("pusheen.png"));
    private float xMovement = 0;
    private float yMovement = 0;
    public boolean pickedUp = false;

    public CatActor(){

        InputListener processor = new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer)
            {
                CatActor cat = (CatActor) event.getTarget();
                cat.pickedUp = true;
                cat.SetPosition(getX() + x - getWidth() / 2, getY() + y - getHeight() / 2);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                CatActor cat = (CatActor) event.getTarget();
                cat.pickedUp = false;
                cat.DetermineNewDirectionToMove();
            }
        };
        addListener(processor);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta){
        if(!pickedUp){
            SetPosition(getX() + xMovement, getY() + yMovement);
        }
    }

    public void DetermineNewDirectionToMove()
    {
        int directionDegrees = new Random().nextInt(360);
        double directionRadians = directionDegrees * Math.PI / 180;
        yMovement = (float)Math.sin(directionRadians) * 1;
        xMovement = (float) Math.cos(directionRadians) * 1;
    }

    public void SetPosition(float x, float y){
        setX(x);
        setY(y);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

}

