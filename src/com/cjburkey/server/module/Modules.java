package com.cjburkey.server.module;

import com.cjburkey.server.modules.ModuleClaim;
import com.cjburkey.server.modules.ModuleCurrency;
import com.cjburkey.server.modules.ModuleHome;
import com.cjburkey.server.modules.ModulePlayer;
import com.cjburkey.server.modules.ModuleRefresh;
import com.cjburkey.server.modules.ModuleTeleport;

public class Modules {
	
	private static ModuleManager modMan;
	
	private static ModulePlayer modCache;
	private static ModuleCurrency modCurrency;
	private static ModuleHome modHome;
	private static ModuleClaim modClaim;
	private static ModuleRefresh modRefresh;
	private static ModuleTeleport modTp;
	
	private static final void addMods() {
		modCache = new ModulePlayer();
		modCurrency = new ModuleCurrency();
		modHome = new ModuleHome();
		modClaim = new ModuleClaim();
		modRefresh = new ModuleRefresh();
		modTp = new ModuleTeleport();
		
		modMan.addModule(modCache);
		modMan.addModule(modCurrency);
		modMan.addModule(modHome);
		modMan.addModule(modClaim);
		modMan.addModule(modRefresh);
		modMan.addModule(modTp);
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
	public static final ModuleClaim getModuleClaim() { return modClaim; }
	public static final ModuleRefresh getModuleRefresh() { return modRefresh; }
	public static final ModuleTeleport getModuleTeleport() { return modTp; }
	
}