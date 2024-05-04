package io.wetalfrogggroup.game.see_battle.client;

import io.wetalfrogggroup.game.see_battle.model.Session;

public class SessionClient {

    private static SessionClient instance;

    public static SessionClient getInstance() {
        if (instance == null) {
            instance = new SessionClient();
        }
        return instance;
    }

    public SessionClient() {
    }

    public void doSomething(final Session session) {
        System.out.println("SessionClient.doSomething");
    }
}
