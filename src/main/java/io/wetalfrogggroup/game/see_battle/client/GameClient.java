package io.wetalfrogggroup.game.see_battle.client;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import io.wetalfrogggroup.game.see_battle.firebase.document.SessionDocument;
import io.wetalfrogggroup.game.see_battle.model.Session;
import io.wetalfrogggroup.game.see_battle.model.User;
import io.wetalfrogggroup.game.see_battle.util.UserHolder;
import lombok.SneakyThrows;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;

import java.security.MessageDigest;
import java.time.Duration;
import java.util.HexFormat;
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
    private final UserHolder userHolder;

    public GameClient() {
        this.client = FirestoreClient.getFirestore();
        this.userHolder = UserHolder.getInstance();
    }

    @SneakyThrows
    public Optional<Session> findFirst() {
        var user = userHolder.getUser();
        var c = client.collection(SESSION_COLLECTION);
        for (var d : c.listDocuments()) {
            var doc = Optional.ofNullable(d.get().get().toObject(SessionDocument.class));
            if (doc.isEmpty()) {
                d.delete();
                continue;
            }

            var dd = doc.get();

            if (dd.getTimestamp() + SESSION_TIMEOUT.toMillis() < System.currentTimeMillis()) {
                d.delete();
                continue;
            }

            if (dd.getPlayer1Id().equals(user.id())) {
                d.delete();
                continue;
            }

            if (dd.getPlayer2Id() != null) {
                continue;
            }

            var session = new Session(d.getId(), new User(dd.getPlayer1Id(), dd.getPlayer1Name()), user);
            return Optional.of(session);
        }

        return Optional.empty();
    }

    public void connectTo(final Session session) {
        System.out.printf("Connect to session %s\n", session.key());
        var c = client.collection(SESSION_COLLECTION);
        c.document(session.key()).update("player2Id", session.player2().id());
        c.document(session.key()).update("player2Name", session.player2().name());
    }

    @SneakyThrows
    public Session openSession() {
        var user = userHolder.getUser();

        var md = MessageDigest.getInstance("SHA1");
        md.update(Long.toString(System.currentTimeMillis()).getBytes());
        var s = new Session(HexFormat.of().formatHex(md.digest()), user);
        System.out.printf("Open session %s\n", s.key());

        var c = client.collection(SESSION_COLLECTION);
        var timestamp = System.currentTimeMillis();
        c.document(s.key()).create(new SessionDocument(
                        timestamp,
                        user.id(),
                        user.name(),
                        null,
                        null
                ));

        return s;
    }

    @SneakyThrows
    public boolean hasPlayer(final Session session) {
        var c = client.collection(SESSION_COLLECTION);
        var doc = c.document(session.key()).get().get().toObject(SessionDocument.class);
        if (doc == null) {
            throw new IllegalStateException("Session not found");
        }

        return doc.getPlayer2Id() != null;
    }
}
