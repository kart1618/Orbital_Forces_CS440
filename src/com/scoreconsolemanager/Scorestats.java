package com.scoreconsolemanager;

public class Scorestats {
	private static int ammo;
	private static int fuelcells;
	private static int score;
	private static int oxygentanks;
	private static int units;
	private static int lives;
	
	public Scorestats () {
		
	}
	public Scorestats (int ammo, int score, int fuelcells, int oxygentanks, int units, int lives) {
		this.ammo=ammo;
		this.fuelcells=fuelcells;
		this.score=score;
		this.oxygentanks=oxygentanks;
		this.units=units;
		this.lives=lives;
	}
	
	public int getAmmocount() {
		return ammo;
	}
	
	public void setAmmocount (int ammo) {
		if (this.ammo > 0)
			this.ammo=ammo;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore (int score) {
		this.score=score;
	}
	
	public int getFuelcellscount() {
		return fuelcells;
	}
	
	public void setFuelcellscount (int fuelcells) {
		this.fuelcells=fuelcells;
	}
	
	public int getOxygentankscount() {
		return oxygentanks;
	}
	
	public void setOxygentankscount (int oxygentanks) {
		this.oxygentanks=oxygentanks;
	}
	public int getUnitscount() {
		return units;
	}
	
	public void setUnitscount (int units) {
		this.units = units;
	}
	
	public int getLivescount() {
		return lives;
	}
	
	public void setLivescount (int lives) {
		this.lives = lives;
	}
	
}
