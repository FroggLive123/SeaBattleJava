package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameScreen implements Screen {

    private final ScreenSelector screenSelector;
    private final Stage stage;
    private final SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    @Override
    public void show() {
        bitmapFont = new BitmapFont();
    }

    @Override
    public void render(final float delta) {
        bitmapFont.draw(spriteBatch, "Game screen", 400, 200);
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
