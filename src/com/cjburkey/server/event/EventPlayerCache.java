package com.cjburkey.server.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.cjburkey.server.module.Modules;

public class EventPlayerCache implements Listener {
	
	@EventHandler
	public void callEvent(PlayerJoinEvent e) {
		if(!Modules.getModuleCacher().hasJoined(e.getPlayer())) EventPlayerFirstJoin.firstJoin(e.getPlayer());
		Modules.getModuleCacher().join(e.getPlayer());
	}
	
}