package me.liamsnow.simplesignwarp.commands;

import me.liamsnow.simplesignwarp.filehandlers.DataFileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class WarpNameCompleter implements TabCompleter {
	@Nullable
	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (args.length == 1) {
			return DataFileHandler.getAllWarpNames();
		}
		return Arrays.asList();
	}
}
