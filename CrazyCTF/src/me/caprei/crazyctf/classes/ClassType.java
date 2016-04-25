package me.caprei.crazyctf.classes;

public enum ClassType {
	ENGINEER (new Assassin()),
	ASSASSIN (new Assassin()), 
	SOLDIER (new Soldier()),
	ARCHER (new Archer());
	
	private CTFClass playerClass;
	
	private ClassType(CTFClass ctfClass){
		playerClass = ctfClass;
	}
	
	public CTFClass getClassInstance(){
		return playerClass;
	}
}
