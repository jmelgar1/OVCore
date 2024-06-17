package org.ovclub.ovcore.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.ovclub.ovcore.OVCore;
import org.ovclub.ovcore.util.EventUtil;

public class CheckForRewards extends BukkitRunnable {

    private final OVCore plugin;

    public CheckForRewards(OVCore plugin) {this.plugin = plugin;}

    @Override
    public void run() {
        if(Bukkit.getServer().getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (plugin.getData().getRewards().get(p.getUniqueId()) != null
                        && plugin.getData().getRewards().get(p.getUniqueId()) != 0) {
                    int amount = plugin.getData().getRewards().get(p.getUniqueId());
                    p.sendMessage(EventUtil.sendUnclaimedMessage(amount));
                }
            }
        }
    }
}
