package com.cjburkey.server.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;

public class CmdClaimChunk extends Cmd {

	public String getName() {
		return "claim";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /" + getLowered()); return; }
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /" + getLowered()); return; }
		Player ply = (Player)  executor;
		boolean worked = Modules.getModuleClaim().claimChunk(ply);
		ChatUtil.send(ply, (worked) ? "&2You have claimed this chunk!" : "&4This chunk is already claimed.");
	}
	
}