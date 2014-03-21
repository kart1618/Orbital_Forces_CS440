package com.spacecraft;

import java.util.Random;

import com.scoreconsolemanager.Scorestats;

public class Randomize {

	/**
	 * @param args
	 */
	
	Scorestats sc = new Scorestats();
	
	public int gameTime () {
		int high = (sc.getScore() + sc.getUnitscount()) + 120;
		int low = (sc.getScore() + sc.getUnitscount());
		
		return (new Random().nextInt(high - low) + low);
	}
	
	public int unitsReqd() {
		int low = sc.getUnitscount() - 250;
		int high = sc.getUnitscount() + 100;
		return (new Random ().nextInt(high - low) + low);
	}
	
	public int ammoReqd() {
		if ( (sc.getAmmocount()/2) <= 20)
			return (sc.getAmmocount()/2);
		else return 20;
	}
	
	public int fuelcellsReqd() {
		if ( (sc.getFuelcellscount()/3) <= 25)
			return (sc.getFuelcellscount()/3);
		else return 25;
	}

	public int o2tanksReqd() {
		if ( (sc.getOxygentankscount()/2) <= 15)
			return (sc.getOxygentankscount()/2);
		else return 15;
	}
	
	public int scoreAttned() {
		return (sc.getScore()/4);
	}
}
