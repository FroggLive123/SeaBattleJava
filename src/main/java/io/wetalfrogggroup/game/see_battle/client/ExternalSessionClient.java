package io.wetalfrogggroup.game.see_battle.client;

import com.google.cloud.firestore.Firestore;
import io.wetalfrogggroup.game.see_battle.exception.SessionNotFoundException;
import io.wetalfrogggroup.game.see_battle.firebase.document.SessionDocument;
import io.wetalfrogggroup.game.see_battle.model.Position;
import io.wetalfrogggroup.game.see_battle.model.Session;
import io.wetalfrogggroup.game.see_battle.model.Ship;
import io.wetalfrogggroup.game.see_battle.model.ShipDocument;
import io.wetalfrogggroup.game.see_battle.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExternalSessionClient implements SessionClient {

    private final static String SESSIONS_COLLECTION = "session";

    private final Firestore client;
    private final Session session;

    @SneakyThrows
    @Override
    public List<ShipDocument> getShips(final User user) {
        var o = find(session.key()).orElseThrow(() -> new SessionNotFoundException(session.key()));

        if (o.getPlayer1Id().equals(user.id())) {
            return Collections.unmodifiableList(o.getPlayer1Ships());
        }

        if (o.getPlayer2Id().equals(user.id())) {
            return Collections.unmodifiableList(o.getPlayer2Ships());
        }

        throw new IllegalAccessException("User is not part of the session");
    }

    @SneakyThrows
    @Override
    public void placeShip(final User user, final Ship type, final Position start, final boolean horizontal) {
        var o = find(session.key()).orElseThrow(() -> new SessionNotFoundException(session.key()));

        var doc = new ShipDocument(start.x(), start.y(), type.getSize(), horizontal);

        if (o.getPlayer1Id().equals(user.id())) {
            o.getPlayer1Ships().add(doc);
        } else if (o.getPlayer2Id().equals(user.id())) {
            o.getPlayer2Ships().add(doc);
        } else {
            throw new IllegalAccessException("User is not part of the session");
        }

        client.collection(SESSIONS_COLLECTION)
                .document(session.key())
                .set(o)
                .get();
    }

    private Optional<SessionDocument> find(final String id) {
        try {
            return Optional.ofNullable(client.collection(SESSIONS_COLLECTION)
                    .document(id)
                    .get()
                    .get()
                    .toObject(SessionDocument.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    private void update(final String id, final SessionDocument doc) {
        try {
            client.collection(SESSIONS_COLLECTION)
                    .document(id)
                    .set(doc)
                    .get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
