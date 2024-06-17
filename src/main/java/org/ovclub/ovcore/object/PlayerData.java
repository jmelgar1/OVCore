package org.ovclub.ovcore.object;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {
    public PlayerData() {
        inventories = new HashMap<>();
        rewards = new HashMap<>();
    }

    private final Map<UUID, Integer> rewards;
    public Map<UUID, Integer> getRewards(){
        return rewards;
    }
    public void replaceReward(UUID uuid, int amount){
        if(rewards.get(uuid) != null) {
            rewards.replace(uuid, amount);
        } else {
            rewards.put(uuid, amount);
        }
    }

    private final HashMap<UUID, Inventory> inventories;
    public HashMap<UUID, Inventory> getInventories(){
        return inventories;
    }

    public void replaceInventory(UUID uuid, Inventory inv){
        if(inventories.get(uuid) != null) {
            inventories.replace(uuid, inv);
        } else {
            inventories.put(uuid, inv);
        }
    }
}
