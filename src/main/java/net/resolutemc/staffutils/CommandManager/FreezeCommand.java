package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.SetManager.FreezeSet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    public FreezeCommand() {
        StaffUtils.getInstance().getCommand("Freeze").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ConsoleMessages.sendMessage(sender, "Player-Only");
            return false;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("StaffUtils.Command.Freeze")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }

        if (args.length == 0) {
            ChatMessages.sendMessage(player, "Not-Enough-Args");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatMessages.sendMessage(player, "Freeze-Target-Not-Found");
            return true;
        }
        if (!FreezeSet.getFrozenPlayers().contains(target.getUniqueId())) {
            FreezeSet.addFrozenPlayers(target.getUniqueId());
            ChatMessages.sendMessageWithPlaceholder(player, "Freeze-Enabled-On-Target", target.getName());
            ChatMessages.sendMessage(target, "Target-Freeze-Message");
            target.teleport(player);
            return true;
        }
        FreezeSet.removeFrozenPlayers(target.getUniqueId());
        ChatMessages.sendMessageWithPlaceholder(player, "Freeze-Disabled-On-Target", target.getName());
        ChatMessages.sendMessage(target, "Target-Un-Freeze-Message");
        return false;
    }
}
