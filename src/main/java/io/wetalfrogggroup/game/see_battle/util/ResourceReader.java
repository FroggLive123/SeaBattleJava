package io.wetalfrogggroup.game.see_battle.util;

import java.io.InputStream;
import java.util.Optional;

public class ResourceReader {

    public static Optional<InputStream> read(final String path) {
        return Optional.ofNullable(ResourceReader.class.getResourceAsStream(path));
    }
}
