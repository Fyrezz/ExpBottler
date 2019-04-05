package net.fyrezz.me.expbottler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.fyrezz.me.expbottler.cmd.XpBottleCommand;
import net.fyrezz.me.expbottler.listeners.PlayerListener;
import net.md_5.bungee.api.ChatColor;

public class ExpBottler
  extends JavaPlugin
{
  public static ExpBottler instance;
  public static final String expBottleName = ChatColor.AQUA + "" + ChatColor.BOLD + "Magic EXP Bottle " + ChatColor.RESET + ChatColor.GRAY + "(Right Click!)";
  
  public ExpBottler()
  {
    instance = this;
  }
  
  public void onEnable()
  {
    getCommand("xpbottle").setExecutor(new XpBottleCommand());
    
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);
  }
  
  public void onDisable() {}
  
  public ItemStack createExpBottle(int exp, String enchanter)
  {
    ItemStack expBottle = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
    ItemMeta im = expBottle.getItemMeta();
    List<String> lore = new ArrayList<String>();
    
    String name = expBottleName;
    im.setDisplayName(name);
    lore.add(ChatColor.LIGHT_PURPLE + "Value" + ChatColor.RESET + " " + Integer.toString(exp));
    lore.add(ChatColor.LIGHT_PURPLE + "Enchanter " + ChatColor.RESET + enchanter);
    im.setLore(lore);
    expBottle.setItemMeta(im);
    
    return expBottle;
  }
  
  public void giveExpBottle(Player player, int exp)
  {
    int remainingexp = ExperienceManager.getTotalExperience(player) - exp;
    ExperienceManager.setTotalExperience(player, remainingexp);
    ItemStack expBottle = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
    ItemMeta im = expBottle.getItemMeta();
    List<String> lore = new ArrayList<String>();
    
    String name = expBottleName;
    im.setDisplayName(name);
    lore.add(ChatColor.LIGHT_PURPLE + "Value" + ChatColor.RESET + " " + Integer.toString(exp));
    lore.add(ChatColor.LIGHT_PURPLE + "Enchanter " + ChatColor.RESET + player.getName());
    im.setLore(lore);
    expBottle.setItemMeta(im);
    player.getInventory().addItem(new ItemStack[] { expBottle });
    player.sendMessage(ChatColor.DARK_GREEN + "Bottle received!");
  }
}
