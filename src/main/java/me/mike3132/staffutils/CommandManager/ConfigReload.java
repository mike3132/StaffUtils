package me.mike3132.staffutils.CommandManager;

import me.mike3132.staffutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigReload implements CommandExecutor {

    public ConfigReload() {
        Main.plugin.getCommand("ConfigReload").setExecutor(this);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("Only players can use this command"));
            return false;
        }
        Player player = (Player) sender;

        if (player.hasPermission("staffUtils.Reload")) {
            Main.plugin.reloadConfig();
        }

        return true;
    }
}
