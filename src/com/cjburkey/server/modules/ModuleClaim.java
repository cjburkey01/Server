package com.cjburkey.server.modules;

import java.util.UUID;
import org.bukkit.entity.Player;
import com.cjburkey.server.Logger;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.cmds.CmdClaimChunk;
import com.cjburkey.server.cmds.CmdUnclaimChunk;
import com.cjburkey.server.cmds.CmdWhoClaim;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.module.Module;
import com.cjburkey.server.module.Modules;
import com.cjburkey.server.pos.ChunkPosition;

public class ModuleClaim extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	private CmdClaimChunk cmdClaim;
	private CmdUnclaimChunk cmdUnClaim;
	private CmdWhoClaim cmdWhoClaim;
	
	public UUID getChunkOwner(ChunkPosition pos) {
		data.loadFromDisk();
		String uuid = data.get(pos.toString());
		if(uuid != null) {
			UUID ply = UUID.fromString(uuid);
			if(ply != null) return ply;
		}
		return null;
	}
	
	public boolean isChunkOwned(ChunkPosition pos) {
		return getChunkOwner(pos) != null;
	}
	
	public boolean claimChunk(Player ply) {
		ChunkPosition p = new ChunkPosition(ply.getLocation());
		if(isChunkOwned(p)) return false;
		data.set(p.toString(), ply.getUniqueId().toString());
		data.saveToDisk();
		return true;
	}
	
	public boolean unclaimChunk(Player ply) {
		ChunkPosition p = new ChunkPosition(ply.getLocation());
		if(!isChunkOwned(p)) { Logger.log("Chunk is not owned."); return false; }
		if(!getChunkOwner(p).equals(ply.getUniqueId())) { Logger.log("Wrong player."); return false; }
		data.unset(p.toString());
		data.saveToDisk();
		return true;
	}
	
	public String getName() {
		return "ModuleClaim";
	}

	public Module[] getRequiredModules() {
		return new Module[] { Modules.getModuleCacher() };
	}

	public ModuleDataHandler getDataHandler() {
		return data;
	}

	public CommandHandler getCommandHandler() {
		return cmds;
	}

	public void onLoad() {
		data = new ModuleDataHandler(this);
		cmds = new CommandHandler();
		cmdClaim = new CmdClaimChunk();
		cmdUnClaim = new CmdUnclaimChunk();
		cmdWhoClaim = new CmdWhoClaim();
		
		cmds.addCmd(cmdClaim);
		cmds.addCmd(cmdUnClaim);
		cmds.addCmd(cmdWhoClaim);
		
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}