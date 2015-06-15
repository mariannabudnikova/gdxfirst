package com.whimsied.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class CatActor extends Actor {
    Texture texture = new Texture(Gdx.files.internal("pusheen2.png"));

    private float xMovement = 0;
    private float yMovement = 0;
    public boolean isEscaping = false;
    private int secondsToWaitBeforeEscape;
    private int MAX_SECONDS_TO_WAIT_BEFORE_ESCAPE = 10;
    private int MIN_SECONDS_TO_WAIT_BEFORE_ESCAPE = 2;

    public CatActor(){

        DetermineNewDirectionToMove();
        CalculateSecondsToWaitBeforeEscaping();
        InputListener processor = new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer)
            {
                CatActor cat = (CatActor) event.getTarget();
                cat.isEscaping = true;
                cat.SetPosition(getX() + x - getWidth() / 2, getY() + y - getHeight() / 2);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                CatActor cat = (CatActor) event.getTarget();
                cat.WaitBeforeEscaping();
                cat.DetermineNewDirectionToMove();
            }
        };
        addListener(processor);

    }

    private void CalculateSecondsToWaitBeforeEscaping(){
        secondsToWaitBeforeEscape = new Random().nextInt(MAX_SECONDS_TO_WAIT_BEFORE_ESCAPE-MIN_SECONDS_TO_WAIT_BEFORE_ESCAPE) + MIN_SECONDS_TO_WAIT_BEFORE_ESCAPE;
    }

    public void WaitBeforeEscaping(){

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                isEscaping = false;
            }
        },
        secondsToWaitBeforeEscape);
    }

    @Override
    public float getWidth(){
        return texture.getWidth();
    }

    @Override
    public float getHeight(){
        return texture.getHeight();
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta){
        if(!isEscaping) {
            SetPosition(getX() + xMovement, getY() + yMovement);
        }
    }

    public boolean IsOffScreen(){
        boolean cutOffY = getStage().getHeight() < getY() || 0 > getY() + getHeight();
        boolean cutOffX = getStage().getWidth() < getX() || 0 > getX() + getWidth();
        return cutOffX || cutOffY;
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

