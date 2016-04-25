package me.caprei.crazyctf.classes;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.caprei.crazyctf.game.GamePlayer;

public class ClassManager {
	
	private static ClassManager manager = new ClassManager();
	
	private ClassManager(){};
	
	public ClassManager getClassManager(){
		return manager;
	}
	
	public static void assignKit(GamePlayer player){
		for(ItemStack itemStack:player.getKit().getClassInstance().getInventoryItems()){
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			itemStack.setItemMeta(itemMeta);
		}
		
		for(ItemStack itemStack:player.getKit().getClassInstance().getWearableItems()){
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			itemStack.setItemMeta(itemMeta);
		}
		
		player.getPlayer().getInventory().clear();
		player.getPlayer().getInventory().setArmorContents((ItemStack[]) player.getKit().getClassInstance().wearableItems.toArray());
		player.getPlayer().getInventory().addItem((ItemStack[]) player.getKit().getClassInstance().inventoryItems.toArray());
	}	

}
