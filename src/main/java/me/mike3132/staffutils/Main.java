package me.mike3132.staffutils;

import me.mike3132.staffutils.CommandManager.*;
import me.mike3132.staffutils.EventManager.BhopEvent;
import me.mike3132.staffutils.EventManager.FreezeEvent;
import me.mike3132.staffutils.EventManager.WelcomeBackEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("&eStaff Utils &7> &2&lEnabled"));

        // Event registers
        Bukkit.getPluginManager().registerEvents(new BhopEvent(), this);
        Bukkit.getPluginManager().registerEvents(new FreezeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WelcomeBackEvent(), this);

        // Command registers
        registerBhopCommand();
        registerFreezeCommand();
        registerSpectateCommand();
        registerWelcomeBackCommand();
        registerXrayCommand();
        registerStaffUtilsCommand();

        // Config loader
        saveDefaultConfig();
        getConfig();
        createFile();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("&eStaff Utils &7> &4&lDisabled"));
    }

    // Messages.yml file creation
    private File messages;
    private FileConfiguration messagesConfig;

    private void createFile() {
        messages = new File(getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        messagesConfig = new YamlConfiguration();
        try {
            messagesConfig.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void registerBhopCommand() {
        new BhopCommand();
    }
    public void registerFreezeCommand() {
        new FreezeCommand();
    }
    public void registerSpectateCommand() {
        new SpectateCommand();
    }
    public void registerWelcomeBackCommand() {
        new WelcomeBackCommand();
    }
    public void registerXrayCommand() {
        new XrayCommand();
    }
    public void registerStaffUtilsCommand() {
        new StaffUtilsCommand();
    }



}
