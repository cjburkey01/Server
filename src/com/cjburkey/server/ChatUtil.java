package com.cjburkey.server;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {
	
	public static final void send(CommandSender to, String msg) {
		to.sendMessage(color(msg));
	}
	
	public static final String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}