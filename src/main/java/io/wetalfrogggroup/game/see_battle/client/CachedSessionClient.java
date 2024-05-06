package io.wetalfrogggroup.game.see_battle.client;

import io.wetalfrogggroup.game.see_battle.model.Position;
import io.wetalfrogggroup.game.see_battle.model.Ship;
import io.wetalfrogggroup.game.see_battle.model.ShipDocument;
import io.wetalfrogggroup.game.see_battle.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CachedSessionClient implements SessionClient {

    private final SessionClient delegate;

    private final Map<User, List<ShipDocument>> ships = new HashMap<>();
    private final Map<User, List<Position>> shots = new HashMap<>();

    public CachedSessionClient(final SessionClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<ShipDocument> getShips(final User user) {
        var x = Optional.ofNullable(ships.get(user));
        if (x.isEmpty()) {
            var list = new ArrayList<ShipDocument>();
            var ships = delegate.getShips(user);

            // do not write to cache if the user has not placed all ships
            if (ships.size() < Ship.maxCount()) {
                return ships;
            }

            // copy the list to avoid modification of the cache
            Collections.copy(list, ships);
            this.ships.put(user, list);
            return ships;
        }

        if (x.get().size() == Ship.maxCount()) {
            return Collections.unmodifiableList(x.get());
        }

        // if something went wrong, we always will have the latest data
        return delegate.getShips(user);
    }
}
