package me.mike3132.staffutils.EventManager;

import me.mike3132.staffutils.CommandManager.BhopCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class BhopEvent implements Listener {

    public BhopEvent() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent pme) {
        Player player = pme.getPlayer();
        double dy = pme.getTo().getY() - pme.getFrom().getY();
        Vector move = pme.getTo().toVector().subtract(pme.getFrom().toVector());
        Vector look = player.getLocation().getDirection();
        double angle = (move.angle(look)) * 180.0F / Math.PI;

        if (BhopCommand.bhoppingPlayers.contains(player.getUniqueId())) {
            if (angle > 0.0D && angle < 150.0D && dy != 0.0D) {
                if (player.getWalkSpeed() < 0.0F) {
                    player.setWalkSpeed(makeToDecimal(player.getWalkSpeed() + 0.1F));
                } else {
                    player.setWalkSpeed(0.99F);
                }
            } else {
                if (player.getWalkSpeed() > 0.4F) {
                    player.setWalkSpeed(makeToDecimal(player.getWalkSpeed() - 0.2F));
                } else {
                    player.setWalkSpeed(0.2F);
                }
            }
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent pqe) {
        Player player = pqe.getPlayer();
        if (BhopCommand.bhoppingPlayers.contains(player.getUniqueId())) {
            BhopCommand.bhoppingPlayers.remove(player.getUniqueId());
        }
    }

    public float makeToDecimal(float a) {
        double roundOff = Math.round(a * 100.0D) / 100.0D;
        return (float) roundOff;
    }
}
