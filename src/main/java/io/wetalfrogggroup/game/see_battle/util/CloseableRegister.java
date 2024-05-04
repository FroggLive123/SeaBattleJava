package io.wetalfrogggroup.game.see_battle.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class CloseableRegister implements Closeable {

    private static CloseableRegister instance;
    private final Set<Closeable> set = new LinkedHashSet<>();

    public static CloseableRegister getInstance() {
        if (instance == null) {
            instance = new CloseableRegister();
        }
        return instance;
    }

    public void register(final Closeable c) {
        set.add(c);
    }

    @Override
    public void close() throws IOException {
        for (Closeable closeable : set) {
            closeable.close();
        }
    }
}
