package com.cjburkey.server.cmds;

import org.bukkit.command.CommandSender;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;

public class CmdRefresh extends Cmd {

	public String getName() {
		return "refresh";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /" + getLowered()); return; }
		Modules.getModuleRefresh().refresh(executor);
	}
	
}