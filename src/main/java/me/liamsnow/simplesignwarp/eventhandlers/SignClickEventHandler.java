package me.liamsnow.simplesignwarp.eventhandlers;

import me.liamsnow.simplesignwarp.Constants;
import me.liamsnow.simplesignwarp.SimpleSignWarp;
import me.liamsnow.simplesignwarp.Util;
import me.liamsnow.simplesignwarp.filehandlers.DataFileHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SignClickEventHandler implements Listener {

	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		//Get Event Data
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block signBlock = event.getClickedBlock();

		//Checks
		if (action != Action.RIGHT_CLICK_BLOCK) return;
		if (signBlock == null) return;
		if (!(signBlock.getState() instanceof Sign)) return;

		//Get Sign
		Sign sign = (Sign) signBlock.getState();

		//Check if Warp
		PersistentDataContainer signPersistentData = sign.getPersistentDataContainer();
		String warpName = signPersistentData.getOrDefault(new NamespacedKey(SimpleSignWarp.instance, Constants.PERSISTENT_DATA_WARP_KEY), PersistentDataType.STRING, null);

		//Warp
		if (warpName != null) {
			Location warpLocation = DataFileHandler.getWarpLocation(warpName);

			if (warpLocation == null) {
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Invalid Warp");
				SimpleSignWarp.instance.getLogger().warning("Error: Invalid SimpleSignWarp Warp");
			}
			else {
				Util.warp(player, warpLocation, warpName + "!");
			}
		}
	}
}
