package com.cjburkey.server.cmd;

import org.bukkit.command.CommandSender;

public abstract class Cmd {
	
	public abstract String getName();
	public abstract void onCall(CommandSender executor, String[] args);
	
	public final String getLowered() { return getName().toLowerCase(); }
	
}