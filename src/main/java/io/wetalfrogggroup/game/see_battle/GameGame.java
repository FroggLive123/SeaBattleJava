package io.wetalfrogggroup.game.see_battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import io.wetalfrogggroup.game.see_battle.scene.GameScreen;
import io.wetalfrogggroup.game.see_battle.scene.MainMenuScreen;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;

public class GameGame extends Game {

    private Stage stage;
    private Camera camera;

    public GameGame() {
        ScreenSelector.getInstance().onchange(this::onScreenChanged);
    }

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera(800, 400);

        var ss = ScreenSelector.getInstance();
        ss.add("main-menu", new MainMenuScreen(stage));
        ss.add("game", new GameScreen(stage));

        ss.choose("main-menu");
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        camera.update();

        super.render();

        stage.act();
        stage.draw();
    }

    private void onScreenChanged(final Screen screen) {
        if (screen == null) {
            return;
        }
        this.setScreen(screen);
    }
}
