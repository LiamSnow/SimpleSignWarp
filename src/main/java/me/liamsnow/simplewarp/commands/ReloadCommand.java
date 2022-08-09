package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import me.liamsnow.simplewarp.SimpleWarp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		sender.sendMessage("Reloading SimpleWarp Config & Data!");

		ConfigFileHandler.load();
		DataFileHandler.load();

		return true;
	}

}
