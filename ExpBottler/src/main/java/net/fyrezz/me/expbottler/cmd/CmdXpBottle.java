package net.fyrezz.me.expbottler.cmd;

import net.fyrezz.me.expbottler.ExpBottler;
import net.fyrezz.me.expbottler.util.ExperienceCalculator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdXpBottle
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      
      if (args.length < 1) {
        player.sendMessage(ChatColor.DARK_RED + "You must introduce EXP amount: /xpbottle <amount>");
        
        return true;
      }
      
      int expArg;
      
      try {
        expArg = Integer.parseInt(args[0]);
      }
      catch (NumberFormatException exception) {
        player.sendMessage(ChatColor.DARK_RED + "Invalid amount!");
        
        return true;
      }
      
      if (expArg < 100)
      {
        player.sendMessage(ChatColor.DARK_RED + "Minimum 100 EXP");
        
        return true;
      }
      if (expArg > 32000)
      {
        player.sendMessage(ChatColor.DARK_RED + "Maxium 32,000 EXP");
        
        return true;
      }
      if (ExperienceCalculator.getTotalExperience(player) < expArg)
      {
        player.sendMessage(ChatColor.DARK_RED + "You don't have enough EXP!");
        
        return true;
      }
      ExpBottler.instance.giveExpBottle(player, expArg);
      player.sendMessage(ChatColor.YELLOW + "Creating " + Integer.toString(expArg) + "EXP ExpBottle...");
      
      return true;
    }
    sender.sendMessage("Player command only!");
    
    return true;
  }
}
