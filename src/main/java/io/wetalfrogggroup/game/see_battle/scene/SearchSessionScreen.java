package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SearchSessionScreen extends BasicScreen {

    public SearchSessionScreen() {
    }

    @Override
    public void show() {
        var font = new BitmapFont();
        var text = new Label("Search session...", new Label.LabelStyle(font, Color.WHITE));
        text.setPosition(300, 300);

        stage.addActor(text);
    }
}
