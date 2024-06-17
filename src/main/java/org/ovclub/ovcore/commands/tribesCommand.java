package org.ovclub.ovcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tribesCommand implements CommandExecutor {
    String cmd1 = "tribes";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
                this.sendMessage(player);
            }
        }
        return true;
    }

    public void sendMessage(Player p) {
        p.sendMessage(Component.text("Tribes has been replaced by Crews! Use /crews").color(NamedTextColor.RED));
    }
}