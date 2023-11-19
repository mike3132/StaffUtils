package net.resolutemc.staffutils.SetManager;

import java.util.HashSet;
import java.util.UUID;

public class BhopSet {

    private final static HashSet<UUID> bhopingPlayers = new HashSet<>();

    public static HashSet<UUID> getBhopingPlayers() {
        return bhopingPlayers;
    }

    public static void addBhoppingPlayers(UUID player) {
        getBhopingPlayers().add(player);
    }

    public static void removeBhoppingPlayers(UUID player) {
        getBhopingPlayers().remove(player);
    }
}
