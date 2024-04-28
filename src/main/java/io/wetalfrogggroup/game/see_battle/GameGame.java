package io.wetalfrogggroup.game.see_battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import io.wetalfrogggroup.game.see_battle.scene.MainMenuScreen;

public class GameGame extends Game {

    private Stage stage;
    private Camera camera;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera(800, 400);
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        this.setScreen(new MainMenuScreen(this::setScreen, stage, spriteBatch));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        super.render();

        stage.act();
        stage.draw();

        spriteBatch.end();
    }
}
