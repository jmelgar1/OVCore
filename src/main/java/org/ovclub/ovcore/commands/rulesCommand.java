package org.ovclub.ovcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rulesCommand implements CommandExecutor {
    String cmd1 = "rules";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
                this.sendMessage(player);
            }
        }
        return true;
    }

    public void sendMessage(Player p) {
        String line = String.join("", java.util.Collections.nCopies(20, "─"));
        p.sendMessage(
                Component.text(line)
                        .color(NamedTextColor.GRAY)
                        .append(Component.text("Chat Rules").decorate(TextDecoration.BOLD).color(NamedTextColor.DARK_RED))
                        .append(Component.text(line).color(NamedTextColor.GRAY))
        );
        p.sendMessage(
                Component.text("These are things that are not allowed. This includes but is not limited to:")
                        .color(NamedTextColor.RED)
        );
        p.sendMessage(Component.empty());
        p.sendMessage(Component.text("- No spamming.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- No automated messages.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- No doxxing.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- No IRL Threats.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- No pedophilia jokes.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- No unicode characters or\n  special font in chat.").color(NamedTextColor.RED));
        p.sendMessage(Component.text("- Don't be constantly annoying,\n  unreasonable, and obnoxious.").color(NamedTextColor.RED));
        p.sendMessage(Component.empty());
        p.sendMessage(
                Component.text("If a player if bothering you, /ignore.")
                        .color(NamedTextColor.RED)
                        .decorate(TextDecoration.BOLD)
        );
        p.sendMessage(Component.text(line).color(NamedTextColor.GRAY));
    }
}
