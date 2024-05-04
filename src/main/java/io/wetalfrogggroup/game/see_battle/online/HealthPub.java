package io.wetalfrogggroup.game.see_battle.online;

import lombok.RequiredArgsConstructor;

import java.io.Closeable;
import java.io.IOException;

/**
 * Publisher health status
 */
@RequiredArgsConstructor
public class HealthPub implements Closeable {

    private final FirestoreConnection conn;
    private final Thread thread;

    public HealthPub(final FirestoreConnection conn) {
        this.conn = conn;
        this.thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                pub();
                try {
                    Thread.sleep(15_000);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void pub() {
        conn.setHealth();
    }

    @Override
    public void close() throws IOException {
        conn.removeHealth();
        conn.close();
        thread.interrupt();
    }
}
