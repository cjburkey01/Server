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
	}
	
	public void unLoadModules() {
		if(mods != null) {
			for(Module m : mods) {
				Logger.log("Unloading Module: " + m.getName());
				m.onUnload();
			}
		}
	}
	
	public void clear() {
		if(mods != null) {
			mods.clear();
		}
	}
	
	public Module[] getMods() {
		return mods.toArray(new Module[mods.size()]);
	}
	
}