package net.fyrezz.me.expbottler.util;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.fyrezz.me.expbottler.P;
import net.md_5.bungee.api.ChatColor;

public class MagicExpBottle extends ItemStack {
	
	private static String displayName = ChatColor.translateAlternateColorCodes('&', P.p.lang.getString("bottlename"));
	private int amount = 1;
	private ArrayList<String> lore = new ArrayList<String>();
	
	public MagicExpBottle(int experience, String enchanter) {
		this.setType(XMaterial.EXPERIENCE_BOTTLE.parseMaterial()); //1.8 - 1.14 compat
		this.setAmount(amount);
		
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(displayName);
		lore.add(ChatColor.LIGHT_PURPLE + "Value " + ChatColor.RESET + experience + " EXP");
		lore.add(ChatColor.LIGHT_PURPLE + "Enchanter " + ChatColor.RESET + enchanter);
		im.setLore(lore);
		this.setItemMeta(im);
	}
	
	public static String getDisplayName() {
		return displayName;
	}

}
