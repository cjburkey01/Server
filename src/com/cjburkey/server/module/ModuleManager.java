package com.cjburkey.server.module;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.server.Logger;

public class ModuleManager {
	
	private List<Module> mods;
	
	public ModuleManager() {
		mods = new ArrayList<>();
	}
	
	public void addModule(Module m) {
		mods.add(m);
	}
	
	public void loadModules() {
		if(mods != null) {
			for(Module m : mods) {
				Logger.log("Loading Module: " + m.getName());
				m.onLoad();
			}
		}
		requireCheck();
	}
	
	private void requireCheck() {
		Logger.log("Checking logger requirements.");
		for(Module m : mods) {
			Module[] required = m.getRequiredModules().clone();
			for(Module req : required) {
				if(!isModuleLoaded(req)) {
					unLoadModule(m);
					Logger.log("Module not found: " + req.getName());
				}
			}
		}
		Logger.log("Checked requirements.");
	}
	
	public void unLoadModules() {
		if(mods != null) {
			for(Module m : mods) {
				Logger.log("Unloading Module: " + m.getName());
				m.onUnload();
			}
		}
	}
	
	public void unLoadModule(Module m) {
		m.onUnload();
		mods.remove(m);
	}
	
	public void clear() {
		if(mods != null) {
			mods.clear();
		}
	}
	
	public boolean isModuleLoaded(Module m) {
		for(Module mod : mods) {
			if(m.equals(mod)) return true;
		}
		return false;
	}
	
	public Module[] getMods() {
		return mods.toArray(new Module[mods.size()]);
	}
	
}