package org.ovclub.ovcore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.ovclub.ovcore.OVCore;

import java.util.Map;
import java.util.UUID;

public class claimCommand implements CommandExecutor {

    private final OVCore plugin;

    public claimCommand(OVCore plugin) {
        this.plugin = plugin;
    }

    String cmd1 = "claim";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            if(cmd.getName().equalsIgnoreCase(cmd1)) {
                UUID playerUUID = p.getUniqueId();

                if (plugin.getData().getRewards().get(playerUUID) != null) {
                    int unclaimedAmount = plugin.getData().getRewards().get(playerUUID);

                    if(unclaimedAmount != 0) {
                        ItemStack sponges = new ItemStack(Material.SPONGE, unclaimedAmount);
                        Map<Integer, ItemStack> spongeStack = p.getInventory().addItem(sponges);
                        if(!spongeStack.isEmpty()) {
                            for(Map.Entry<Integer, ItemStack> entry : spongeStack.entrySet()) {
                                ItemStack stack = entry.getValue();
                                if(stack.getAmount() > 0) {
                                    int amountClaimed = unclaimedAmount - stack.getAmount();
                                    p.sendMessage(ChatColor.YELLOW + "You have claimed " + amountClaimed + " sponges!");
                                    p.sendMessage(ChatColor.RED + "Inventory full. Could not claim " + stack.getAmount() + " sponges.");
                                    plugin.getData().getRewards().remove(playerUUID);
                                    break;
                                }
                            }
                        } else {
                            plugin.getData().getRewards().remove(playerUUID);
                            p.sendMessage(ChatColor.YELLOW + "You have claimed " + unclaimedAmount + " sponges!");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have any unclaimed sponges!");
                    }
                }
            }
        }
        return true;
    }
}
