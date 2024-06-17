package org.ovclub.ovcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.ovclub.ovcore.commands.*;
import org.ovclub.ovcore.commands.admin.addSpongesCommand;
import org.ovclub.ovcore.commands.admin.setSpongesCommand;
import org.ovclub.ovcore.events.PlayerEvents;
import org.ovclub.ovcore.file.RewardsFile;
import org.ovclub.ovcore.object.PlayerData;
import org.ovclub.ovcore.runnables.CheckForRewards;

public final class OVCore extends JavaPlugin {

    private PlayerData playerData;
    public PlayerData getData(){return this.playerData;}

    private RewardsFile rewardsFile;

    @Override
    public void onEnable() {
        System.out.println("[OVCore Enabled]");
        this.playerData = new PlayerData();
        this.getCommand("kill").setExecutor(new killCommand());
        this.getCommand("donate").setExecutor(new donateCommand());
        this.getCommand("rules").setExecutor(new rulesCommand());
        this.getCommand("chatrules").setExecutor(new chatrulesCommand());
        this.getCommand("help").setExecutor(new helpCommand());
        this.getCommand("addsponges").setExecutor(new addSpongesCommand(this));
        this.getCommand("setsponges").setExecutor(new setSpongesCommand(this));
        this.getCommand("claim").setExecutor(new claimCommand(this));
        this.getCommand("tribes").setExecutor(new tribesCommand());

        //this.getCommand("wiki").setExecutor(new wikiCommand());
        //red vs blue here sometime
        //bring back raffle eventually
        this.getCommand("champions").setExecutor(new championsCommand(this));

        CheckForRewards checkForRewards = new CheckForRewards(this);
        checkForRewards.runTaskTimer(this, 0L, 3000);

        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);

        rewardsFile = new RewardsFile(this);
        rewardsFile.loadRewardsIntoMemory();
    }

    @Override
    public void onDisable() {
        rewardsFile.saveRewards();
        System.out.println("[OVCore Disabled]");
    }
}
