package io.wetalfrogggroup.game.see_battle.exception;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(final String key) {
        super("Session not found: " + key);
    }
}
