package net.fyrezz.me.expbottler.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.fyrezz.me.expbottler.P;
import net.md_5.bungee.api.ChatColor;

public class MagicExpBottle extends ItemStack {
	
	private static String displayName = P.p.getLang().getString("bottlename");
	private static int amount = 1;
	private static Material type = Material.EXPERIENCE_BOTTLE;
	private ArrayList<String> lore = new ArrayList<String>();
	
	public MagicExpBottle(int experience, String enchanter) {
		this.setType(type);
		this.setAmount(amount);
		this.getItemMeta().setDisplayName(displayName);
		lore.add(ChatColor.DARK_PURPLE + "Value " + ChatColor.RESET + experience);
		lore.add(ChatColor.DARK_PURPLE + enchanter);
		this.getItemMeta().setLore(lore);
	}
	
	public static String getDisplayName() {
		return displayName;
	}

}
