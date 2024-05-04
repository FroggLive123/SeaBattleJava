package io.wetalfrogggroup.game.see_battle.exception;

public class ScreenNotFoundException extends RuntimeException {

    public ScreenNotFoundException(final String name) {
        super("Screen %s not found".formatted(name));
    }
}
