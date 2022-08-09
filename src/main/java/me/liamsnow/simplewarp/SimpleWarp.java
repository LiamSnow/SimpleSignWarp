package me.liamsnow.simplewarp;

import me.liamsnow.simplewarp.commands.*;
import me.liamsnow.simplewarp.eventhandlers.SignClickEventHandler;
import me.liamsnow.simplewarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleWarp extends JavaPlugin {
	public static SimpleWarp instance;

	@Override
	public void onEnable() {
		instance = this;

		//Load Config & Data
		ConfigFileHandler.init();
		DataFileHandler.init();

		//Register Commands
		getCommand("warp-reload").setExecutor(new ReloadCommand());
		getCommand("warp-set-location").setExecutor(new SetLocationCommand());
		getCommand("warp-set-location").setTabCompleter(new WarpNameCompleter());
		getCommand("warp-list").setExecutor(new ListCommand());
		getCommand("warp-delete").setExecutor(new DeleteCommand());
		getCommand("warp-delete").setTabCompleter(new WarpNameCompleter());
		getCommand("warp-get-sign").setExecutor(new GetSignCommand());
		getCommand("warp-get-sign").setTabCompleter(new WarpNameCompleter());

		//Register Event Handlers
		getServer().getPluginManager().registerEvents(new SignClickEventHandler(), this);

		//Log
		getLogger().info("Enabled SimpleWarp!");
	}

	@Override
	public void onDisable() {
		ConfigFileHandler.save();
		DataFileHandler.save();
	}
}
