package me.caprei.crazyctf.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import me.caprei.crazyctf.data.ConfigurationManager;
import me.caprei.crazyctf.map.Lobby;
import me.caprei.crazyctf.map.Map;
import me.caprei.crazyctf.map.MapManager;
import me.caprei.crazyctf.team.Team;
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
		if(numOfPlayers >= ConfigurationManager.getManager().getMinPlayers()){
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
		case SUDDEN_DEATH:
			this.beginSuddenDeath();
			break;
		case END_GAME:
			this.beginEndGame();
			break;
		}
	}
	
	private void beginLobby(){
		if(Lobby.getSpawnPoint() == null){
			Bukkit.broadcastMessage(ChatColor.RED + "CrazyCTF> No lobby spawnpoint set, please set one.");
			return;
		}
		for(Player player:Bukkit.getOnlinePlayers()){	
			player.teleport(Lobby.getSpawnPoint());
		}
	}
	
	public BukkitTask beginCountdown(){
		currentMap = MapManager.getMapManager().getRandomMap();
		return new CountdownRunnable().runTaskTimer(CrazyCTF.getInstance(), 1, 20);
	}
	
	private void beginIngame(){
		TeamManager.assignTeams();
		for(GamePlayer gamePlayer:PlayerManager.getPlayerManager().getPlayers()){
			if(gamePlayer.getTeam() == Team.BLUE){
				gamePlayer.getPlayer().teleport(currentMap.getBlueSpawn());
				gamePlayer.getPlayer().sendMessage(ChatColor.BLUE + "You are on the blue team! Capture the red flag, and return it to your base.");
			}else{
				gamePlayer.getPlayer().teleport(currentMap.getRedSpawn());
				gamePlayer.getPlayer().sendMessage(ChatColor.RED + "You are on the red team! Capture the red flag, and return it to your base.");
			}
			Player player = gamePlayer.getPlayer();
			player.setFireTicks(0);
			for(PotionEffect effect:player.getActivePotionEffects()){
				player.removePotionEffect(effect.getType());
			}
			player.setHealth(20.0d);
			player.setFoodLevel(20);
		}
	}
	
	private void beginSuddenDeath(){
		CTFPlayerEvents.suddenDeath = true;
	}
	
	private void beginEndGame(){
		CTFPlayerEvents.suddenDeath = false;
		TeamManager.setBlueTeamCount(0);
		TeamManager.setRedTeamCount(0);
	}
}
