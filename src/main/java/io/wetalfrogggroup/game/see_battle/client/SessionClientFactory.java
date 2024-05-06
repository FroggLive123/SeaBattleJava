package io.wetalfrogggroup.game.see_battle.client;

import com.google.firebase.cloud.FirestoreClient;
import io.wetalfrogggroup.game.see_battle.model.Session;

public class SessionClientFactory {

    private static SessionClientFactory instance;

    public static SessionClientFactory getInstance() {
        if (instance == null) {
            instance = new SessionClientFactory();
        }
        return instance;
    }

    private SessionClientFactory() {
    }

    public SessionClient build(final Session session) {
        return new CachedSessionClient(new ExternalSessionClient(FirestoreClient.getFirestore(), session));
    }
}
