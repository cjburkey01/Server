package com.cjburkey.server.cmds;

import java.util.UUID;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;
import com.cjburkey.server.pos.ChunkPosition;

public class CmdWhoClaim extends Cmd {

	public String getName() {
		return "whoclaimed";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(!(executor instanceof Player)) { ChatUtil.send(executor, "&4You must be in game to use /" + getLowered()); return; }
		if(args.length != 0) { ChatUtil.send(executor, "&4Usage: /" + getLowered()); return; }
		Player ply = (Player)  executor;
		UUID claimed = Modules.getModuleClaim().getChunkOwner(new ChunkPosition(ply.getLocation()));
		if(claimed != null) {
			String plyN = Modules.getModuleCacher().fromUUID(claimed);
			if(plyN != null) {
				ChatUtil.send(ply, "&2Chunk owned by: " + plyN);
				return;
			}
		}
		ChatUtil.send(ply, "&4Chunk not owned.");
	}
	
}