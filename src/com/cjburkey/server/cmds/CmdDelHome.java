package com.cjburkey.server.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;

public class CmdDelHome extends Cmd {

	public String getName() {
		return "delhome";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /delhome"); return; }
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /delhome"); return; }
		Player ply = (Player)  executor;
		Modules.getModuleHome().delHome(ply.getUniqueId());
		ChatUtil.send(executor, "&2Home deleted!");
	}
	
}