package me.liamsnow.simplesignwarp.filehandlers;

import me.liamsnow.simplesignwarp.SimpleSignWarp;
import me.liamsnow.simplesignwarp.Util;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static me.liamsnow.simplesignwarp.Constants.DATA_FILE_NAME;

public class DataFileHandler {

	private static File file;
	private static FileConfiguration data;

	public static void init() {
		load();
	}

	public static void load() {
		file = new File(SimpleSignWarp.instance.getDataFolder(), DATA_FILE_NAME);

		//Make Folder for Config File
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			SimpleSignWarp.instance.saveResource(DATA_FILE_NAME, false);
		}

		data = YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		try {
			data.save(file);
		} catch (IOException e) {
			SimpleSignWarp.instance.getLogger().severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private static Location readLocation(String key) {
		String worldUUIDString = data.getString(key + ".world", null);
		if (worldUUIDString == null) return null;
		UUID worldUUID = UUID.fromString(worldUUIDString);
		World world = SimpleSignWarp.instance.getServer().getWorld(worldUUID);

		double x = data.getDouble(key + ".x", Double.MAX_VALUE);
		double y = data.getDouble(key + ".y", Double.MAX_VALUE);
		double z = data.getDouble(key + ".z", Double.MAX_VALUE);
		float yaw = (float) data.getDouble(key + ".yaw", Double.MAX_VALUE);
		float pitch = (float) data.getDouble(key + ".pitch", Double.MAX_VALUE);
		if (Arrays.asList(x, y, z, yaw, pitch).contains(Double.MAX_VALUE)) return null;

		return new Location(world, x, y, z, yaw, 0);
	}
	private static void saveLocation(String key, Location location) {
		data.set(key + ".world", location.getWorld().getUID().toString());
		data.set(key + ".x", Util.round(location.getX(), 1));
		data.set(key + ".y", Util.round(location.getY(), 1));
		data.set(key + ".z", Util.round(location.getZ(), 1));
		data.set(key + ".yaw", Util.round(location.getYaw(), 1));
		data.set(key + ".pitch", Util.round(location.getPitch(), 1));
	}

	public static void setWarpLocation(String name, Location location) {
		saveLocation(name, location);
	}

	public static Location getWarpLocation(String name) {
		return readLocation(name);
	}

	public static void deleteWarp(String name) {
		data.set(name, null);
	}

	public static List<String> getAllWarpNames() {
		List<String> warpNames = new ArrayList<String>();
		warpNames.addAll(data.getKeys(false));
		return warpNames;
	}
}
