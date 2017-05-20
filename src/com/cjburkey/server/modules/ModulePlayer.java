package com.cjburkey.server.modules;

import java.util.UUID;
import org.bukkit.entity.Player;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.event.EventPlayerJoin;
import com.cjburkey.server.module.Module;

public class ModulePlayer extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	
	public UUID fromName(String username) {
		data.loadFromDisk();
		String out = data.get(username);
		if(out != null) return UUID.fromString(out);
		return null;
	}
	
	public void join(Player ply) {
		Logger.log(ply.getDisplayName() + " has joined.");
		data.set(ply.getDisplayName(), ply.getUniqueId().toString());
		data.saveToDisk();
	}
	
	public String getName() {
		return "ModuleCacher";
	}

	public Module[] getRequiredModules() {
		return new Module[] {  };
	}

	public ModuleDataHandler getDataHandler() {
		return data;
	}

	public CommandHandler getCommandHandler() {
		return cmds;
	}

	public void onLoad() {
		MainServer.instance.getServer().getPluginManager().registerEvents(new EventPlayerJoin(), MainServer.instance);
		data = new ModuleDataHandler(this);
		cmds = new CommandHandler();
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}