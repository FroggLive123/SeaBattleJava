package io.wetalfrogggroup.game.see_battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import io.wetalfrogggroup.game.see_battle.scene.MainMenuScreen;
import io.wetalfrogggroup.game.see_battle.scene.SearchSessionScreen;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;

public class GameGame extends Game {

    public GameGame() {
        ScreenSelector.getInstance().onchange(this::onScreenChanged);
    }

    @Override
    public void create() {

        var ss = ScreenSelector.getInstance();
        ss.add("main-menu", new MainMenuScreen());
        ss.add("search-session", new SearchSessionScreen());

        ss.choose("main-menu");
    }

    private void onScreenChanged(final Screen screen) {
        if (screen == null) {
            return;
        }

        this.setScreen(screen);
    }
}
