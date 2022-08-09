package me.liamsnow.simplewarp.commands;

import me.liamsnow.simplewarp.Constants;
import me.liamsnow.simplewarp.SimpleWarp;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GetSignCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		//Get Warp Name
		if (args.length < 1) { player.sendMessage(ChatColor.RED + "Error: You need to provide a warp name!"); return true; }
		String warpName = args[0];

		//Create Sign Item
        ItemStack signItem = new ItemStack(Constants.WARP_SIGN_MATERIAL, 1);
		ItemMeta signItemMeta = signItem.getItemMeta();

		//Set Name + Lore
		signItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Warp " + warpName + " Sign");
		signItemMeta.setLore(Arrays.asList("Place this sign so players", "can use the " + warpName + " warp!"));

		//Get Block Data
		BlockStateMeta signBlockStateMeta = (BlockStateMeta) signItemMeta;
		Sign signBlockState = (Sign) signBlockStateMeta.getBlockState();

		//Tag Sign with Warp Name
		PersistentDataContainer signPersistentData = signBlockState.getPersistentDataContainer();
		signPersistentData.set(new NamespacedKey(SimpleWarp.instance, Constants.PERSISTENT_DATA_WARP_KEY), PersistentDataType.STRING, warpName);

		//Edit Sign Text
		signBlockState.setLine(1, ChatColor.GREEN + "" + ChatColor.BOLD + "[Warp]");
		signBlockState.setLine(2, ChatColor.BLUE + warpName);
		signBlockState.update();

		//Save Item & Block Data
		signBlockStateMeta.setBlockState(signBlockState);
		signItem.setItemMeta(signBlockStateMeta);

		//Add Item to Player's Inventory
		player.getInventory().addItem(signItem);

		//Send message to Player
		player.sendMessage(ChatColor.GREEN + "Given Warp " + warpName + " Sign. " + ChatColor.GRAY + "" + ChatColor.ITALIC +
				                   "Place this sign so players can use the " + warpName + " warp!");

		return true;
	}
}
