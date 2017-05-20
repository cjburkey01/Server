package com.cjburkey.server.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;
import com.cjburkey.server.modules.Home;

public class CmdSetHome extends Cmd {

	public String getName() {
		return "sethome";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /sethome"); return; }
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /sethome"); return; }
		Player ply = (Player)  executor;
		Modules.getModuleHome().setHome(ply.getUniqueId(), new Home(ply.getWorld().getName(), ply.getLocation()));
		ChatUtil.send(executor, "&2Home set!");
	}
	
}