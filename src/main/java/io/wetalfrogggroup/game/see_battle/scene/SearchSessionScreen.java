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
import io.wetalfrogggroup.game.see_battle.client.GameClient;
import io.wetalfrogggroup.game.see_battle.exception.SessionNotFoundException;
import io.wetalfrogggroup.game.see_battle.model.Session;
import io.wetalfrogggroup.game.see_battle.util.ScreenSelector;

import java.util.Optional;

public class SearchSessionScreen extends BasicScreen {

    private final Skin atlas;
    private boolean isActive;

    public SearchSessionScreen() {
        this.atlas = new Skin();
        atlas.add("button_up", new Texture(Gdx.files.classpath("assets/button_up.png")));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        var group = new Group();
        stage.addActor(group);

        group.setWidth(170);
        group.setPosition(300, 300);

        var font = new BitmapFont();
        var text = new Label("Search session...", new Label.LabelStyle(font, Color.WHITE));
        group.addActor(text);

        var cancelButton = new TextButton("Cancel", new TextButton.TextButtonStyle(
                atlas.get("button_up", Drawable.class),
                null,
                null,
                new BitmapFont()
        ));
        cancelButton.setWidth(group.getWidth());
        cancelButton.setHeight(50);
        cancelButton.setPosition(0, text.getY() - 90);

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                disableSearch();
                ScreenSelector.getInstance().choose("main-menu");
            }
        });

        group.addActor(cancelButton);

        searchSession();
    }

    private void searchSession() {
        System.out.println("Search session");
        isActive = true;
        var client = GameClient.getInstance();
        var session = client.findFirst();
        if (session.isPresent()) {
            System.out.println("Session found");
            client.connectTo(session.get());
            open(session.get());
            return;
        }

        new Thread(() -> {
            var errCount = 0;
            var s = client.openSession();
            while (true) {
                try {
                    if (client.hasPlayer(s)) {
                        break;
                    }
                } catch (SessionNotFoundException e) {
                    if (errCount++ > 5) {
                        System.err.printf("Session %s closed\n", s.key());
                        return;
                    }
                    continue;
                }

                if (!isActive) {
                    client.closeSession(s);
                    return;
                }
                System.out.printf("Waiting for player... [%s]\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
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

    private void disableSearch() {
        System.out.println("Search session disabled");
        isActive = false;
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
    }
}
