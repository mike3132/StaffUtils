package net.resolutemc.staffutils.MessageManager;

import org.bukkit.ChatColor;

public class ColorTranslate {

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

}
