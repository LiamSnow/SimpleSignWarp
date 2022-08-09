package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.filehandlers.ConfigFileHandler;
import me.liamsnow.simplewarp.SimpleWarp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarpLobbyCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		ConfigFileHandler.setWarpLobbyLocation(player.getLocation());
		player.sendMessage(ChatColor.GREEN + "Set SimpleWarp Warp Lobby!");

		return true;
	}
}
