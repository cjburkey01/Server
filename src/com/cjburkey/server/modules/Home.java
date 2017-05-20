package com.cjburkey.server.modules;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;

public class Home {
	
	private String dim;
	private int x, y, z;
	
	public Home(String dim, int x, int y, int z) {
		this.dim = dim.trim();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Home(String dim, Location loc) {
		this.dim = dim.trim();
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
	}
	
	public boolean teleport(Entity entity) {
		World world = MainServer.instance.getServer().getWorld(dim);
		if(world != null) {
			Location loc = new Location(world, x + 0.5, y + 0.5, z + 0.5);
			if(loc != null) {
				loc.setYaw(entity.getLocation().getYaw());
				loc.setPitch(entity.getLocation().getPitch());
				entity.teleport(loc, TeleportCause.PLUGIN);
				return true;
			}
		}
		return false;
	}
	
	public String getDimension() {
		return dim;
	}
	
	public String toString() {
		return dim + "," + x + "," + y + "," + z;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dim == null) ? 0 : dim.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		Home other = (Home) obj;
		if(dim == null) if(other.dim != null) return false;
		else if(!dim.equals(other.dim)) return false;
		if(x != other.x) return false;
		if(y != other.y) return false;
		if(z != other.z) return false;
		return true;
	}

	public static Home fromString(String in) {
		String[] split = in.trim().split(",");
		if(split.length == 4) {
			try {
				int x = Integer.parseInt(split[1].trim());
				int y = Integer.parseInt(split[2].trim());
				int z = Integer.parseInt(split[3].trim());
				return new Home(split[0].trim(), x, y, z);
			} catch(Exception e) {  }
		}
		Logger.log("Couldn't parse home location: " + in);
		return null;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
}