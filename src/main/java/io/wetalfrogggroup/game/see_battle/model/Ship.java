package io.wetalfrogggroup.game.see_battle.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Ship {
    BATTLESHIP(4, 1),
    SUBMARINE(3, 2),
    DESTROYER(2, 3);

    public static int maxCount() {
        return Stream.of(values()).map(Ship::getCount).reduce(Integer::sum).orElseThrow();
    }

    private final int size;
    private final int count;
}
