package com.cjburkey.server.pos;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import com.cjburkey.server.MainServer;

public class Home {
	
	private Position pos;
	
	public Home(Position pos) {
		this.pos = pos;
	}
	
	public Home(String dim, int x, int y, int z) {
		this(new Position(MainServer.instance.getServer().getWorld(dim), x, y, z));
	}
	
	public Home(Location loc) {
		this(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		Home other = (Home) obj;
		if(pos == null) if(other.pos != null) return false;
		else if(!pos.equals(other.pos)) return false;
		return true;
	}
	
}