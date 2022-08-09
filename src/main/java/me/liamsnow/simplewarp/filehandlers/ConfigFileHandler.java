package me.liamsnow.simplewarp.filehandlers;

import me.liamsnow.simplewarp.SimpleWarp;
import me.liamsnow.simplewarp.Util;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.liamsnow.simplewarp.Constants.CONFIG_FILE_NAME;
import static me.liamsnow.simplewarp.Constants.FORCE_OVERWRITE_CONFIG_FILE;

public class ConfigFileHandler {

	private static File file;
	private static FileConfiguration config;
	private static SimpleWarp instance;

	public static void init() {
		instance = SimpleWarp.instance;
		load();
	}

	public static void load() {
		file = new File(instance.getDataFolder(), CONFIG_FILE_NAME);
		boolean fileExists = file.exists();

		//Make Folder for Config File
		if (!fileExists) {
			file.getParentFile().mkdirs();
		}

		//Create Config File (& Overwrite if Asked)
		if (FORCE_OVERWRITE_CONFIG_FILE || !fileExists) {
			instance.saveResource(CONFIG_FILE_NAME, FORCE_OVERWRITE_CONFIG_FILE);
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			SimpleWarp.instance.getLogger().severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}