package net.resolutemc.staffutils.SetManager;

import java.util.HashSet;
import java.util.UUID;

public class XraySet {

    private final static HashSet<UUID> xrayingPlayers = new HashSet<>();

    public static HashSet<UUID> getXrayingPlayers() {
        return xrayingPlayers;
    }

    public static void addXrayingPlayers(UUID player) {
        getXrayingPlayers().add(player);
    }

    public static void removeXrayingPlayers(UUID player) {
        getXrayingPlayers().remove(player);
    }
}
