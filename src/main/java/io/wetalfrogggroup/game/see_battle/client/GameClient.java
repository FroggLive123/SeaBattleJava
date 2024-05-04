package io.wetalfrogggroup.game.see_battle.client;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.hash.Sha1;
import com.google.firebase.cloud.FirestoreClient;
import io.wetalfrogggroup.game.see_battle.model.Session;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Optional;

public class GameClient {

    private static GameClient instance;

    public static GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    private final Firestore client;

    public GameClient() {
        this.client = FirestoreClient.getFirestore();
    }

    public Optional<Session> findFirst() {
        return Optional.of(new Session("random"));
    }

    @SneakyThrows
    public Session openSession() {
        var md = MessageDigest.getInstance("SHA1");
        md.update(Long.toString(System.currentTimeMillis()).getBytes());
        var s = new Session(new String(md.digest()));

        var c = client.collection("session");
        c.document(s.key()).create(Map.of());

        return s;
    }

    public boolean hasPlayer(final Session session) {
        return false;
    }
}
