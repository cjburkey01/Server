package com.cjburkey.server.module;

import com.cjburkey.server.modules.ModuleCurrency;
import com.cjburkey.server.modules.ModuleHome;
import com.cjburkey.server.modules.ModulePlayer;

public class Modules {
	
	private static ModuleManager modMan;
	
	private static ModulePlayer modCache;
	private static ModuleCurrency modCurrency;
	private static ModuleHome modHome;
	
	private static final void addMods() {
		modCache = new ModulePlayer();
		modCurrency = new ModuleCurrency();
		modHome = new ModuleHome();
		
		modMan.addModule(modCache);
		modMan.addModule(modCurrency);
		modMan.addModule(modHome);
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

	public static final ModulePlayer getModuleCacher() { return modCache; }
	public static final ModuleCurrency getModuleCurrency() { return modCurrency; }
	public static final ModuleHome getModuleHome() { return modHome; }
	
}