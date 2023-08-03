package net.resolutemc.staffutils.MessageManager;

import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ChatMessages {

    public static void sendMessage(Player player,String key) {
        File messagesConfig = new File(StaffUtils.getInstance().getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessageWithPlaceholder(Player player, String key, String target) {
        File messagesConfig = new File(StaffUtils.getInstance().getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
