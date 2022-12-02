package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class BhopCommand implements CommandExecutor {

    public static HashSet<UUID> bhoppingPlayers = new HashSet<>();
    public static BossBar bar;

    public BhopCommand() {
        Main.plugin.getCommand("Bhop").setExecutor(this);
        bar = Bukkit.createBossBar(Main.chatColor("&2Bunny &aHopping"), BarColor.BLUE, BarStyle.SOLID);
    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&eStaff Utils &7> &4You must be a player to use Bhop"));
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("staffUtils.Bhop")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }
        if (!bhoppingPlayers.contains(player.getUniqueId())) {
            bhoppingPlayers.add(player.getUniqueId());
            bar.addPlayer(player);
            ChatMessages.sendMessage(player, "Bhop-Enabled");
            return true;
        }
        bhoppingPlayers.remove(player.getUniqueId());
        bar.removePlayer(player);
        player.setWalkSpeed(0.2F);
        ChatMessages.sendMessage(player, "Bhop-Disabled");
        return false;
    }
}
