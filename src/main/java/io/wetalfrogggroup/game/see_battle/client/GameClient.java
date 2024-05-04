package io.wetalfrogggroup.game.see_battle.client;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.hash.Sha1;
import com.google.firebase.cloud.FirestoreClient;
import io.wetalfrogggroup.game.see_battle.firebase.document.SessionDocument;
import io.wetalfrogggroup.game.see_battle.model.Session;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.time.Duration;
import java.util.HexFormat;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GameClient {

    private static final String SESSION_COLLECTION = "session";
    private static final Duration SESSION_TIMEOUT = Duration.ofHours(1);

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

    @SneakyThrows
    public Optional<Session> findFirst() {
        var c = client.collection(SESSION_COLLECTION);
        for (var d : c.listDocuments()) {
            var doc = Optional.ofNullable(d.get().get().toObject(SessionDocument.class));
            if (doc.isEmpty()) {
                d.delete();
                continue;
            }

            if (doc.get().getTimestamp() + SESSION_TIMEOUT.toMillis() < System.currentTimeMillis()) {
                d.delete();
                continue;
            }

            return Optional.of(new Session(d.getId()));
        }

        return Optional.empty();
    }

    @SneakyThrows
    public Session openSession() {
        var md = MessageDigest.getInstance("SHA1");
        md.update(Long.toString(System.currentTimeMillis()).getBytes());
        var s = new Session(HexFormat.of().formatHex(md.digest()));
        System.out.printf("Open session %s\n", s.key());

        var c = client.collection(SESSION_COLLECTION);
        c.document(s.key()).create(new SessionDocument(System.currentTimeMillis(), UUID.randomUUID().toString(), "noxe", null, null));

        return s;
    }

    public boolean hasPlayer(final Session session) {
        return false;
    }
}
