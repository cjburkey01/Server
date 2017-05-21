package com.cjburkey.server.teleport;

import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.cjburkey.server.MainServer;

public class TeleportRequest {
	
	private UUID from;
	private UUID to;
	
	public TeleportRequest(Player from, Player to) {
		if(from != null) this.from = from.getUniqueId();
		if(to != null) this.to = to.getUniqueId();
	}
	
	public boolean doTeleport() {
		if(to == null || from == null) return false;
		if(!online(from) || !online(to)) return false;
		Player pFrom = MainServer.instance.getServer().getPlayer(from);
		Player pTo = MainServer.instance.getServer().getPlayer(to);
		Location toPos = pTo.getLocation();
		pFrom.teleport(new Location(toPos.getWorld(), toPos.getBlockX(), toPos.getBlockY(), toPos.getBlockZ()));
		return true;
	}
	
	private boolean online(UUID id) {
		return MainServer.instance.getServer().getPlayer(id) != null;
	}
	
	public UUID getFrom() {
		return from;
	}
	
	public UUID getTo() {
		return to;
	}
	
}