package com.cjburkey.server.pos;

import org.bukkit.Location;
import org.bukkit.World;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;

public class ChunkPosition {
	
	private World world;
	private int x, z;
	
	public ChunkPosition(World world, int x, int z) {
		this.world = world;
		this.x = x;
		this.z = z;
	}
	
	public ChunkPosition(Location loc) {
		this.world = loc.getWorld();
		this.x = loc.getChunk().getX();
		this.z = loc.getChunk().getZ();
	}
	
	public ChunkPosition(Position pos) {
		this(pos.toLocation());
	}
	
	public String toString() {
		return getWorld().getName() + "," + x + "," + z;
	}
	
	public static ChunkPosition fromString(String in) {
		String[] split = in.trim().split(",");
		if(split.length == 3) {
			try {
				int x = Integer.parseInt(split[1].trim());
				int z = Integer.parseInt(split[2].trim());
				return new ChunkPosition(MainServer.instance.getServer().getWorld(split[0].trim()), x, z);
			} catch(Exception e) {  }
		}
		Logger.log("Couldn't parse chunk position: " + in);
		return null;
	}

	public World getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}
	
}