package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final Stage stage;

    public GameScreen() {
        this.stage = new Stage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        var font = new BitmapFont();
        font.setColor(Color.WHITE);
        var text = new TextArea(
                "Game screen",
                new TextField.TextFieldStyle(font, Color.WHITE, null, null, null)
        );
        text.setPosition(100, 100);
        stage.addActor(text);
    }

    @Override
    public void render(final float delta) {
        ScreenUtils.clear(Color.BLACK);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
