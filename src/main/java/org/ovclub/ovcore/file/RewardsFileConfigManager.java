package org.ovclub.ovcore.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.ovclub.ovcore.OVCore;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class RewardsFileConfigManager {
    private static File rewardsFile = null;
    private static FileConfiguration rewardsFileConfig = null;
    private static OVCore plugin;

    public static void initialize(OVCore plugin) {
        RewardsFileConfigManager.plugin = plugin;
        reloadHighTableConfig();
    }

    public static void reloadHighTableConfig() {
        if (rewardsFile == null) {
            rewardsFile = new File(plugin.getDataFolder(), "rewards.yml");
        }
        rewardsFileConfig = YamlConfiguration.loadConfiguration(rewardsFile);

        Reader defConfigStream = new InputStreamReader(plugin.getResource("rewards.yml"), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        rewardsFileConfig.setDefaults(defConfig);
    }

    public static FileConfiguration getHighTableConfig() {
        if (rewardsFileConfig == null) {
            reloadHighTableConfig();
        }
        return rewardsFileConfig;
    }

    public static void saveHighTableConfig() {
        if (rewardsFileConfig == null || rewardsFile == null) {
            return;
        }
        try {
            getHighTableConfig().save(rewardsFile);
        } catch (IOException ex) {
            plugin.getLogger().severe("Could not save rewards.yml to " + rewardsFile);
            ex.printStackTrace();
        }
    }

    public static void saveDefaultHighTableConfig() {
        if (rewardsFile == null) {
            rewardsFile = new File(plugin.getDataFolder(), "rewards.yml");
        }
        if (!rewardsFile.exists()) {
            plugin.saveResource("rewards.yml", false);
        }
    }
}

