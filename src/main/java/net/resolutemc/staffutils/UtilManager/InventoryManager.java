package net.resolutemc.staffutils.UtilManager;

import net.resolutemc.staffutils.MessageManager.ChatMessages;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public void onCopyInventoryToPlayer(Player player, Player target) {
        if (player.getInventory().isEmpty()) {
            ChatMessages.sendMessage(player, "Copy-Inventory-Player-Empty");
            return;
        }

        if (target.getInventory().isEmpty()) {
            target.getInventory().setContents(player.getInventory().getContents());
            ChatMessages.sendMessage(target, "Copy-Inventory-Target");
            ChatMessages.sendMessage(player, "Copy-Inventory-Player");
            return;
        }

        for (ItemStack items : player.getInventory().getContents()) {
            if (items == null) continue;
            target.getLocation().getWorld().dropItem(target.getLocation(), items);
        }
        ChatMessages.sendMessage(target, "Copy-Inventory-Target-Inventory-Full");
        ChatMessages.sendMessage(player, "Copy-Inventory-Player");
    }

    public void onCopyInventoryToChest(Player player, Block block) {
        if (!block.getType().equals(Material.CHEST)) {
            ChatMessages.sendMessage(player, "Copy-Inventory-To-Chest-Target-Not-Found");
            return;
        }
        if (player.getInventory().isEmpty()) {
            ChatMessages.sendMessage(player, "Copy-Inventory-Player");
            return;
        }
        Chest chest = (Chest) block.getState();

        if (chest.getBlockInventory().firstEmpty() == -1) {
            ChatMessages.sendMessage(player, "Copy-Inventory-To-Chest-Chest-Full");
            return;
        }
        ChatMessages.sendMessage(player, "Copy-Inventory-To-Chest");

        for (ItemStack items : player.getInventory().getContents())  {
            if (items == null) continue;
            chest.getBlockInventory().addItem(items);
        }
    }
}
