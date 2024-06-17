package org.ovclub.ovcore.commands.admin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ovclub.ovcore.OVCore;

import java.util.UUID;

public class addSpongesCommand implements CommandExecutor {

    private final OVCore plugin;

    public addSpongesCommand(OVCore plugin) {
        this.plugin = plugin;
    }

    String cmd1 = "addsponges";

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            if (cmd.getName().equalsIgnoreCase(cmd1)) {
                if (p.hasPermission("crews.addrewards")) {
                    if (args.length == 2) {

                        OfflinePlayer rewardee = Bukkit.getServer().getOfflinePlayer(args[0]);
                        UUID rewardeeUUID = rewardee.getUniqueId();
                        int amount = Integer.parseInt(args[1]);

                        if (plugin.getData().getRewards().get(rewardeeUUID) != null) {
                            int oldAmount = plugin.getData().getRewards().get(rewardeeUUID);
                            oldAmount += amount;
                            plugin.getData().replaceReward(rewardeeUUID, oldAmount);
                            p.sendMessage(ChatColor.GREEN + rewardee.getName() + " unclaimed sponges new amount is " + oldAmount + " sponges!");
                        } else {
                            p.sendMessage(ChatColor.RED + rewardee.getName() + " does not exist in rewards file! Use /setrewards");
                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Correct usage: /addsponges [player] [amount]");
                    }
                }
            }
        }
        return true;
    }
}
