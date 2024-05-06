package io.wetalfrogggroup.game.see_battle.model;

public record Position(int x, int y) {

    public static Position decode(final String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }

        var v = value.split(":");
        return new Position(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
    }

    public String encode() {
        return "%d:%d".formatted(x, y);
    }

    @Override
    public String toString() {
        return encode();
    }
}
