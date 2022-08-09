package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLocationCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		//Get Warp Name
		if (args.length < 1) { player.sendMessage(ChatColor.RED + "Error: You need to provide a warp name!"); return true; }
		String warpName = args[0];

		DataFileHandler.setWarpLocation(warpName, player.getLocation());

		sender.sendMessage(ChatColor.GREEN + "Set warp location for " + warpName + "!");

		return true;
	}

}
