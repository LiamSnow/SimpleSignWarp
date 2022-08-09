package me.liamsnow.simplesignwarp;

import me.liamsnow.simplesignwarp.commands.*;
import me.liamsnow.simplesignwarp.eventhandlers.SignClickEventHandler;
import me.liamsnow.simplesignwarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplesignwarp.filehandlers.DataFileHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleSignWarp extends JavaPlugin {
	public static SimpleSignWarp instance;

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
		getLogger().info("Enabled SimpleSignWarp!");
	}

	@Override
	public void onDisable() {
		ConfigFileHandler.save();
		DataFileHandler.save();
	}
}
