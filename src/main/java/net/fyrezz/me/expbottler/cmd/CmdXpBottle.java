package net.fyrezz.me.expbottler.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;
import net.fyrezz.me.expbottler.util.MagicExpBottle;
import net.fyrezz.me.expbottler.util.MessageSender;

public class CmdXpBottle implements CommandExecutor {

	private static final String correctExecution = "&c/xpbottle [amountxp]";

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Player command only");

			return true;
		}

		Player player = (Player) sender;

		if (!player.hasPermission(P.p.getConfig().getString("xpbottlepermission"))) {
			MessageSender.sndMsg(player, "nopermission");

			return true;
		}

		if (args.length < 1) {
			MessageSender.sndMsg(player, "notenoughargs", correctExecution);

			return true;
		}

		int expArg;

		try {
			expArg = Integer.parseInt(args[0]);
		} catch (NumberFormatException exception) {
			MessageSender.sndMsg(player, "invalidargument", correctExecution);

			return true;
		}

		if (expArg < P.p.getConfig().getInt("minimumexperience")) {
			MessageSender.sndMsg(player, "minimumexperience",
					Integer.toString(P.p.getConfig().getInt("minimumexperience")));

			return true;
		}

		if (expArg > P.p.getConfig().getInt("maximumexperience")) {
			MessageSender.sndMsg(player, "maximumexperience",
					Integer.toString(P.p.getConfig().getInt("maximumexperience")));

			return true;
		}

		int playerExp = Math.round(P.p.experienceManager.getExpToLevel(player.getLevel())
				+ P.p.experienceManager.getExpForProgressToNextLevel(player.getExp(), player.getLevel()));

		if (playerExp < expArg) {
			MessageSender.sndMsg(player, "notenoughexp");

			return true;
		}
		/*
		 * TODO: Drop MagicExpBottle if player's empty slots < 1
		 */
		P.p.experienceManager.createBottle(player, expArg);

		MessageSender.sndMsg(player, "bottlecreated", Integer.toString(expArg) + " EXP!");

		player.getInventory().addItem(new MagicExpBottle(expArg, player.getName()));

		return false;
	}

}
