package com.cjburkey.server.event;

import org.bukkit.entity.Player;
import com.cjburkey.server.Logger;
import com.cjburkey.server.module.Modules;

public class EventPlayerFirstJoin {
	
	public static void firstJoin(Player ply) {
		Modules.getModuleCurrency().setMoney(ply.getUniqueId(), 100.0d);
		Logger.log("Initial join: " + ply.getDisplayName());
	}
	
}