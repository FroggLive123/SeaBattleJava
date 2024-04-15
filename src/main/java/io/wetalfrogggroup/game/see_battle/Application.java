package io.wetalfrogggroup.game.see_battle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

class Application {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("See Battle");
        config.setWindowedMode(800, 400);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new Game(), config);
    }
}