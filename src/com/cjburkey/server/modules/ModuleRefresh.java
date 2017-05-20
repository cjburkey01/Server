package com.cjburkey.server.modules;

import org.bukkit.command.CommandSender;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.cmds.CmdRefresh;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.event.EventPlayerCache;
import com.cjburkey.server.module.Module;

public class ModuleRefresh extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	private CmdRefresh cmdRefresh;
	
	public void refresh(CommandSender exec) {
		Logger.log("Reloading...");
		ChatUtil.send(exec, "&2Reloading...");
		MainServer.instance.deinit();
		ChatUtil.send(exec, "&2Unloaded.");
		MainServer.instance.init();
		ChatUtil.send(exec, "&2Reloaded. Done refreshing.");
		Logger.log("Reloaded.");
	}
	
	public String getName() {
		return "ModuleRefresh";
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
		MainServer.instance.getServer().getPluginManager().registerEvents(new EventPlayerCache(), MainServer.instance);
		data = new ModuleDataHandler(this);
		cmds = new CommandHandler();
		cmdRefresh = new CmdRefresh();
		
		cmds.addCmd(cmdRefresh);
		
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}