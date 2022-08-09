package me.liamsnow.simplewarp;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class Util {

	public static void warp(Player player, Location location, String desc) {
		player.teleport(location);
		Location particleLocation = player.getLocation();
		particleLocation = particleLocation.add(0, 1.5, 0);
		player.spawnParticle(Particle.CLOUD, particleLocation, 50, 0.5, 0.1, 0.5, 0.001);
		player.sendMessage(ChatColor.GREEN + "Warped to " + ChatColor.GOLD + "" + ChatColor.BOLD + desc);
	}

	public static double round(double num, int places) {
		double fac = Math.pow(10, places);
		return Math.round(num * fac) / fac;
	}
}
