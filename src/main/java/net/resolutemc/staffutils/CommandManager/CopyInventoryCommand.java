package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.StaffUtils;
import net.resolutemc.staffutils.UtilManager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CopyInventoryCommand implements CommandExecutor {

    public CopyInventoryCommand() {
        StaffUtils.getInstance().getCommand("CopyInv").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ConsoleMessages.sendMessage(sender, "Player-Only");
            return false;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("StaffUtils.Command.CopyInventory")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }

        if (args.length == 0) {
            ChatMessages.sendMessage(player, "Not-Enough-Args");
            return true;
        }

        InventoryManager inventoryManager = new InventoryManager();
        if (args[0].equalsIgnoreCase("Player")) {
            if (args.length == 1) {
                player.sendMessage("Please supply a players name");
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                ChatMessages.sendMessage(player, "Copy-Inventory-To-Player-Target-Not-Found");
                return false;
            }
            inventoryManager.onCopyInventoryToPlayer(player, target);
            return false;
        }

        if (args[0].equalsIgnoreCase("Chest")) {
            Block targetBlock = player.getTargetBlockExact(4);
            if (targetBlock == null) {
                ChatMessages.sendMessage(player, "Copy-Inventory-To-Chest-To-Far");
                return false;
            }
            inventoryManager.onCopyInventoryToChest(player, targetBlock);
            return false;
        }

        return false;
    }


}
