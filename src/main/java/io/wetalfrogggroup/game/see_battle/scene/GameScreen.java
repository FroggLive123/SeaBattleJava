package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GameScreen extends BasicScreen {

    private final String sessionKey;

    public GameScreen(final String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public void show() {
        super.show();

        var font = new BitmapFont();
        font.setColor(Color.WHITE);
        var text = new TextArea(
                "Game screen",
                new TextField.TextFieldStyle(font, Color.WHITE, null, null, null)
        );
        text.setPosition(100, 100);
        stage.addActor(text);
    }
}
