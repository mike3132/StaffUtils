package net.resolutemc.staffutils.UtilManager;

import net.resolutemc.staffutils.SetManager.XraySet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class XrayUtils implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent pme) {
        Player player = pme.getPlayer();

        if (!XraySet.getXrayPlayers().contains(player.getUniqueId())) return;
        if (pme.getFrom().getChunk().equals(pme.getTo().getChunk())) return;
        onXrayDisable(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("Should be re-enabled xray now");
                onXrayEnable(player);
            }
        }.runTaskLater(StaffUtils.getInstance(), 20L);

    }

    public void onXrayEnable(Player player) {
        Chunk chunk = player.getLocation().getChunk();
        int minHeight = chunk.getWorld().getMinHeight();
        int maxHeight = chunk.getWorld().getMaxHeight();
        for (int x = 0; x < 16; x++) {
            for (int y = minHeight; y < maxHeight; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    List<Material> hiddenBlocks = new ArrayList<>();
                    for (String string : StaffUtils.getInstance().getConfig().getStringList("XrayBlocks")) {
                        hiddenBlocks.add(Material.valueOf(string));
                    }
                    if (block.getType() != Material.AIR && !hiddenBlocks.contains(block.getType())) {
                        player.sendBlockChange(block.getLocation(), Material.GLASS.createBlockData());
                    }
                }
            }
        }
    }

    public void onXrayDisable(Player player) {
        Chunk chunk = player.getLocation().getChunk();
        int minHeight = chunk.getWorld().getMinHeight();
        int maxHeight = chunk.getWorld().getMaxHeight();
        for (int x = 0; x < 16; x++) {
            for (int y = minHeight; y < maxHeight; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    player.sendBlockChange(block.getLocation(), block.getType().createBlockData());
                }
            }
        }
    }

}
