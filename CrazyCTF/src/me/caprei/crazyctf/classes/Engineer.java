package me.caprei.crazyctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.caprei.crazyctf.ability.BlockShot;
import me.caprei.crazyctf.ability.CannonShot;
import me.caprei.crazyctf.utils.ChatUtils;

public class Engineer extends CTFClass{
	
	public Engineer(){
		setMenuMaterial();
		setWearableItems();
		setInventoryItems();
		setAbilities();
	}
	
	@Override
	public void setWearableItems() {
		wearableItems.add(new ItemStack(Material.LEATHER_CHESTPLATE));
		wearableItems.add(new ItemStack(Material.LEATHER_LEGGINGS));
		wearableItems.add(new ItemStack(Material.IRON_BOOTS));
		
	}

	@Override
	public void setInventoryItems() {
		ItemStack cannon = new ItemStack(Material.WOOD_HOE);
	    ItemMeta cannonMeta = cannon.getItemMeta();
	    cannonMeta.setDisplayName(ChatUtils.ITEM_COLOURS + "Cannon");
	    cannon.setItemMeta(cannonMeta);
	       
	    ItemStack woodBlock = new ItemStack(Material.LOG, 5);
	    ItemMeta woodBlockMeta = woodBlock.getItemMeta();
	    woodBlockMeta.setDisplayName(ChatUtils.ITEM_COLOURS + "Block Shot");
	    woodBlock.setItemMeta(woodBlockMeta);
	    
	    inventoryItems.add(new ItemStack(Material.WOOD_SWORD));
	    inventoryItems.add(woodBlock);
	    inventoryItems.add(cannon);
		
	}

	@Override
	public void setAbilities() {
		abilities.add(new CannonShot());
		abilities.add(new BlockShot());
		
	}

	@Override
	public void setMenuMaterial() {
		menuMaterial = Material.DISPENSER;
	}
}
