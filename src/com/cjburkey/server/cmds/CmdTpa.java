package com.cjburkey.server.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;

public class CmdTpa extends Cmd {

	public String getName() {
		return "tpa";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /" + getLowered()); return; }
		if(args.length != 1) { ChatUtil.send(executor, "&4Usage: /" + getLowered() + " <player>"); return; }
		Player ply = (Player) executor;
		Modules.getModuleTeleport().addRequest(ply, Bukkit.getServer().getPlayer(args[0].trim()));
		ChatUtil.send(executor, "&2Request sent!");
	}
	
}