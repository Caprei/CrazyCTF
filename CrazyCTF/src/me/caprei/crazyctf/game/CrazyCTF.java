package me.caprei.crazyctf.game;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrazyCTF extends JavaPlugin {
	
	//Forgive me oh Java gods
	private static CrazyCTF plugin;
	
	public static CrazyCTF getInstance(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		this.saveDefaultConfig();
		//TODO register events
	}
	
	public void registerEvents(Listener... listeners){
		for(Listener listener:listeners){
		Bukkit.getPluginManager().registerEvents(listener, this);
		}
	}
}
