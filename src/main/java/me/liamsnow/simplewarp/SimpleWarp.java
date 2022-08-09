package me.liamsnow.simplewarp;

import com.sk89q.worldguard.WorldGuard;
import me.liamsnow.simplewarp.commands.*;
import me.liamsnow.simplewarp.eventhandlers.GriefProtectionEventHandler;
import me.liamsnow.simplewarp.eventhandlers.SignBreakEventHandler;
import me.liamsnow.simplewarp.eventhandlers.SignClickEventHandler;
import me.liamsnow.simplewarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class SimpleWarp extends JavaPlugin {
	public static SimpleWarp instance;
	public static GriefPrevention griefPrevention;

	@Override
	public void onEnable() {
		instance = this;

		//Load Config & Data
		ConfigFileHandler.init();
		DataFileHandler.init();

		//Load GriefPrevention Plugin
		Plugin griefPreventionPlugin = getServer().getPluginManager().getPlugin("GriefPrevention");
		if(griefPreventionPlugin == null || !griefPreventionPlugin.isEnabled()) {
			getLogger().severe("SimpleWarp was unable to find GriefPrevention dependency - Disabling");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		griefPrevention = (GriefPrevention) griefPreventionPlugin;

		//Register Commands
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("stuck").setExecutor(new StuckCommand());
		getCommand("simplewarp-reload").setExecutor(new ReloadCommand());
		getCommand("simplewarp-setspawn").setExecutor(new SetSpawnCommand());
		getCommand("simplewarp-setwarplobby").setExecutor(new SetWarpLobbyCommand());
		getCommand("simplewarp-givewarplobbysign").setExecutor(new GiveWarpLobbySignCommand());

		//Register Event Handlers
		getServer().getPluginManager().registerEvents(new SignClickEventHandler(), this);
		getServer().getPluginManager().registerEvents(new SignBreakEventHandler(), this);
		getServer().getPluginManager().registerEvents(new GriefProtectionEventHandler(), this);
		//TODO remove /sethome on claim abandonment

		//Log
		getLogger().info("Enabled SimpleWarp!");
	}

	@Override
	public void onDisable() {
		ConfigFileHandler.save();
		DataFileHandler.save();
	}
}
