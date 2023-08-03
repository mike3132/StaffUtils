package net.resolutemc.staffutils.MessageManager;

import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConsoleMessages {

    public static void sendMessage(CommandSender sender, String key) {
        File messagesConfig = new File(StaffUtils.getInstance().getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
