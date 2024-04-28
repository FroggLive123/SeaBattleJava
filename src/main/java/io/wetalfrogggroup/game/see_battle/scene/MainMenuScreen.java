package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;
import org.w3c.dom.Text;

public class MainMenuScreen implements Screen {

    private final ScreenSelector screenSelector;
    private final Stage stage;
    private final SpriteBatch spriteBatch;

    private Button openGameScreenButton;
    private BitmapFont bitmapFont;

    public MainMenuScreen(
            final ScreenSelector screenSelector,
            final Stage stage,
            final SpriteBatch spriteBatch
    ) {
        this.screenSelector = screenSelector;
        this.stage = stage;
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void show() {
        Skin skin = new Skin();
        skin.add("button_up", new Texture(Gdx.files.classpath("assets/button_up.png")));
        skin.add("button_down", new Texture(Gdx.files.classpath("assets/button_down.png")));

        for (var i = 0; i < 100; i++) {

        }

        bitmapFont = new BitmapFont();

        var style = new ImageTextButton.ImageTextButtonStyle(
                skin.get("button_up", Drawable.class),
                skin.get("button_down", Drawable.class),
                null,
                bitmapFont
        );
        style.fontColor = Color.BLUE;
        openGameScreenButton = new ImageTextButton("Open game screen", style);


        openGameScreenButton.setWidth(200);
        openGameScreenButton.setHeight(300);

        openGameScreenButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                screenSelector.accept(new GameScreen(screenSelector, stage, spriteBatch));
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
