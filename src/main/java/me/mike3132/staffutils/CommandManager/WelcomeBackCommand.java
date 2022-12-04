package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.EventManager.WelcomeBackEvent;
import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WelcomeBackCommand implements CommandExecutor {

    public WelcomeBackCommand() {
        Main.plugin.getCommand("WelcomeBack").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&eStaff Utils &7> &4You must be a player to use WelcomeBack"));
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("staffUtils.WelcomeBack")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }

        if(args.length == 0) {
            ChatMessages.sendMessage(player, "WelcomeBack-Not-Enough-Args");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatMessages.playerPlaceholderMessage(player, "WelcomeBack-Target-Not-Found", args[0]);
            return true;
        }

        if (WelcomeBackEvent.returningPlayer.contains(target.getUniqueId())) {
            ChatMessages.broadcastPlaceholderMessage("WelcomeBack-Message", args[0]);
        }



        return true;
    }
}
