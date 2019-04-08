package net.fyrezz.me.expbottler.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import net.fyrezz.me.expbottler.P;
import net.fyrezz.me.expbottler.util.MagicExpBottle;
import net.fyrezz.me.expbottler.util.MessageSender;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if (event.getHand() == null || event.getHand().equals(EquipmentSlot.OFF_HAND) || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
	        return; // ignore offhand 2nd packet
	    }
	    
	    if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contentEquals(MagicExpBottle.getDisplayName())) {
    	event.setCancelled(true);
    	
    	Player player = event.getPlayer();
    	
    	//Get 1st line of lore
        String strexp = (String)event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
        //Remove all chars except ints
        String exps = strexp.replaceAll("[^0-9]", "");
        int exp = Integer.parseInt(exps);
        
        //Remove 1 of item from player's hand
        ItemStack remove = event.getPlayer().getInventory().getItemInMainHand();
        remove.setAmount(remove.getAmount() - 1);
        player.getInventory().setItemInMainHand(remove);
        
        P.p.experienceManager.redeemBottle(player, exp);
        
        // Player never knows he loses extra -1 :)
			MessageSender.sndMsg(player, "bottleredeemed",
					"&a&n+ " + exp + " EXP!&7 &o(-" + P.p.config.getInt("redeemtax") + " tax.)");
		}
	}
}
