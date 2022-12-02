package me.mike3132.staffutils.EventManager;

import me.mike3132.staffutils.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

public class WelcomeBackEvent implements Listener {

    public WelcomeBackEvent() {
    }

    public static HashSet<UUID> returningPlayer = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent pje) {
        Player player = pje.getPlayer();
        if (!returningPlayer.contains(player.getUniqueId())) {
            returningPlayer.add(player.getUniqueId());

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (returningPlayer.contains(player.getUniqueId())) {
                        returningPlayer.remove(player.getUniqueId());
                    }
                }
            }.runTaskLater(Main.plugin, 200L);
        }
    }

}
