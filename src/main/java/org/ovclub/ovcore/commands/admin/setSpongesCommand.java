package org.ovclub.ovcore.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.ovclub.ovcore.OVCore;

import java.util.UUID;

public class setSpongesCommand implements CommandExecutor {

    private final OVCore plugin;

    public setSpongesCommand(OVCore plugin) {
        this.plugin = plugin;
    }

    String cmd1 = "setsponges";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            if(cmd.getName().equalsIgnoreCase(cmd1)) {
            		if(p.hasPermission("crews.setsponges")) {
            			if(args.length == 2) {

            				OfflinePlayer rewardee = Bukkit.getServer().getOfflinePlayer(args[0]);
            				UUID rewardeeUUID = rewardee.getUniqueId();
            				int amount = Integer.parseInt(args[1]);

            				if(plugin.getData().getRewards().get(rewardeeUUID) != null) {
                                plugin.getData().replaceReward(rewardeeUUID, amount);
            					p.sendMessage(ChatColor.GREEN + rewardee.getName() + " unclaimed sponges set to " + amount + " sponges!");
            				} else {
                                plugin.getData().replaceReward(rewardeeUUID, amount);
                                p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "NEW FILE: " + rewardee.getName() + " unclaimed sponges set to " + amount + " sponges!");
            				}
            			} else {
            				p.sendMessage(ChatColor.RED + "Correct usage: /setsponges [player] [amount]");
            			}
            		}
            }
        }
        return true;
    }
}
