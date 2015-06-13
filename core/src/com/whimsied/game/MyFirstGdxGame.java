package com.whimsied.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class MyFirstGdxGame implements ApplicationListener, InputProcessor {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private float posX, posY;
    private float xMovement, yMovement;
    private boolean catPickedUp = false;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("pusheen.png"));
        sprite = new Sprite(texture);
        posX = w/2 - sprite.getWidth()/2;
        posY = h/2 - sprite.getHeight()/2;
        sprite.setPosition(posX, posY);

        float delay = 0f;
        float amtOfSec = 0.1f;
        Timer catTimer = new Timer();
        catTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (!catPickedUp) {
                    moveCat();
                }
            }
        }
                , delay        //    (delay)
                , amtOfSec     //    (seconds))
        );

        Gdx.input.setInputProcessor(this);
        DetermineNewDirectionToMove();
    }

    public void DetermineNewDirectionToMove()
    {
        int directionDegrees = new Random().nextInt(360);
        double directionRadians = directionDegrees * Math.PI / 180;

        yMovement = (float)Math.sin(directionRadians) * 10;
        xMovement = (float) Math.cos(directionRadians) * 10;
    }

    public void moveCat()
    {
        posX += xMovement;
        posY += yMovement;
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprite.setPosition(posX, posY);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    private boolean touchedTheCat(int touchX, int touchY){
//        System.out.println("Sprite origin: x=" + sprite.getX() + ", y=" +sprite.getY());
//        System.out.println("Touch origin: x=" + touchX + ", y=" + touchY);
//        System.out.println("Sprite width=" + sprite.getWidth() + ", height=" + sprite.getHeight());
//        System.out.println("Difference x=" + (touchX - sprite.getOriginX()));
//        System.out.println("Difference y=" + (touchY - sprite.getOriginY()));
        return (touchX - sprite.getX() < sprite.getWidth() &&
                Gdx.graphics.getHeight() - touchY - sprite.getY() < sprite.getHeight());
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (touchedTheCat(screenX, screenY)) {
            catPickedUp = false;
            DetermineNewDirectionToMove();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (touchedTheCat(screenX, screenY)) {
            catPickedUp = true;
            posX = screenX - sprite.getWidth() / 2;
            posY = Gdx.graphics.getHeight() - screenY - sprite.getHeight() / 2;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
