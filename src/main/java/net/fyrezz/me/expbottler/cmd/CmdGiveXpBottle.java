package net.fyrezz.me.expbottler.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;
import net.fyrezz.me.expbottler.util.ExperienceCalculator;
import net.fyrezz.me.expbottler.util.MagicExpBottle;
import net.fyrezz.me.expbottler.util.MessageSender;

public class CmdGiveXpBottle implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player && !(sender.hasPermission("givexpbottlepermission"))) {
			MessageSender.sndMsg((Player) sender, "nopermission");
			
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length < 2) {
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
		
		Player playerArg = Bukkit.getPlayer(args[1]);
		
		if (!(playerArg.isOnline())) {
			MessageSender.sndMsg(player, "playernotonline");
			
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
		/*
		 * TODO: Drop MagicExpBottle if playerArg's empty slots < 1
		 */
		playerArg.getInventory().addItem(new MagicExpBottle(expArg, playerArg.getName()));
		MessageSender.sndMsg(playerArg, "bottlereceived");
		MessageSender.sndMsg(player, "bottlegiven");
		
		return false;
	}

}
