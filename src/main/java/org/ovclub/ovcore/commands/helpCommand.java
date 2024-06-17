package org.ovclub.ovcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCommand implements CommandExecutor {
    String cmd1 = "help";

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
        p.sendMessage(Component.text(line).style(Style.style(TextColor.fromHexString("#5A9ACE"))));

        p.sendMessage(
                Component.text("- /home ")
                        .style(Style.style(TextColor.fromHexString("#5A9ACE")))
                        .append(Component.text("(Set a home)").style(Style.style(TextColor.fromHexString("#B3C7D8"))))
        );
        p.sendMessage(
                Component.text("- /crews ")
                        .style(Style.style(TextColor.fromHexString("#5A9AA4")))
                        .append(Component.text("(Crews commands)").style(Style.style(TextColor.fromHexString("#8FA0AE"))))
        );
        p.sendMessage(
                Component.text("- /vote ")
                        .style(Style.style(TextColor.fromHexString("#5A9AA4")))
                        .append(Component.text("(Vote for the server!)").style(Style.style(TextColor.fromHexString("#8FA0AE"))))
        );
        p.sendMessage(
                Component.text("- /spawn ")
                        .style(Style.style(TextColor.fromHexString("#5A9AA4")))
                        .append(Component.text("(Go to spawn)").style(Style.style(TextColor.fromHexString("#8FA0AE"))))
        );
        p.sendMessage(
                Component.text("- /warp ")
                        .style(Style.style(TextColor.fromHexString("#5A9ACE")))
                        .append(Component.text("(See a list of warps)").style(Style.style(TextColor.fromHexString("#B3C7D8"))))
        );
        p.sendMessage(
                Component.text("- /rules ")
                        .style(Style.style(TextColor.fromHexString("#5A9ACE")))
                        .append(Component.text("(Rules)").style(Style.style(TextColor.fromHexString("#B3C7D8"))))
        );
        p.sendMessage(
                Component.text("- /discord ")
                        .style(Style.style(TextColor.fromHexString("#5A9AA4")))
                        .append(Component.text("(Discord Link)").style(Style.style(TextColor.fromHexString("#8FA0AE"))))
        );
//        p.sendMessage(
//                Component.text("- /ovprofile ")
//                        .style(Style.style(TextColor.fromHexString("#5A9ACE")))
//                        .append(Component.text("(Check your OV profile)").style(Style.style(TextColor.fromHexString("#B3C7D8"))))
//        );
        p.sendMessage(
                Component.text("- /donate ")
                        .style(Style.style(TextColor.fromHexString("#5A9AA4")))
                        .append(Component.text("(See donation info)").style(Style.style(TextColor.fromHexString("#8FA0AE"))))
        );
        p.sendMessage(
                Component.text("- /ignore ")
                        .style(Style.style(TextColor.fromHexString("#5A9ACE")))
                        .append(Component.text("(Private message someone)").style(Style.style(TextColor.fromHexString("#B3C7D8"))))
        );

        p.sendMessage(Component.text(line).style(Style.style(TextColor.fromHexString("#5A9ACE"))));
    }

}
