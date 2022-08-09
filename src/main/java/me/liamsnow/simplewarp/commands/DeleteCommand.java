package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DeleteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		//Get Warp Name
		if (args.length < 1) { sender.sendMessage(ChatColor.RED + "Error: You need to provide a warp name!"); return true; }
		String warpName = args[0];

		//Delete
		DataFileHandler.deleteWarp(warpName);

		//Send Message
		sender.sendMessage(ChatColor.RED + "Deleted warp: " + warpName);

		return true;
	}

}
