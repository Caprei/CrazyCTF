package me.caprei.crazyctf.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.caprei.crazyctf.ability.ArcherModeChange;
import me.caprei.crazyctf.ability.ExplosiveBow;
import me.caprei.crazyctf.ability.MachineGunBow;
import me.caprei.crazyctf.ability.SniperBow;
import me.caprei.crazyctf.utils.ChatUtils;

public class Archer extends CTFClass {
		
	public Archer(){
		setMenuMaterial();
		setWearableItems();
		setInventoryItems();
		setAbilities();
	}

	@Override
	public void setMenuMaterial() {
		menuMaterial = Material.BOW;
		
	}

	@Override
	public void setWearableItems() {
		wearableItems.add(new ItemStack(Material.CHAINMAIL_BOOTS));
		wearableItems.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		wearableItems.add(new ItemStack(Material.LEATHER_LEGGINGS));
		
	}

	@Override
	public void setInventoryItems() {
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 4);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName(ChatUtils.ITEM_COLOURS + "Bow [Sniper Mode]");
		bow.setItemMeta(bowMeta);
		
		inventoryItems.add(bow);
		inventoryItems.add(new ItemStack(Material.ARROW, 1));
	}

	@Override
	public void setAbilities() {
		abilities.add(new SniperBow());
		abilities.add(new MachineGunBow());
		abilities.add(new ExplosiveBow());
		abilities.add(new ArcherModeChange());
		
	}
}
