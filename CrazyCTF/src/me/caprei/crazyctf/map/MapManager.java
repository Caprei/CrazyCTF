package me.caprei.crazyctf.map;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MapManager {
	
	private Set<Map> maps = new HashSet<Map>();
	
	private static MapManager mapManager = new MapManager();
	
	private MapManager(){}
	
	public static MapManager getMapManager(){
		return mapManager;
	}
	
	public void addMap(Map map){
		maps.add(map);
	}
	
	public Map getMap(String name){
		for(Map map:maps){
			if(map.getName().equals(name)){
				return map;
			}
		}
		return null;
	}
	
	public Map getRandomMap(){
		Map[] mapArray = (Map[]) maps.toArray();
		int random = ThreadLocalRandom.current().nextInt(mapArray.length);
		return mapArray[random];
	}
}
