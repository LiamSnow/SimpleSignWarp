package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.filehandlers.DataFileHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class ListCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		List<String> warpNames = DataFileHandler.getAllWarpNames();
		StringBuilder message = new StringBuilder(ChatColor.YELLOW + "Warps:\n");

		for (String warpName : warpNames) {
			Location warpLocation = DataFileHandler.getWarpLocation(warpName);
			message.append(" - ");
			message.append(warpName);
			message.append(": {");

			if (warpLocation != null) {
				message.append("world:");
				message.append(warpLocation.getWorld().getName());
				message.append(",x:");
				message.append(warpLocation.getX());
				message.append(",y:");
				message.append(warpLocation.getY());
				message.append(",z:");
				message.append(warpLocation.getZ());
				message.append(",yaw:");
				message.append(warpLocation.getYaw());
				message.append(",pitch:");
				message.append(warpLocation.getPitch());
			}
			else {
				message.append("CORRUPT DATA");
			}

			message.append("}\n");
		}

		sender.sendMessage(message.toString());

		return true;
	}

}
