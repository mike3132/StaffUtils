package net.resolutemc.staffutils.SetManager;

import java.util.HashSet;
import java.util.UUID;

public class FreezeSet {

    private final static HashSet<UUID> frozenPlayers = new HashSet<>();

    public static HashSet<UUID> getFrozenPlayers() {
        return frozenPlayers;
    }

    public static void addFrozenPlayers(UUID player) {
        getFrozenPlayers().add(player);
    }

    public static void removeFrozenPlayers(UUID player) {
        getFrozenPlayers().remove(player);
    }
}
