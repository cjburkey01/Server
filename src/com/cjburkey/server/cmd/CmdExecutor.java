package com.cjburkey.server.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExecutor implements CommandExecutor {
	
	private Cmd cmd;
	
	public CmdExecutor(Cmd cmd) {
		this.cmd = cmd;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String cmds, String[] args) {
		this.cmd.onCall(sender, args);
		return true;
	}
	
}