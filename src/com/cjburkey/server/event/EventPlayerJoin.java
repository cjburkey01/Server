package com.cjburkey.server.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.cjburkey.server.module.Modules;

public class EventPlayerJoin implements Listener {
	
	@EventHandler
	public void callEvent(PlayerJoinEvent e) {
		Modules.getModuleCacher().join(e.getPlayer());
	}
	
}