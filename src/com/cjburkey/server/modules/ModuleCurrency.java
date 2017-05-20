package com.cjburkey.server.modules;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.cmds.CmdBalance;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.module.Module;
import com.cjburkey.server.module.Modules;

public class ModuleCurrency extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	private CmdBalance cmdBalance;
	
	public static final String stylize(double amt) {
		return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amt);
	}
	
	public void setMoney(UUID player, double amount) {
		amount = Math.abs(amount);
		data.set(player.toString(), amount);
		data.saveToDisk();
	}
	
	public void takeMoney(UUID player, double amount) {
		amount = Math.abs(amount);
		if(canAfford(player, amount)) setMoney(player, getMoney(player) - amount);
	}
	
	public void addMoney(UUID player, double amount) {
		amount = Math.abs(amount);
		setMoney(player, getMoney(player) + amount);
	}
	
	public double getMoney(UUID player) {
		data.loadFromDisk();
		return data.getDouble(player.toString());
	}
	
	public boolean canAfford(UUID player, double cost) {
		cost = Math.abs(cost);
		double current = getMoney(player);
		if((current - cost) >= 0) return true;
		return false;
	}

	public String getName() {
		return "ModuleCurrency";
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
		cmdBalance = new CmdBalance();
				
		cmds.addCmd(cmdBalance);
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}