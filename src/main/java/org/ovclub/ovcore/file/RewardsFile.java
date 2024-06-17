package org.ovclub.ovcore.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.ovclub.ovcore.OVCore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RewardsFile {
    private final OVCore plugin;
    private File rewardsFile;
    private FileConfiguration rewardsData;

    public RewardsFile(final OVCore plugin){
        this.plugin = plugin;
    }

    public void loadRewardsIntoMemory() {
        rewardsFile = new File(plugin.getDataFolder(), "rewards.yml");
        if (!rewardsFile.exists()) {
            plugin.saveResource("rewards.yml", false);
        }
        rewardsData = YamlConfiguration.loadConfiguration(rewardsFile);
        loadRewards();
    }

    private void loadRewards() {
        plugin.getData().getRewards().clear();
        for (String key : rewardsData.getKeys(false)) {
            if (key != null && !key.isEmpty()) {
                UUID uuid = UUID.fromString(key);
                int unclaimed = rewardsData.getInt(key + ".unclaimed", 0);
                plugin.getData().getRewards().put(uuid, unclaimed);
            }
        }
    }

    public void saveRewards(){
        System.out.println("Saving Rewards");
        List<String> savedRewards = new ArrayList<>();

        for (Map.Entry<UUID, Integer> reward : plugin.getData().getRewards().entrySet()) {
            rewardsData.set(String.valueOf(reward.getKey()), reward.getValue());
            savedRewards.add(String.valueOf(reward.getKey()));
        }
        try {
            rewardsData.save(rewardsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String key : rewardsData.getKeys(false)){
            if(!savedRewards.contains(key)){
                rewardsData.set(key, null);
            }
        }

        try {
            rewardsData.save(rewardsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
