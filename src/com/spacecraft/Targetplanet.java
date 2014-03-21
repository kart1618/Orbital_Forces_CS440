package com.spacecraft;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Targetplanet {
	
	private static String name;
	private static int gameTime;
	private static int unitsReqd;
	private static int planetAmmo;
	private static int planetFuelcells;
	private static int planetOxygentanks;
	private static int scoreAttr;
	private static boolean targetplanetReached;
	private static boolean[] attempted = {false,false,false,false,false,false};
	private static Image image;
	private static ImageIcon ii;
	Randomize rz = new Randomize();
	
	public void setName(String name) {
		this.name = name;
		if (name.equalsIgnoreCase("AMAZONIA")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/amazonia.jpg"));
		}
    	else if (name.equalsIgnoreCase("CYCLOPIA")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/cyclopia.jpg"));
    	}
    	else if (name.equalsIgnoreCase("OSIRIS")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/osiris_4.jpg"));
    	}
    	else if (name.equalsIgnoreCase("SIMIAN_7")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/simian_7.png"));
    	}
    	else if (name.equalsIgnoreCase("CHAPEK_9")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/chapek_9.jpg"));
    	}
    	else if (name.equalsIgnoreCase("SPHERON_1")) {
    		gameTime = rz.gameTime();
    		unitsReqd = rz.unitsReqd();
    		planetAmmo = rz.ammoReqd();
    		planetFuelcells = rz.fuelcellsReqd();
    		planetOxygentanks = rz.o2tanksReqd();
    		scoreAttr = rz.scoreAttned();
    		ii = new ImageIcon(this.getClass().getResource("/images/spheron_1.jpg"));
    	}

	}
	
	public void setTargetplanetreached(boolean targetplanetReached) {
    	this.targetplanetReached = targetplanetReached;
    }
	
	public void setAttempted (int planetNum) {
		this.attempted[planetNum] = true;
	}
	
	public Image getImage() {
		image = ii.getImage();
		return image;
	}
	public String getName () {
		return this.name;
	}
	
	public boolean getAttempted (int planetNum) {
		return attempted[planetNum];
	}
	
    public boolean getTargetplanetreached() {
    	return this.targetplanetReached;
    }
    
    public int getGametime() {
    	return gameTime;
    }
    
    public int getUnitsreqd() {
    	return unitsReqd;
    }
    
    public int getPlanetammo() {
    	return planetAmmo;
    }
    
    public int getPlanetfuelcells () {
    	return planetFuelcells;
    }
    
    public int getPlanetoxygentanks () {
    	return planetOxygentanks;
    }
    
    public int getScoreattr () {
    	return scoreAttr;
    }
}
