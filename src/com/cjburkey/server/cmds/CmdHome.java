package com.cjburkey.server.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;

public class CmdHome extends Cmd {

	public String getName() {
		return "home";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /home"); return; }
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /home"); return; }
		Player ply = (Player)  executor;
		boolean worked = Modules.getModuleHome().teleportToHome(ply);
		ChatUtil.send(executor, (worked) ? "&2Teleported!" : "&4Home not set. Use /sethome to set your home.");
	}
	
}