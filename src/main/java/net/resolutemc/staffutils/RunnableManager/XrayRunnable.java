package net.resolutemc.staffutils.RunnableManager;

import net.resolutemc.staffutils.SetManager.XraySet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class XrayRunnable {

    public static void xrayRunnable(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!XraySet.getXrayingPlayers().contains(player.getUniqueId())) return;
                Chunk chunk = player.getLocation().getChunk();
                int minHeight = chunk.getWorld().getMinHeight();
                int maxHeight = chunk.getWorld().getMaxHeight();
                for (int x = 0; x < 16; x++) {
                    for (int y = minHeight; y < maxHeight; y++) {
                        for (int z = 0; z < 16; z++) {
                            Block block = chunk.getBlock(x,y,z);
                            List<Material> hiddenBlocks = new ArrayList<>();
                            for (String string : StaffUtils.getInstance().getConfig().getStringList("XrayBlocks")) {
                                hiddenBlocks.add(Material.valueOf(string));
                            }
                            if (block.getType() != Material.AIR && !hiddenBlocks.contains(block.getType())) {
                                player.sendBlockChange(block.getLocation(), Material.BARRIER.createBlockData());
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(StaffUtils.getInstance(), 0L, 20L);
    }
}
