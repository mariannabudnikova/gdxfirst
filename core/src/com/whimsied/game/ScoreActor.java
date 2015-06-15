package com.whimsied.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by marianna on 6/13/15.
 */
public class ScoreActor extends Label {

    public ScoreActor(CharSequence text, LabelStyle style) {
        super(text, style);
        setColor(Color.GREEN);
        setAlignment(Align.top | Align.right);
    }

}
