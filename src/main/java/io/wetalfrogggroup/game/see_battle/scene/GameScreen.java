package io.wetalfrogggroup.game.see_battle.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import io.wetalfrogggroup.game.see_battle.client.ExternalSessionClient;
import io.wetalfrogggroup.game.see_battle.client.SessionClient;
import io.wetalfrogggroup.game.see_battle.client.SessionClientFactory;
import io.wetalfrogggroup.game.see_battle.model.Session;

public class GameScreen extends BasicScreen {

    private final Session session;
    private final SessionClient client;

    public GameScreen(final Session session) {
        this.session = session;
        this.client = SessionClientFactory.getInstance().build(session);
    }

    @Override
    public void show() {
        super.show();

        var font = new BitmapFont();
        font.setColor(Color.WHITE);
        var text = new TextArea(
                "Game screen",
                new TextField.TextFieldStyle(font, Color.WHITE, null, null, null)
        );
        text.setPosition(100, 100);
        stage.addActor(text);
    }
}
