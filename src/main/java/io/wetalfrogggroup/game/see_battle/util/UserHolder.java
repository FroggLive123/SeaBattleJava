package io.wetalfrogggroup.game.see_battle.util;

import io.wetalfrogggroup.game.see_battle.model.User;

import java.util.UUID;

public class UserHolder {

    private static UserHolder instance;

    public static UserHolder getInstance() {
        if (instance == null) {
            instance = new UserHolder();
        }
        return instance;
    }

    private User user;

    public UserHolder() {
        user = new User(UUID.randomUUID().toString(), String.valueOf(System.currentTimeMillis()));
    }

    public User getUser() {
        return user;
    }
}
