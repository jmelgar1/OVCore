package org.ovclub.ovcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class donateCommand implements CommandExecutor {
    String cmd1 = "donate";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
                this.sendMessage(player);
            }
        }
        return true;
    }

    public void sendMessage(Player p) {
        Component message = Component.text("https://ovclub.gg/store")
                .color(NamedTextColor.RED)
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://ovclub.gg/store"))
                .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("Visit our store!")));
        p.sendMessage(Component.text("Click the link below!").color(NamedTextColor.GRAY));
        p.sendMessage(message);
    }
}