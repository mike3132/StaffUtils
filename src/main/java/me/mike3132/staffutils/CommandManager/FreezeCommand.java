package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class FreezeCommand implements CommandExecutor {

    public static HashSet<UUID> frozenPlayers = new HashSet<>();

    public FreezeCommand() {
        Main.plugin.getCommand("Freeze").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&eStaff Utils &7> &4You must be a player to use Freeze"));
            return true;
        }
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatMessages.playerPlaceholderMessage(player, "Freeze-Target-Not_Found", args[0]);
            return true;
        }
        if (!frozenPlayers.contains(target.getUniqueId())) {
            frozenPlayers.add(target.getUniqueId());
            ChatMessages.playerPlaceholderMessage(player, "Freeze-Enabled-On-Target", args[0]);
            target.teleport(player);
            return true;
        }
        frozenPlayers.remove(target.getUniqueId());
        ChatMessages.playerPlaceholderMessage(player, "Freeze-Disabled-On-Target", args[0]);

        return true;
    }
}
