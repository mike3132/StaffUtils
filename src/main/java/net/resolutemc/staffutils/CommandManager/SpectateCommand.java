package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.EffectManager.SpectateEffect;
import net.resolutemc.staffutils.MessageManager.ChatMessages;
import net.resolutemc.staffutils.MessageManager.ConsoleMessages;
import net.resolutemc.staffutils.UtilManager.SpectateRunnable;
import net.resolutemc.staffutils.SetManager.SpectateSet;
import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateCommand implements CommandExecutor {

    public SpectateCommand() {
        StaffUtils.getInstance().getCommand("Spectate").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ConsoleMessages.sendMessage(sender, "Player-Only");
            return false;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("StaffUtils.Command.Spectate")) {
            ChatMessages.sendMessage(player, "No-Permission");
            return true;
        }

        if (args.length == 0) {
            ChatMessages.sendMessage(player, "Not-Enough-Args");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ChatMessages.sendMessage(player, "Spectate-Target-Not-Found");
            return true;
        }

        if (!SpectateSet.getSpectatingPlayers().contains(player.getUniqueId())) {
            SpectateSet.addSpectatePlayers(player.getUniqueId());
            SpectateRunnable.SpectateTeleport(player, target);
            ChatMessages.sendMessageWithPlaceholder(player, "Spectate-Enabled", target.getName());
            SpectateEffect.spectateEffectEnabled(player);
            return true;
        }
        SpectateSet.removeSpectatePlayers(player.getUniqueId());
        ChatMessages.sendMessageWithPlaceholder(player, "Spectate-Disabled", target.getName());
        SpectateEffect.spectateEffectDisabled(player);
        return false;
    }
}
