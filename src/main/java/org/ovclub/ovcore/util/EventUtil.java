package org.ovclub.ovcore.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class EventUtil {
    public static String sponge_icon = "❐";
    public static TextColor sponge_color = TextColor.fromHexString("#dfff00");

    public static boolean isInSpawn(Block block){
        World world = Bukkit.getWorld("world");
        // Get the location of the block
        Location blockLocation = block.getLocation();

        // Create a location at (0, 0, 0) in the same world
        Location origin = new Location(Bukkit.getWorld("world"), 0, 0, 0);

        // Calculate the distance between the block and the origin
        double distance = blockLocation.distance(origin);

        // Check if the distance is less than or equal to 50 blocks
        return distance <= 24;
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random rand = new Random();
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return rand.nextInt((max - min) + 1) + min;
    }

    public static TextComponent sendSpongeMessage(int amount, String activity){
        return Component.text("[" + sponge_icon + "] You earned " + amount + " sponges from " + activity).color(sponge_color);
    }

    public static TextComponent sendUnclaimedMessage(int amount){
        return Component.text("You have " + amount + " unclaimed sponges! /claim").color(sponge_color);
    }
}
