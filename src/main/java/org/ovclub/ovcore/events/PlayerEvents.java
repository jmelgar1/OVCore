package org.ovclub.ovcore.events;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jline.utils.Log;
import org.ovclub.ovcore.OVCore;
import org.ovclub.ovcore.object.PlayerData;
import org.ovclub.ovcore.util.EventUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayerEvents implements Listener {

    private final OVCore plugin;
    public PlayerEvents(OVCore plugin) {
        this.plugin = plugin;
    }

    //CoreProtectAPI api = getCoreProtect();

    List<Material> blocks = Arrays.asList(Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.ANCIENT_DEBRIS);

//    protected CoreProtectAPI getCoreProtect() {
//        Plugin plugin = this.plugin.getServer().getPluginManager().getPlugin("CoreProtect");
//
//        // Check that CoreProtect is loaded
//        if (!(plugin instanceof CoreProtect)) {
//            return null;
//        }
//
//        // Check that the API is enabled
//        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
//        if (!CoreProtect.isEnabled()) {
//            return null;
//        }
//
//        // Check that a compatible version of the API is loaded
//        if (CoreProtect.APIVersion() < 9) {
//            return null;
//        }
//
//        return CoreProtect;
//	}

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        PlayerData data = plugin.getData();
        final Player p = (Player) e.getWhoClicked();
        UUID playerUUID = p.getUniqueId();
        if(!e.getInventory().equals(data.getInventories().get(playerUUID))) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void breakDiamondAndEmerald(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        Material material = b.getType();
        if(blocks.contains(material) && !EventUtil.isInSpawn(b)) {
            boolean blockPlaced = false;
            b.getMetadata("placed");
            if(!b.getMetadata("placed").isEmpty()) {
                blockPlaced = true;
            }

            if(!blockPlaced) {
                int amountDropped;
                if(blocks.contains(material)) {
                    if (material == Material.ANCIENT_DEBRIS){
                        amountDropped = EventUtil.getRandomNumberInRange(5, 8);
                    } else {
                        amountDropped = EventUtil.getRandomNumberInRange(1, 4);
                    }
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.SPONGE, amountDropped));
                    p.sendMessage(EventUtil.sendSpongeMessage(amountDropped, "the " + material));

                }
            }
        }
    }

    @EventHandler
    public void blockPlaced(BlockPlaceEvent e){
        Block b = e.getBlock();
        Material material = b.getType();

        if(blocks.contains(material)) {
            b.setMetadata("placed", new FixedMetadataValue(plugin, true));
        }
    }

    @EventHandler
	public void catchFish(PlayerFishEvent event) {
		Player p = event.getPlayer();
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
			Item item = (Item) event.getCaught();
            if (item.getItemStack().getType() == Material.ENCHANTED_BOOK ||
					  item.getItemStack().getType() == Material.FISHING_ROD ||
					  item.getItemStack().getType() == Material.NAME_TAG ||
					  item.getItemStack().getType() == Material.NAUTILUS_SHELL ||
					  item.getItemStack().getType() == Material.SADDLE) {
				int amountDropped = EventUtil.getRandomNumberInRange(2, 4);
				p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.SPONGE, amountDropped));
                p.sendMessage(EventUtil.sendSpongeMessage(amountDropped, "catching treasure!"));
			}
		}
	}

    @EventHandler
	public void killMob(EntityDeathEvent event) {

		LivingEntity entity = event.getEntity();
        if(!(entity.getKiller() == null)) {
			Player p = entity.getKiller();
			List<Object> bossMobs = Arrays.asList(EntityType.WITHER, EntityType.ENDER_DRAGON);

			int amountDropped = 0;
			if(bossMobs.contains(entity.getType())) {
				if (entity.getType() == EntityType.ENDER_DRAGON) {
					amountDropped = EventUtil.getRandomNumberInRange(15, 18);
                    p.sendMessage(EventUtil.sendSpongeMessage(amountDropped, "killing an Ender Dragon!"));

                } else if (entity.getType() == EntityType.WITHER) {
					amountDropped = EventUtil.getRandomNumberInRange(10, 13);
                    p.sendMessage(EventUtil.sendSpongeMessage(amountDropped, "killing a Wither!"));

                }
				entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.SPONGE, amountDropped));
			}

			if (entity.getType() != EntityType.PLAYER ||
					entity.getType() != EntityType.ARMOR_STAND) {
				amountDropped = EventUtil.getRandomNumberInRange(0, 405);
				if (amountDropped == 1 || amountDropped == 2 || amountDropped == 3) {
					entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.SPONGE, amountDropped));
                    p.sendMessage(EventUtil.sendSpongeMessage(amountDropped, "killing " + entity.getType() + "!"));

                }
			}
		}
	}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        int days = (int) (Bukkit.getWorld("world").getFullTime() / 24000);

        new BukkitRunnable() {
            private int count = 0;

            @Override
            public void run() {
                if (count >= 5) {
                    this.cancel();
                    return;
                }

                Component message = Component.text("(" + "⌚" + ") ").color(TextColor.fromHexString("#A3C2BE"))
                        .append(Component.text("Server Day: ").color(TextColor.fromHexString("#4DB6AC"))
                                .append(Component.text(days).color(TextColor.fromHexString("#00796B"))));

                event.getPlayer().sendActionBar(message);
                count++;
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private static long daysSince(LocalDate startDate) {
        return ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }
}
