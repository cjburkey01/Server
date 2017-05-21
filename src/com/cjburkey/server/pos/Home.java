package com.cjburkey.server.pos;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class Home {
	
	private final Position pos;
	
	public Home(Position pos) {
		this.pos = pos;
	}
	
	public Home(World dim, int x, int y, int z) {
		this(new Position(dim, x, y, z));
	}
	
	public Home(Location loc) {
		this(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
	
	public boolean teleport(Entity entity) {
		World world = pos.getWorld();
		if(world != null) {
			Location loc = pos.toLocation();
			if(loc != null) {
				loc.setYaw(entity.getLocation().getYaw());
				loc.setPitch(entity.getLocation().getPitch());
				entity.teleport(loc, TeleportCause.PLUGIN);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return pos.toString();
	}
	
}