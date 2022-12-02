package me.mike3132.staffutils.EventManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.CommandManager.FreezeCommand;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeEvent implements Listener {

    public FreezeEvent() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent pme) {
        Player player = pme.getPlayer();
        Location location = player.getLocation();
        if (FreezeCommand.frozenPlayers.contains(player.getUniqueId())) {
            ChatMessages.sendMessage(player, "Target-Freeze-Message");
            pme.setCancelled(true);
            location.getWorld().strikeLightningEffect(location);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (FreezeCommand.frozenPlayers.contains(player.getUniqueId())) {
            FreezeCommand.frozenPlayers.remove(player.getUniqueId());
            Bukkit.broadcast(Main.chatColor(player.getName() + " &4Has logged out while frozen"), "staffUtils.LogoutWhileFrozen");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent bbe) {
        Player player = bbe.getPlayer();
        if (FreezeCommand.frozenPlayers.contains(player.getUniqueId())) {
            bbe.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent bpe) {
        Player player = bpe.getPlayer();
        if (FreezeCommand.frozenPlayers.contains(player.getUniqueId())) {
            bpe.setCancelled(true);
        }
    }
}
