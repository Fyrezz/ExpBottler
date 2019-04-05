package net.fyrezz.me.expbottler.util;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.fyrezz.me.expbottler.P;

public class MagicExpBottle extends ItemStack {
	
	private int experience;
	private List<String> lore = new ArrayList<String>();
	
	public MagicExpBottle(int experience) {
		this.experience = experience;
		this.setType(Material.EXPERIENCE_BOTTLE);
		this.setAmount(1);
		this.getItemMeta().setDisplayName(P.lang.getConfig().getString("bottlename"));
	}
	
	public ItemStack getItemStack() {
		return this;
	}

}
