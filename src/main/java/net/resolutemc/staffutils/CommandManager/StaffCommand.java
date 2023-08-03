package net.resolutemc.staffutils.CommandManager;

import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffCommand implements CommandExecutor {

    public StaffCommand() {
        StaffUtils.getInstance().getCommand("StaffUtils").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("test");

        return false;
    }


}
