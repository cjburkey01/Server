package com.cjburkey.server.pos;

import org.bukkit.Location;
import org.bukkit.World;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;

public class Position {
	
	private World world;
	private int x, y, z;
	
	public Position(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(Location loc) {
		this(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
	
	public String toString() {
		return world.getName() + "," + x + "," + y + "," + z;
	}
	
	public static Position fromString(String in) {
		String[] split = in.trim().split(",");
		if(split.length == 4) {
			try {
				int x = Integer.parseInt(split[1].trim());
				int y = Integer.parseInt(split[2].trim());
				int z = Integer.parseInt(split[3].trim());
				return new Position(MainServer.instance.getServer().getWorld(split[0].trim()), x, y, z);
			} catch(Exception e) {  }
		}
		Logger.log("Couldn't parse position location: " + in);
		return null;
	}
	
	public Location toLocation() {
		return new Location(world, x, y, z);
	}

	public World getWorld() {
		return world;
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