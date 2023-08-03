package net.resolutemc.staffutils.EventManager;

import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ColorTranslate;
import net.resolutemc.staffutils.SetManager.FreezeSet;
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


    /**
     *
     * @param pqe If a player logs out while frozen, This will broadcast a message to anyone or group with permission
     *            node of "StaffUtils.FrozenLogout"
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (FreezeSet.getFrozenPlayers().contains(player.getUniqueId())) {
            FreezeSet.removeFrozenPlayers(player.getUniqueId());
            Bukkit.broadcast(ColorTranslate.chatColor(player.getName() + "&4Has logged out while froze"), "StaffUtils.FrozenLogout");
        }
    }

    /**
     *
     * @param pme Stops players from moving while frozen
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent pme) {
        Player player = pme.getPlayer();
        Location playerLocation = player.getLocation();
        if (!FreezeSet.getFrozenPlayers().contains(player.getUniqueId())) return;
        ChatMessages.sendMessage(player, "Target-Freeze-Message");
        pme.setCancelled(true);
        playerLocation.getWorld().strikeLightning(playerLocation);
    }

    /**
     *
     * @param bbe Stops players from breaking blocks while frozen.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent bbe) {
        Player player = bbe.getPlayer();
        if (FreezeSet.getFrozenPlayers().contains(player.getUniqueId())) {
            bbe.setCancelled(true);
        }
    }

    /**
     *
     * @param bpe Stops players from placing blocks while frozen.
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent bpe) {
        Player player = bpe.getPlayer();
        if (FreezeSet.getFrozenPlayers().contains(player.getUniqueId())) {
            bpe.setCancelled(true);
        }
    }



}
