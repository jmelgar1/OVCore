package org.ovclub.ovcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class killCommand implements CommandExecutor {
    String cmd1 = "kill";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
                player.setHealth(0.0);
            }
        }
        return true;
    }
}
