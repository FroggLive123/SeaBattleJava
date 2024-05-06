package io.wetalfrogggroup.game.see_battle.client;

import io.wetalfrogggroup.game.see_battle.model.Position;
import io.wetalfrogggroup.game.see_battle.model.Ship;
import io.wetalfrogggroup.game.see_battle.model.ShipDocument;
import io.wetalfrogggroup.game.see_battle.model.User;

import java.util.List;

public interface SessionClient {

    List<ShipDocument> getShips(final User user);

    void placeShip(final User user, final Ship type, final Position start, final boolean horizontal);

    List<Position> getShots(final User user);

    void shot(final User user, final Position position);

    default boolean isReady(final User user) {
        return getShips(user).size() == Ship.maxCount();
    }
}
