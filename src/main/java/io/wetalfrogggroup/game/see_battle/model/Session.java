package io.wetalfrogggroup.game.see_battle.model;

import lombok.With;

@With
public record Session(String key, User player1, User player2) {

    public Session(final String key, final User user) {
        this(key, null, null);
    }
}
