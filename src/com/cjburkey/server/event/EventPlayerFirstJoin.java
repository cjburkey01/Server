package com.cjburkey.server.event;

import org.bukkit.entity.Player;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;
import com.cjburkey.server.module.Modules;

public class EventPlayerFirstJoin {
	
	public static void firstJoin(Player ply) {
		Modules.getModuleCurrency().setMoney(ply.getUniqueId(), MainServer.instance.getConfig().getDouble("defaultMoney"));
		Logger.log("First join: " + ply.getDisplayName());
	}
	
}