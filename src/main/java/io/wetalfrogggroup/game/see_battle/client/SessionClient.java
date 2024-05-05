package io.wetalfrogggroup.game.see_battle.client;

import com.google.cloud.firestore.Firestore;
import io.wetalfrogggroup.game.see_battle.model.Session;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionClient {

    private final Firestore client;
    private final Session session;


}
