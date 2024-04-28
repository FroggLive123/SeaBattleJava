package io.wetalfrogggroup.game.see_battle.component;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MyButton extends ImageButton {

    private final String id;

    public MyButton(final String id, final ImageButtonStyle style) {
        super(style);
        this.id = id;
        this.addListener(new ClickListener() {
            @Override
            public void clicked(final com.badlogic.gdx.scenes.scene2d.InputEvent event, final float x, final float y) {
                System.out.println("Button clicked: " + id);
            }
        });
    }
}
