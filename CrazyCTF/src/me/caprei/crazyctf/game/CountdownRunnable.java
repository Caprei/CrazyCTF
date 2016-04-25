package me.caprei.crazyctf.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.caprei.crazyctf.utils.ChatUtils;

public class CountdownRunnable extends BukkitRunnable{
	
	private int secondsLeft = 30;
	
	@Override
	public void run(){
		
		if(secondsLeft == 30){
		    Bukkit.getServer().broadcastMessage(ChatUtils.COUNTDOWN_TEXT + secondsLeft + " seconds left till the game starts!");
		} else if(secondsLeft == 15){
			Bukkit.getServer().broadcastMessage(ChatUtils.COUNTDOWN_TEXT + secondsLeft + " seconds left till the game starts!");
		} else if(secondsLeft <= 10){
			Bukkit.getServer().broadcastMessage(ChatUtils.COUNTDOWN_TEXT + secondsLeft + " seconds left till the game starts!");
		} else if(secondsLeft == 0){
			Bukkit.getServer().broadcastMessage(ChatUtils.COUNTDOWN_TEXT + secondsLeft + " Game started! Capture the opposing team's flag!");
			GameStateManager.getManager().setCurrentState(GameState.INGAME);
			this.cancel();
		}
		secondsLeft--;
	}
}
