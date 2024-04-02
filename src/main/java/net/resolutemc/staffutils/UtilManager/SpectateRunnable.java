package net.resolutemc.staffutils.UtilManager;

import net.resolutemc.staffutils.SetManager.SpectateSet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpectateRunnable {

   public static void SpectateTeleport(Player player, Player target) {
       new BukkitRunnable() {
           @Override
           public void run() {
               if (SpectateSet.getSpectatingPlayers().contains(player.getUniqueId())) {
                   if (player.getGameMode() == GameMode.SPECTATOR) return;
                   player.setGameMode(GameMode.SPECTATOR);
                   player.teleport(target);
                   player.setSpectatorTarget(target);
               }
               this.cancel();
           }
       }.runTaskTimer(StaffUtils.getInstance(), 0L, 20L);
   }


}
