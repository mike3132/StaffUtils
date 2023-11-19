package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ColorTranslate;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.SetManager.BhopSet;
import net.resolutemc.staffutils.SetManager.FreezeSet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BhopCommand implements CommandExecutor {

    public BhopCommand() {
        StaffUtils.getInstance().getCommand("Bhop").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ConsoleMessages.sendMessage(sender, "Player-Only");
            return false;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("StaffUtils.Command.Bhop")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }
        if (args.length != 0) {
            player.sendMessage("To many args");
            return false;
        }
        if (!BhopSet.getBhopingPlayers().contains(player.getUniqueId())) {
            BhopSet.addBhoppingPlayers(player.getUniqueId());
            player.sendMessage(ColorTranslate.chatColor("&bBhop &2Enabled"));
            return true;
        }
        BhopSet.removeBhoppingPlayers(player.getUniqueId());
        player.sendMessage(ColorTranslate.chatColor("&bBhop &4Disabled"));
        return false;
    }
}
