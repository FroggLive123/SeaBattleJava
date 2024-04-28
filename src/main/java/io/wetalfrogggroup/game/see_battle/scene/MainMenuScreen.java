package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;

public class MainMenuScreen implements Screen {

    private final ScreenSelector screenSelector;
    private final Stage stage;
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;

    private Button openGameScreenButton;

    public MainMenuScreen(
            final ScreenSelector screenSelector,
            final Stage stage,
            final SpriteBatch spriteBatch,
            final BitmapFont bitmapFont
    ) {
        this.screenSelector = screenSelector;
        this.stage = stage;
        this.spriteBatch = spriteBatch;
        this.bitmapFont = bitmapFont;
    }

    @Override
    public void show() {
        openGameScreenButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                screenSelector.accept(new GameScreen(screenSelector, stage, spriteBatch, bitmapFont));
            }
        });
        stage.addActor(openGameScreenButton);
    }

    @Override
    public void render(final float delta) {
        openGameScreenButton.draw(spriteBatch, 1f);
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
