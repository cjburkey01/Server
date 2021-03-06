package com.cjburkey.server.module;

import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.data.ModuleDataHandler;

public abstract class Module {
	
	public abstract String getName();
	public abstract Module[] getRequiredModules();
	public abstract ModuleDataHandler getDataHandler();
	public abstract CommandHandler getCommandHandler();
	public abstract void onLoad();
	public abstract void onUnload();
	
	public boolean equals(Object otherObj) {
		if(otherObj instanceof Module) {
			Module other = (Module) otherObj;
			return other.getName().equals(getName());
		}
		return false;
	}
	
}