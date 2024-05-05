package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import io.wetalfrogggroup.game.see_battle.client.GameClient;
import io.wetalfrogggroup.game.see_battle.model.Session;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;

import java.util.Optional;

public class SearchSessionScreen extends BasicScreen {

    public SearchSessionScreen() {
    }

    @Override
    public void show() {
        var font = new BitmapFont();
        var text = new Label("Search session...", new Label.LabelStyle(font, Color.WHITE));
        text.setPosition(300, 300);

        stage.addActor(text);

        searchSession();
    }

    private void searchSession() {
        var client = GameClient.getInstance();
        var session = client.findFirst();
        if (session.isPresent()) {
            System.out.println("Session found");
            client.connectTo(session.get());
            open(session.get());
            return;
        }

        new Thread(() -> {
            var s = client.openSession();
            while (!client.hasPlayer(s)) {
                System.out.println("Waiting for player...");
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            open(s);
        }).start();
    }

    private void open(final Session session) {
        var selector = ScreenSelector.getInstance();
        var screenName = "game-"+session.key();
        selector.add(screenName, new GameScreen(session));
        selector.choose(screenName);
    }
}
