package io.wetalfrogggroup.game.see_battle.util;

import com.badlogic.gdx.Screen;
import io.wetalfrogggroup.game.see_battle.exception.ScreenNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ScreenSelector {

    private static ScreenSelector instance;
    private final Map<String, Screen> screens = new HashMap<>();
    private final List<Consumer<Screen>> changeScreenListeners = new ArrayList<>();
    private ScreenSelector() {

    }

    public static ScreenSelector getInstance() {
        if (instance == null) {
            instance = new ScreenSelector();
        }
        return instance;
    }

    public void add(final String name, final Screen screen) {
        screens.put(name, screen);
    }

    public void choose(final String name) {
        Screen screen = screens.get(name);
        if (screen == null) {
            throw new ScreenNotFoundException(name);
        }
        changeScreenListeners.forEach(listener -> listener.accept(screen));
    }

    public void onchange(final Consumer<Screen> listener) {
        changeScreenListeners.add(listener);
    }

    public void unregisterListener(final Consumer<Screen> listener) {
        changeScreenListeners.remove(listener);
    }
}
