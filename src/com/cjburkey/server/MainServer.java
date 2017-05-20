package com.cjburkey.server;

import org.bukkit.plugin.java.JavaPlugin;
import com.cjburkey.server.module.Module;
import com.cjburkey.server.module.Modules;

public class MainServer extends JavaPlugin {
	
	public static MainServer instance;
	
	public void onEnable() {
		instance = this;
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		Modules.initializeModules();
		for(Module m : Modules.getMods()) {
			Logger.log("Setting up cmds for: " + m.getName());
			m.getCommandHandler().register();
		}
		
		Logger.log("Initialization complete.");
	}
	
	public void onDisable() {
		Modules.deInitializeModules();
		Logger.log("Deinitialization complete.");
	}
	
}