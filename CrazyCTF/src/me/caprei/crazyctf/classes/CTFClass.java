package me.caprei.crazyctf.classes;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.caprei.crazyctf.abilities.Ability;

public abstract class CTFClass implements Listener{
	
	protected Set<ItemStack> wearableItems = new HashSet<ItemStack>();
	protected Set<ItemStack> inventoryItems = new HashSet<ItemStack>();
	protected Set<Ability> abilities = new HashSet<>();
	public abstract void setWearableItems();
	public abstract void setInventoryItems();
	public abstract void setAbilities();
	
	public Set<ItemStack> getWearableItems(){
		return wearableItems;
	}
	
	public Set<ItemStack> getInventoryItems(){
		return inventoryItems;
	}
}
