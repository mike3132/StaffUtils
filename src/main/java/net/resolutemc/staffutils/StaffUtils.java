package net.resolutemc.staffutils;

import net.resolutemc.staffutils.CommandManager.FreezeCommand;
import net.resolutemc.staffutils.CommandManager.SpectateCommand;
import net.resolutemc.staffutils.CommandManager.StaffCommand;
import net.resolutemc.staffutils.CommandManager.XrayCommand;
import net.resolutemc.staffutils.ConfigManager.ConfigCreator;
import net.resolutemc.staffutils.EventManager.FreezeEvent;
import net.resolutemc.staffutils.MessageManager.ColorTranslate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffUtils extends JavaPlugin {

    private static StaffUtils INSTANCE;


    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        getServer().getConsoleSender().sendMessage(ColorTranslate.chatColor("&2Enabled"));

        // Event registers
        Bukkit.getPluginManager().registerEvents(new FreezeEvent(), this);


        // Config loader
        ConfigCreator.MESSAGES.create();
        saveDefaultConfig();
        getConfig();

        // Command registers
        registerStaffCommand();
        registerFreezeCommand();
        registerSpectateCommand();
        registerXrayCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ColorTranslate.chatColor("&4Disabled"));

    }

    public static StaffUtils getInstance() {
        return INSTANCE;
    }

    // Command loaders
    private void registerStaffCommand() {
        new StaffCommand();
    }
    private void registerFreezeCommand() {
        new FreezeCommand();
    }
    private void registerSpectateCommand() {
        new SpectateCommand();
    }
    private void registerXrayCommand() {
        new XrayCommand();
    }

}
