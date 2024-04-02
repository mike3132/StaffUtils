package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.EffectManager.XrayEffect;
import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.SetManager.XraySet;
import net.resolutemc.staffutils.StaffUtils;
import net.resolutemc.staffutils.UtilManager.XrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

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

        if (args.length == 0) {
            player.sendMessage("Not enough args");
            return false;
        }

        XrayUtils xrayUtils = new XrayUtils();
        switch (args[0].toUpperCase(Locale.ROOT)) {
            case "ON":
                XraySet.addXrayPlayers(player.getUniqueId());
                xrayUtils.onXrayEnable(player);
                XrayEffect.xrayEffectEnabled(player);
                ChatMessages.sendMessage(player, "Xray-Enabled");
                break;
            case "OFF":
                XraySet.removeXrayPlayers(player.getUniqueId());
                xrayUtils.onXrayDisable(player);
                ChatMessages.sendMessage(player, "Xray-Disabled");
                XrayEffect.xrayEffectDisabled(player);
                break;
            default:
                player.sendMessage("Please select either on or off");
                break;

        }

        return false;
    }
}
