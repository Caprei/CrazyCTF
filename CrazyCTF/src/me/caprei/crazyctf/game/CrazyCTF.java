package me.caprei.crazyctf.game;

import org.bukkit.plugin.java.JavaPlugin;

public class CrazyCTF extends JavaPlugin {
	
	//forgive me java lords
	private static CrazyCTF plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		
	}
	
	public static CrazyCTF getInstance(){
		return plugin;
	}
	
	

}
