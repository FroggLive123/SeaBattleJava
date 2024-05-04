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
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainMenuScreen implements Screen {

    private final Stage stage;

    private Button openGameScreenButton;
    private BitmapFont bitmapFont;

    @Override
    public void show() {
        Skin skin = new Skin();
        skin.add("button_up", new Texture(Gdx.files.classpath("assets/button_up.png")));
        skin.add("button_down", new Texture(Gdx.files.classpath("assets/button_down.png")));

        bitmapFont = new BitmapFont();

        var style = new ImageTextButton.ImageTextButtonStyle(
                skin.get("button_up", Drawable.class),
                null,
                null,
                bitmapFont
        );
        style.fontColor = Color.BLUE;
        openGameScreenButton = new ImageTextButton("Open game screen", style);

        openGameScreenButton = new Button(new Button.ButtonStyle(
                skin.get("button_up", Drawable.class),
                null,
                null
        ));

        openGameScreenButton.setWidth(200);
        openGameScreenButton.setHeight(300);

        openGameScreenButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
            }
        });
        stage.addActor(openGameScreenButton);
    }

    @Override
    public void render(final float delta) {
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
