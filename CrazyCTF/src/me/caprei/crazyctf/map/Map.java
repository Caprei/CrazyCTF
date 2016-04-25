package me.caprei.crazyctf.map;

import org.bukkit.Location;
import org.bukkit.World;

public class Map {
	
	private Location redSpawn;
	private Location blueSpawn;
	private Location redFlag;
	private Location blueFlag;
	private World world = redSpawn.getWorld();
	public Location getRedSpawn() {
		return redSpawn;
	}
	public Location getBlueSpawn() {
		return blueSpawn;
	}
	public Location getRedFlag() {
		return redFlag;
	}
	public Location getBlueFlag() {
		return blueFlag;
	}
	public World getWorld() {
		return world;
	}
	public void setRedSpawn(Location redSpawn) {
		this.redSpawn = redSpawn;
	}
	public void setBlueSpawn(Location blueSpawn) {
		this.blueSpawn = blueSpawn;
	}
	public void setRedFlag(Location redFlag) {
		this.redFlag = redFlag;
	}
	public void setBlueFlag(Location blueFlag) {
		this.blueFlag = blueFlag;
	}
	public void setWorld(World world) {
		this.world = world;
	}
}
