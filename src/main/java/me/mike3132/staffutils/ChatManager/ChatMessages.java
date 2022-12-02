package me.mike3132.staffutils.ChatManager;

import me.mike3132.staffutils.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ChatMessages {

    public static void sendMessage(Player player, String key) {
        File messagesConfig = new File(Main.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    public static void playerPlaceholderMessage(Player player, String key, String target) {
        File messagesConfig = new File(Main.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        message = message.replace("%playerName%", target);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void broadcastPlaceholderMessage(String key, String target) {
        File messagesConfig = new File(Main.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        message = message.replace("%playerName%", target);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }



}
