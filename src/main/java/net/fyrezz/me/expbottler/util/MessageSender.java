package net.fyrezz.me.expbottler.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.fyrezz.me.expbottler.P;

public class MessageSender {
	
	public static void sndMsg(Player player, String path) {
	    player.sendMessage(ChatColor.translateAlternateColorCodes('&', P.lang.getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', P.lang.getConfig().getString(path)));

	}
	
	public static void sndMsg(Player player, String path, String extra) {
	    player.sendMessage(ChatColor.translateAlternateColorCodes('&', P.lang.getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', P.lang.getConfig().getString(path)) + " " + ChatColor.translateAlternateColorCodes('&', extra));

	}

}
