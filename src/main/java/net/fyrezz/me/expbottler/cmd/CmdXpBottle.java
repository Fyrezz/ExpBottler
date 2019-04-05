package net.fyrezz.me.expbottler.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;
import net.fyrezz.me.expbottler.util.ExperienceCalculator;
import net.fyrezz.me.expbottler.util.MagicExpBottle;
import net.fyrezz.me.expbottler.util.MessageSender;

public class CmdXpBottle implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Player command only");
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission(P.p.getConfig().getString("xpbottlepermission"))) {
			MessageSender.sndMsg(player, "nopermission");
			
			return true;
		}
		
		if (args.length < 1) {
			MessageSender.sndMsg(player, "notenoughargs");;
			
			return true;
		}
		
		int expArg;
		
		try {
			expArg = Integer.parseInt(args[0]);
		} catch (NumberFormatException exception) {
			MessageSender.sndMsg(player, "invalidargument");
			
			return true;
		}
		
		if (expArg < P.p.getConfig().getInt("minimumexperience")) {
			MessageSender.sndMsg(player, "minimumexperience", Integer.toString(P.p.getConfig().getInt("minimumexperience")));
			
			return true;
		}
		
		if (expArg > P.p.getConfig().getInt("maximumexperience")) {
			MessageSender.sndMsg(player, "maximumexperience", Integer.toString(P.p.getConfig().getInt("maximumexperience")));
			
			return true;
		}
		
		if (ExperienceCalculator.getTotalExperience(player) < expArg) {
			MessageSender.sndMsg(player, "notenoughexp");
			
			return true;
		}
		/*
		 * TODO: Drop MagicExpBottle if player's empty slots < 1
		 */
		player.getInventory().addItem(new MagicExpBottle(expArg, player.getName()));
		MessageSender.sndMsg(player, "bottlecreated");
		
		return false;
	}

}
