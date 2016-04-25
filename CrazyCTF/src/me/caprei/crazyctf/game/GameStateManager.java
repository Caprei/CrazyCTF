package me.caprei.crazyctf.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import me.caprei.crazyctf.map.Map;
import me.caprei.crazyctf.team.TeamManager;

public class GameStateManager {
	
	private static GameStateManager gameStateManager = new GameStateManager();
	private GameState currentState = GameState.LOBBY;
	private Map currentMap;
	
	private GameStateManager(){}
	
	public static GameStateManager getManager(){
		return gameStateManager;
	}
	
	public Map getCurrentMap(){
		return currentMap;
	}
	
	public GameState getCurrentState(){
		return currentState;
	}
	
	public boolean validStart(){
		int numOfPlayers = PlayerManager.getPlayerManager().getPlayers().size();
		if(numOfPlayers >= 8){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCurrentState(GameState state){
		currentState = state;
		switch(state){
		case LOBBY:
			this.beginLobby();
			break;
		case COUNTDOWN:
			this.beginCountdown();
			break;
		case INGAME:
			this.beginIngame();
			break;
		case DESTRUCTION:
			this.beginDestruction();
			break;
		case SUDDEN_DEATH:
			this.beginSuddenDeath();
			break;
		case END_GAME:
			this.beginEndGame();
			break;
		}
	}
	
	private void beginLobby(){
		for(Player player:Bukkit.getOnlinePlayers()){
			player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
			//TODO: Get spawn location from config;
		}
	}
	
	public BukkitTask beginCountdown(){
		return new CountdownRunnable().runTaskTimer(CrazyCTF.getInstance(), 1, 20);
	}
	
	private void beginIngame(){
		
	}
	
	private void beginDestruction(){
		
	}
	
	private void beginSuddenDeath(){
		
	}
	
	private void beginEndGame(){
		TeamManager.setBlueTeamCount(0);
		TeamManager.setRedTeamCount(0);
	}
}
