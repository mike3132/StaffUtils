package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.EffectManager.XrayEffect;
import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.RunnableManager.XrayRunnable;
import net.resolutemc.staffutils.SetManager.XraySet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XrayCommand implements CommandExecutor {

    public XrayCommand() {
        StaffUtils.getInstance().getCommand("Xray").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ConsoleMessages.sendMessage(sender, "Player-Only");
            return false;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("StaffUtils.Command.Xray")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }

        if (!XraySet.getXrayingPlayers().contains(player.getUniqueId())) {
            XraySet.addXrayingPlayers(player.getUniqueId());
            ChatMessages.sendMessage(player, "Xray-Enabled");
            XrayRunnable.xrayRunnable(player);
            XrayEffect.xrayEffectEnabled(player);
            return false;
        }
        XraySet.removeXrayingPlayers(player.getUniqueId());
        ChatMessages.sendMessage(player, "Xray-Disabled");
        XrayEffect.xrayEffectDisabled(player);
        return false;
    }
}
