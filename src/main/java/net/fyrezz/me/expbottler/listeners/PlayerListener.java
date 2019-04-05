package net.fyrezz.me.expbottler.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.fyrezz.me.expbottler.util.ExperienceCalculator;
import net.fyrezz.me.expbottler.util.MagicExpBottle;

public class PlayerListener
  implements Listener
{
  @EventHandler
  public void onRightClick(PlayerInteractEvent event)
  {
    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.EXPERIENCE_BOTTLE)
    {
      Player player = event.getPlayer();
      ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
      if (im.getDisplayName().contentEquals(MagicExpBottle.getDisplayName())) {
        event.setCancelled(true);
        
        String strexp = (String)im.getLore().get(0);
        String exps = strexp.replaceAll("[^0-9]", "");
        int exp = Integer.parseInt(exps);
        ItemStack remove = event.getPlayer().getInventory().getItemInMainHand();
        remove.setAmount(remove.getAmount() - 1);
        player.getInventory().setItemInMainHand(remove);
        player.sendMessage("WHOOSH");
        ExperienceCalculator.setTotalExperience(player, ExperienceCalculator.getTotalExperience(player) + exp - 2);
      }
    }
    else {}
  }
}
