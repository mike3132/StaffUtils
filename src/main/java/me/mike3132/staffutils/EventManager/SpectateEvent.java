package me.mike3132.staffutils.EventManager;

import me.mike3132.staffutils.CommandManager.SpectateCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class SpectateEvent implements Listener {

    public SpectateEvent() {

    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent wc) {
        Player player = wc.getPlayer();
        if (SpectateCommand.specatorList.contains(player.getUniqueId())) {
            SpectateCommand.specatorList.remove(player.getUniqueId());
        }

    }
}
