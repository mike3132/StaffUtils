package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

public class SpectateCommand implements CommandExecutor {

    public static HashSet<UUID> specatorList = new HashSet<>();
    public static BossBar bar;

    String barTitle = Main.plugin.getConfig().getString("spectateBossBarTitle");
    boolean bossBarEnabled = Main.plugin.getConfig().getBoolean("spectateBossBar");

    public SpectateCommand() {
        Main.plugin.getCommand("Spectate").setExecutor(this);
        bar = Bukkit.createBossBar(Main.chatColor("" + barTitle), BarColor.YELLOW, BarStyle.SOLID);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&eStaff Utils &7> &4You must be a player to use Spectate"));
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("staffUtils.Spectate")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }
        if (args.length == 0) {
            ChatMessages.sendMessage(player, "Spectate-Not-Enough-Args");
            return true;
        }


        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatMessages.playerPlaceholderMessage(player, "Spectate-Target-Not-Found", args[0]);
            return true;
        }

        if (player.getWorld() != target.getWorld()) {
            ChatMessages.playerPlaceholderMessage(player, "Spectate-World-Different", args[0]);
            return true;
        }


        if (!specatorList.contains(player.getUniqueId())) {
            specatorList.add(player.getUniqueId());
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(target);
            player.setSpectatorTarget(target);
            ChatMessages.playerPlaceholderMessage(player, "Spectate-Enabled", args[0]);

            if (bossBarEnabled) {
                bar.addPlayer(player);
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (specatorList.contains(player.getUniqueId())) {
                        player.teleport(target);
                        player.setSpectatorTarget(target);
                        return;
                    }
                    this.cancel();
                }
            }.runTaskTimer(Main.plugin, 20L, 20L);
            return true;
        }
        specatorList.remove(player.getUniqueId());
        ChatMessages.sendMessage(player, "Spectate-Disabled");
        if (bossBarEnabled && bar.getPlayers().contains(player)) {
            bar.removePlayer(player);
        }


        return true;
    }
}
