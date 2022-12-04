package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.ChatManager.ChatMessages;
import me.mike3132.staffutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffUtilsCommand implements CommandExecutor {

    public StaffUtilsCommand() {
        Main.plugin.getCommand("StaffUtils").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&eStaff Utils &7> &4You must be a player to use Spectate"));
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("staffUtils.Admin")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }
        if (args.length == 0) {
            ChatMessages.sendMessage(player, "Help-Not-Enough-Arguments");
            return true;
        }
        if (args[0].equalsIgnoreCase("Reload")) {
            player.sendMessage(Main.chatColor("&eStaff Utils &7> &aPluging config reloaded in &2" + String.valueOf(System.currentTimeMillis() - 1) + " &ams"));
            Main.plugin.reloadConfig();
        }

        if (args[0].equalsIgnoreCase("Help")) {
            ChatMessages.sendMessage(player, "Help-Header");
            ChatMessages.sendMessage(player, "Help-A");
            ChatMessages.sendMessage(player, "Help-B");
            ChatMessages.sendMessage(player, "Help-C");
            ChatMessages.sendMessage(player, "Help-D");
            ChatMessages.sendMessage(player, "Help-E");
            ChatMessages.sendMessage(player, "Help-F");
            ChatMessages.sendMessage(player, "Help-G");
            ChatMessages.sendMessage(player, "Help-H");
            ChatMessages.sendMessage(player, "Help-Footer");
        }




        return true;
    }

}
