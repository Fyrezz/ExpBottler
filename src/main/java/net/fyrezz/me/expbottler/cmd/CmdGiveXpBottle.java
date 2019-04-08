package net.fyrezz.me.expbottler.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;
import net.fyrezz.me.expbottler.util.MagicExpBottle;
import net.fyrezz.me.expbottler.util.MessageSender;

public class CmdGiveXpBottle implements CommandExecutor {

	private static final String correctExecution = "&c/givexpbottle [player] [amountxp]";

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player && !(sender.hasPermission("givexpbottlepermission"))) {
			MessageSender.sndCmdResponse((Player) sender, "nopermission");

			return true;
		}

		if (args.length < 2) {
			MessageSender.sndCmdResponse(sender, "notenoughargs", correctExecution);

			return true;
		}

		Player playerArg = Bukkit.getPlayer(args[0]);

		if (!(playerArg.isOnline())) {
			MessageSender.sndCmdResponse(sender, "playernotonline", correctExecution);

			return true;
		}

		int expArg;

		try {
			expArg = Integer.parseInt(args[1]);
		} catch (NumberFormatException exception) {
			MessageSender.sndCmdResponse(sender, "invalidargument", correctExecution);

			return true;
		}

		if (expArg < P.p.getConfig().getInt("minimumexperience")) {
			MessageSender.sndCmdResponse(sender, "minimumexperience",
					" " + Integer.toString(P.p.getConfig().getInt("minimumexperience")));

			return true;
		}

		if (expArg > P.p.getConfig().getInt("maximumexperience")) {
			MessageSender.sndCmdResponse(sender, "maximumexperience",
					" " + Integer.toString(P.p.getConfig().getInt("maximumexperience")));

			return true;
		}
		/*
		 * TODO: Drop MagicExpBottle if playerArg's empty slots < 1
		 */
		playerArg.getInventory().addItem(new MagicExpBottle(expArg, playerArg.getName()));

		MessageSender.sndMsg(playerArg, "bottlereceived", Integer.toString(expArg) + " EXP from " + sender.getName());
		MessageSender.sndCmdResponse(sender, "bottlegiven",
				Integer.toString(expArg) + " EXP to " + playerArg.getName());

		return false;
	}

}
