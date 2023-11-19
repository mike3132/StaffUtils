package net.resolutemc.staffutils.EventManager;

import net.resolutemc.staffutils.SetManager.BhopSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class BhopEvent implements Listener {


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent pme) {
        Player player = pme.getPlayer();
        double dy = pme.getTo().getY() - pme.getFrom().getY();
        Vector move = pme.getTo().toVector().subtract(pme.getFrom().toVector());
        Vector look = player.getLocation().getDirection();
        double angle = (move.angle(look) * 180.0F) / Math.PI;
        if (BhopSet.getBhopingPlayers().contains(player.getUniqueId())) {
            if (angle > 0.0D && angle < 150.0D && dy != 0.0D) {
                if (player.getWalkSpeed() < 0.0F) {
                    player.setWalkSpeed(makeTwoDecimal(player.getWalkSpeed() + 0.1F));
                } else {
                    player.setWalkSpeed(0.99F);
                }
            } else if (player.getWalkSpeed() > 0.4F) {
                player.setWalkSpeed(makeTwoDecimal(player.getWalkSpeed() - 0.2F));
            } else {
                player.setWalkSpeed(0.2F);
            }
        } else {
            player.setWalkSpeed(0.2F);
        }
    }

    public float makeTwoDecimal(float f) {
        double roundOff = Math.round(f * 100.0D) / 100.0D;
        return (float)roundOff;
    }

    @EventHandler
    public  void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (BhopSet.getBhopingPlayers().contains(player.getUniqueId())) {
            BhopSet.removeBhoppingPlayers(player.getUniqueId());
        }
    }




}
