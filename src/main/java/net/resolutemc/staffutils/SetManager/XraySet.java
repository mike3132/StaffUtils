package net.resolutemc.staffutils.SetManager;

import java.util.HashSet;
import java.util.UUID;

public class XraySet {

    private final static HashSet<UUID> xrayPlayers = new HashSet<>();

    public static HashSet<UUID> getXrayPlayers() {
        return xrayPlayers;
    }

    public static void addXrayPlayers(UUID player) {
        getXrayPlayers().add(player);
    }

    public static void removeXrayPlayers(UUID player) {
        getXrayPlayers().remove(player);
    }

}
