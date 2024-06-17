package org.ovclub.ovcore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.ovclub.ovcore.OVCore;

import java.util.Arrays;
import java.util.UUID;

public class championsCommand implements CommandExecutor {
    String cmd1 = "champions";
    static Inventory inv;
    private final OVCore plugin;

    public championsCommand(OVCore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
            inv = Bukkit.createInventory(null, 9, "OVClub Champions");
            plugin.getData().replaceInventory(p.getUniqueId(), inv);
            this.openInventory(p);
            initalizeItems();
        }

        return true;
    }

    public static void initalizeItems() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("a82ccfde-a4ac-47b8-abfd-5c93df58e024")));
        skull.setItemMeta(meta);
        inv.addItem(new ItemStack[]{createGuiItem(skull, ChatColor.GOLD + "CopnMagi | July 2021 Champion", "Round Record: 14-4")});
        ItemStack skull1 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta1 = (SkullMeta)skull1.getItemMeta();
        meta1.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("c4c30f0e-4e18-49f9-a0dd-31af02cf15e9")));
        skull1.setItemMeta(meta1);
        inv.addItem(new ItemStack[]{createGuiItem(skull1, ChatColor.GOLD + "Senox23 | October 2021 Champion", "Round Record: 14-6")});
        ItemStack skull2 = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta2 = (SkullMeta)skull2.getItemMeta();
        meta2.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("79c3050c-a221-4dd7-a6e0-d749a68c6efc")));
        skull2.setItemMeta(meta2);
        inv.addItem(new ItemStack[]{createGuiItem(skull2, ChatColor.GOLD + "Jakamo2 | August 2022 Champion", "Round Record: 22-9")});
    }

    protected static ItemStack createGuiItem(ItemStack skull, String name, String lore) {
        ItemMeta meta = skull.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        skull.setItemMeta(meta);
        return skull;
    }

    public void openInventory(HumanEntity ent) {
        ent.openInventory(inv);
    }
}
