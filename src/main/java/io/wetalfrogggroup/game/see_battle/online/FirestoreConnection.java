package io.wetalfrogggroup.game.see_battle.online;

import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

public class FirestoreConnection implements Closeable {

    private final UUID uuid;
    private final Firestore conn;

    public FirestoreConnection() throws Exception {
        this.uuid = UUID.randomUUID();
        this.conn = FirestoreClient.getFirestore();
    }

    public void asyncWrite(final String collection, final String document, final Object data) {
        conn.collection(collection).document(document).set(data);
    }

    public void setHealth() {
        var doc = conn.collection("health").document("health");
        doc.update(Map.of(uuid.toString(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
    }

    public void removeHealth() {
        var doc = conn.collection("health").document("health");
        doc.update(Map.of(uuid.toString(), FieldValue.delete()));
    }

    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
