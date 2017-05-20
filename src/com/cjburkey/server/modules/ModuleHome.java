package com.cjburkey.server.modules;

import java.util.UUID;
import org.bukkit.entity.Player;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.cmds.CmdDelHome;
import com.cjburkey.server.cmds.CmdHome;
import com.cjburkey.server.cmds.CmdSetHome;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.module.Module;
import com.cjburkey.server.module.Modules;

public class ModuleHome extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	private CmdSetHome cmdSetHome;
	private CmdHome cmdHome;
	private CmdDelHome cmdDelHome;
	
	public Home getHome(UUID ply) {
		data.loadFromDisk();
		String stored = data.get(ply.toString());
		if(stored != null) {
			return Home.fromString(stored);
		}
		return null;
	}
	
	public void setHome(UUID ply, Home home) {
		data.set(ply.toString(), home.toString());
		data.saveToDisk();
	}
	
	public void delHome(UUID ply) {
		data.unset(ply.toString());
		data.saveToDisk();
	}
	
	public boolean teleportToHome(Player player) {
		Home home = getHome(player.getUniqueId());
		if(home != null) {
			return home.teleport(player);
		}
		return false;
	}
	
	public String getName() {
		return "ModuleHome";
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
		cmdSetHome = new CmdSetHome();
		cmdHome = new CmdHome();
		cmdDelHome = new CmdDelHome();
		
		cmds.addCmd(cmdSetHome);
		cmds.addCmd(cmdHome);
		cmds.addCmd(cmdDelHome);
		
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}