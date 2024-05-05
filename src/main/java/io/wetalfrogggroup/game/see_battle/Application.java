package io.wetalfrogggroup.game.see_battle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.wetalfrogggroup.game.see_battle.firebase.FirebaseApplication;
import io.wetalfrogggroup.game.see_battle.util.CloseableRegister;
import lombok.SneakyThrows;

class Application {

    @SneakyThrows
    public static void main(final String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(Application::shutdown));
        final CloseableRegister cr = CloseableRegister.getInstance();

        try {
            //todo: uncomment this block to enable online mode
            new FirebaseApplication();

            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Sea Battle");
            config.setWindowedMode(800, 400);
            config.useVsync(true);
            config.setForegroundFPS(60);
            new Lwjgl3Application(new GameGame(), config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void shutdown() {
        try {
            CloseableRegister.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}