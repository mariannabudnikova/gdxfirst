package com.whimsied.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MyFirstGdxGame implements ApplicationListener {

    private Stage stage;

    @Override
    public void create() {
        stage = new Stage();

        CatActor myActor = new CatActor();
        myActor.SetPosition(stage.getWidth()/2, stage.getHeight()/2);
        myActor.setTouchable(Touchable.enabled);
        stage.addActor(myActor);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
}