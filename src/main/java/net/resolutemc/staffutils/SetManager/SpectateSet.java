package net.resolutemc.staffutils.SetManager;

import java.util.HashSet;
import java.util.UUID;

public class SpectateSet {

    private final static HashSet<UUID> spectatingPlayers = new HashSet<>();

    public static HashSet<UUID> getSpectatingPlayers() {
        return spectatingPlayers;
    }

    public static void addSpectatePlayers(UUID player) {
        getSpectatingPlayers().add(player);
    }

    public static void removeSpectatePlayers(UUID player) {
        getSpectatingPlayers().remove(player);
    }
}
