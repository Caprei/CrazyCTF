package me.caprei.crazyctf.ability;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;

import me.caprei.crazyctf.game.CrazyCTF;

public class BlockShot extends Ability{

	public BlockShot(){
		triggerItemNames.add("Block Shot");
		abilityName = "Block Shot";
		secondsCooldown = 3.0;
		Bukkit.getPluginManager().registerEvents(this, CrazyCTF.getInstance());
	}
	
	@Override
	public void castAbility(Player caster) {
		if(!super.validCast(caster)){
			return;
		}
		Block block;
		BlockIterator blockIterator = new BlockIterator(caster, 7);
		while(blockIterator.hasNext()){
			block = blockIterator.next();
			Location blockLocation = block.getLocation();
			Entity armourStand = blockLocation.getWorld().spawnEntity(blockLocation, EntityType.ARMOR_STAND);
			for(Entity entity:armourStand.getNearbyEntities(1.0, 1.0, 1.0)){
				if(entity instanceof LivingEntity){
					LivingEntity livingEntity = (LivingEntity) entity;
					livingEntity.damage(2.0d);
					livingEntity.setVelocity(livingEntity.getLocation().toVector().subtract(blockLocation.toVector()).normalize());
				}
			}
			armourStand.remove();
			block.setType(Material.WOOD);
		}
		cooldown = new AbilityCooldown(secondsCooldown);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		String itemName = ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName());
		if(triggerItemNames.contains(itemName)){
			castAbility(event.getPlayer());
		    if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR){
		    	event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
		    }
		}
	}
}
