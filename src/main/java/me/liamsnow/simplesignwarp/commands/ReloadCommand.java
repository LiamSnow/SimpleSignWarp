package me.liamsnow.simplesignwarp.commands;

import me.liamsnow.simplesignwarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplesignwarp.filehandlers.DataFileHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		ConfigFileHandler.load();
		DataFileHandler.load();

		sender.sendMessage(ChatColor.YELLOW + "Reloaded SimpleSignWarp Config & Data!");

		return true;
	}

}
