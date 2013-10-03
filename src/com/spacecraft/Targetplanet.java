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
	private static Image image;
	private static ImageIcon ii;
	
	public void setName(String name) {
		this.name = name;
		if (name.equalsIgnoreCase("AMAZONIA")) {
    		gameTime = 45;
    		unitsReqd = 30;
    		planetAmmo = 100;
    		planetFuelcells = 33;
    		planetOxygentanks = 22;
    		scoreAttr = 500;
    		ii = new ImageIcon(this.getClass().getResource("/images/amazonia.jpg"));
		}
    	else if (name.equalsIgnoreCase("CYCLOPIA")) {
    		gameTime = 60;
    		unitsReqd = 40;
    		planetAmmo = 105;
    		planetFuelcells = 40;
    		planetOxygentanks = 25;
    		scoreAttr = 732;
    		ii = new ImageIcon(this.getClass().getResource("/images/cyclopia.jpg"));
    	}
    	else if (name.equalsIgnoreCase("OSIRIS")) {
    		gameTime = 75;
    		unitsReqd = 50;
    		planetAmmo = 111;
    		planetFuelcells = 44;
    		planetOxygentanks = 38;
    		scoreAttr = 987;
    		ii = new ImageIcon(this.getClass().getResource("/images/osiris.jpg"));
    	}
    	else if (name.equalsIgnoreCase("SIMIAN_7")) {
    		gameTime = 90;
    		unitsReqd = 78;
    		planetAmmo = 126;
    		planetFuelcells = 59;
    		planetOxygentanks = 40;
    		scoreAttr = 1219;
    		ii = new ImageIcon(this.getClass().getResource("/images/simian_7.png"));
    	}
    	else if (name.equalsIgnoreCase("CHAPEK_9")) {
    		gameTime = 115;
    		unitsReqd = 91;
    		planetAmmo = 134;
    		planetFuelcells = 61;
    		planetOxygentanks = 55;
    		scoreAttr = 1324;
    		ii = new ImageIcon(this.getClass().getResource("/images/chapek_9.jpg"));
    	}
    	else if (name.equalsIgnoreCase("SPHERON_1")) {
    		gameTime = 140;
    		gameTime = 120;
    		unitsReqd = 94;
    		planetAmmo = 137;
    		planetFuelcells = 63;
    		planetOxygentanks = 56;
    		scoreAttr = 1500;
    		ii = new ImageIcon(this.getClass().getResource("/images/spheron_1.jpg"));
    	}

	}
	
	public Image getImage() {
		image = ii.getImage();
		return image;
	}
	public String getName () {
		return this.name;
	}
	
	public void setTargetplanetreached(boolean targetplanetReached) {
    	this.targetplanetReached = targetplanetReached;
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
