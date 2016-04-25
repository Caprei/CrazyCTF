package me.caprei.crazyctf.classes;

import org.bukkit.Material;

public enum ClassType {
	ENGINEER (new Assassin(), Material.DISPENSER, "Engineer", "Use building blocks to defeat your enemies. You also get an explosive cannon!"),
	ASSASSIN (new Assassin(), Material.POISONOUS_POTATO, "Assassin", "Uses sneakiness and speed to attack enemies without them knowing what hit them."), 
	SOLDIER (new Soldier(), Material.IRON_SWORD, "Soldier", "A straight fighter who will never need anything more than a sword."),
	ARCHER (new Archer(), Material.BOW, "Archer", "A long ranged attacker, skilled in the art of sniping, rapid fire and explosive arrows.");
	
	private CTFClass playerClass;
	private Material menuMaterial;
	private String menuName;
	private String menuLore;
	
	private ClassType(CTFClass ctfClass, Material menuMaterial, String menuName, String menuLore){
		playerClass = ctfClass;
		this.menuMaterial = menuMaterial;
		this.menuName = menuName;
		this.menuLore = menuLore;
	}
	
	public CTFClass getClassInstance(){
		return playerClass;
	}

	public Material getMenuMaterial() {
		return menuMaterial;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuLore() {
		return menuLore;
	}
}
