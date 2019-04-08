package net.fyrezz.me.expbottler.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;

public class MessageSender {

	public static void sndMsg(Player player, String path) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', P.p.lang.getString("prefix")) + ChatColor.RESET
				+ " " + ChatColor.translateAlternateColorCodes('&', P.p.lang.getString(path)));

	}

	public static void sndMsg(Player player, String path, String extra) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', P.p.lang.getString("prefix")) + ChatColor.RESET
				+ " " + ChatColor.translateAlternateColorCodes('&', P.p.lang.getString(path)) + " "
				+ ChatColor.translateAlternateColorCodes('&', extra));

	}

	public static void sndCmdResponse(CommandSender sender, String path) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', P.p.lang.getString("prefix")) + ChatColor.RESET
				+ " " + ChatColor.translateAlternateColorCodes('&', P.p.lang.getString(path)));

	}

	public static void sndCmdResponse(CommandSender sender, String path, String extra) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', P.p.lang.getString("prefix")) + ChatColor.RESET
				+ " " + ChatColor.translateAlternateColorCodes('&', P.p.lang.getString(path)) + " "
				+ ChatColor.translateAlternateColorCodes('&', extra));

	}

}
