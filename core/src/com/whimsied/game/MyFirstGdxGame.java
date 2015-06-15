package com.whimsied.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Iterator;

public class MyFirstGdxGame implements ApplicationListener {

    private Stage stage;

    private ArrayList<CatActor> cats;

    ScoreActor scoreLabel;

    private int STARTING_NUMBER_OF_CATS = 3;

    @Override
    public void create() {
        CreateStage();
        CreateBasket();
        CreateCats();
        CreateScoreLabel();
        PrepareToAddNewCatsAtIntervals();
    }

    private void CreateStage() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    private void PrepareToAddNewCatsAtIntervals() {
        float delay = 2; // seconds
        float interval = 10;

        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               CreateCat();
                               UpdateScoreText();
                           }
                       },
                delay,
                interval);
    }

    private void CreateCats() {
        cats = new ArrayList<CatActor>();
        for (int i = 0; i<STARTING_NUMBER_OF_CATS; i++){
            CreateCat();
        }
    }

    private BasketActor CreateBasket(){
        BasketActor basket = new BasketActor();
        basket.setX(stage.getWidth() / 2 - basket.getWidth() / 2);
        basket.setY(stage.getHeight() / 2 - basket.getHeight() / 2);
        stage.addActor(basket);
        return basket;
    }

    private CharSequence GetScoreLabel(){
        return "Score: " + cats.size();
    }

    private int UpdateScoreText(){
        scoreLabel.setText(GetScoreLabel());
        return cats.size();
    }

    private ScoreActor CreateScoreLabel() {
        scoreLabel = new ScoreActor(GetScoreLabel(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel.setX(80);
        scoreLabel.setY(50);
        stage.addActor(scoreLabel);
        return scoreLabel;
    }

    private CatActor CreateCat(){
        CatActor cat = new CatActor();
        cat.SetPosition(stage.getWidth() / 2 - cat.getWidth() / 2, stage.getHeight() / 2 - cat.getHeight() / 2);
        cat.setTouchable(Touchable.enabled);
        cats.add(cat);
        stage.addActor(cat);
        return cat;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RemoveEscapedCats();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void RemoveEscapedCats() {
        Iterator<CatActor> iterator = cats.iterator();
        while (iterator.hasNext()){
            CatActor cat = iterator.next();
            if (cat.IsOffScreen()) {
                iterator.remove();
                UpdateScoreText();
            }
        }
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
    public void dispose() {
    }
}