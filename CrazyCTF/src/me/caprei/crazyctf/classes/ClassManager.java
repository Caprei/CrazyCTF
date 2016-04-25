package me.caprei.crazyctf.classes;

import org.bukkit.inventory.ItemStack;

import me.caprei.crazyctf.game.GamePlayer;

public class ClassManager {
	
	private static ClassManager manager = new ClassManager();
	
	private ClassManager(){};
	
	public ClassManager getClassManager(){
		return manager;
	}
	
	public static void assignKit(GamePlayer player){
		player.getPlayer().getInventory().clear();
		player.getPlayer().getInventory().setArmorContents((ItemStack[]) player.getKit().getClassInstance().wearableItems.toArray());
		player.getPlayer().getInventory().addItem((ItemStack[]) player.getKit().getClassInstance().inventoryItems.toArray());
	}	

}
