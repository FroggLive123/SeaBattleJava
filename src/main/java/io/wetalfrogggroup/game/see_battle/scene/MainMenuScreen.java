package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainMenuScreen extends BasicScreen {

    private final Skin atlas;

    private BitmapFont bitmapFont;

    public MainMenuScreen() {
        this.atlas = new Skin();
        atlas.add("button_up", new Texture(Gdx.files.classpath("assets/button_up.png")));
        atlas.add("button_down", new Texture(Gdx.files.classpath("assets/button_down.png")));
    }

    @Override
    public void show() {
        if (Gdx.input.getInputProcessor() != stage) {
            Gdx.input.setInputProcessor(stage);
        }

        var buttonsGroup = new Group();
        stage.addActor(buttonsGroup);

        buttonsGroup.setWidth(170);
        buttonsGroup.setPosition(300, 300);

        bitmapFont = new BitmapFont();

        var titleFont = new BitmapFont();
        var title = new Label("Sea Battle", new Label.LabelStyle(titleFont, Color.WHITE));
        title.setPosition(50, 0);
        buttonsGroup.addActor(title);

        var style = new TextButton.TextButtonStyle(
                atlas.get("button_up", Drawable.class),
                null,
                null,
                bitmapFont
        );
        var findGameButton = new TextButton("Find game", style);
        findGameButton.setWidth(buttonsGroup.getWidth());
        findGameButton.setHeight(50);
        findGameButton.setPosition(0, title.getY() - 90);

        findGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                System.out.println("Open search session screen");
                ScreenSelector.getInstance().choose("search-session");
            }
        });

        buttonsGroup.addActor(findGameButton);

        var settingsButton = new TextButton("Settings", style);
        settingsButton.setWidth(buttonsGroup.getWidth());
        settingsButton.setHeight(50);

        settingsButton.setPosition(0, findGameButton.getY() - 60);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                //                ScreenSelector.getInstance().choose("settings");
            }
        });

        buttonsGroup.addActor(settingsButton);

        var exitButton = new TextButton("Exit", style);
        exitButton.setWidth(buttonsGroup.getWidth());
        exitButton.setHeight(50);

        exitButton.setPosition(0, settingsButton.getY() - 60);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                Gdx.app.exit();
            }
        });

        buttonsGroup.addActor(exitButton);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
    }
}
