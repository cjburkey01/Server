package com.cjburkey.server.module;

import com.cjburkey.server.modules.ModulePlayer;
import com.cjburkey.server.modules.ModuleCurrency;

public class Modules {
	
	private static ModuleManager modMan;
	
	private static ModulePlayer modCache;
	private static ModuleCurrency modCurrency;
	
	private static final void addMods() {
		modCache = new ModulePlayer();
		modCurrency = new ModuleCurrency();
		
		modMan.addModule(modCache);
		modMan.addModule(modCurrency);
	}
	
	public static final void initializeModules() {
		modMan = new ModuleManager();
		addMods();
		init();
	}
	
	public static final void deInitializeModules() {
		deInit();
		modMan.clear();
	}
	
	private static final void init() {
		modMan.loadModules();
	}
	
	private static final void deInit() {
		modMan.unLoadModules();
	}
	
	public static Module[] getMods() {
		return modMan.getMods();
	}
	
	public static final ModuleCurrency getModuleCurrency() { return modCurrency; }
	public static final ModulePlayer getModuleCacher() { return modCache; }
	
}