package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class XrayCommand implements CommandExecutor {

    public static HashSet<UUID> xrayingPlayers = new HashSet<>();
    public  static BossBar bar;

    String barTitle = Main.plugin.getConfig().getString("xrayBossBarTitle");
    boolean nightVisionEnabled = Main.plugin.getConfig().getBoolean("nightVision");
    boolean bossBarEnabled = Main.plugin.getConfig().getBoolean("xrayBossBar");

    public XrayCommand() {
        Main.plugin.getCommand("Xray").setExecutor(this);
        bar = Bukkit.createBossBar(Main.chatColor("" + barTitle), BarColor.GREEN, BarStyle.SOLID);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("Only players can use this command"));
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("staffUtils.Xray")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }
        if (!xrayingPlayers.contains(player.getUniqueId())) {
            xrayingPlayers.add(player.getUniqueId());
            ChatMessages.sendMessage(player, "Xray-Enabled");

            if (bossBarEnabled) {
                bar.addPlayer(player);
            }

            if (nightVisionEnabled) {
                player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(999999999, 255));
            }

            new BukkitRunnable() {
                public void run() {
                    if (xrayingPlayers.contains(player.getUniqueId())) {
                        Chunk chunk = player.getLocation().getChunk();
                        int minHeight = chunk.getWorld().getMinHeight();
                        int maxHeight = chunk.getWorld().getMaxHeight();
                        for (int x = 0; x < 16; x++) {
                            for (int y = minHeight; y < maxHeight; y++) {
                                for (int z = 0; z < 16; z++) {
                                    Block block = chunk.getBlock(x, y, z);
                                    List<Material> hiddenBlocks = new ArrayList<>();
                                    for (String string : Main.plugin.getConfig().getStringList("XrayBlocks")) {
                                        hiddenBlocks.add(Material.valueOf(string));
                                    }
                                    if (block.getType() != Material.AIR && !hiddenBlocks.contains(block.getType())) {
                                        player.sendBlockChange(block.getLocation(), Material.BARRIER.createBlockData());
                                    }
                                }
                            }
                        }
                    } else {
                        this.cancel();
                    }

                }
            }.runTaskTimer(Main.plugin, 20L, 20L);

            return true;
        }
        xrayingPlayers.remove(player.getUniqueId());
        ChatMessages.sendMessage(player, "Xray-Disabled");
        if (nightVisionEnabled) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
        if (bossBarEnabled) {
            bar.removePlayer(player);
        }

        return true;
    }
}
