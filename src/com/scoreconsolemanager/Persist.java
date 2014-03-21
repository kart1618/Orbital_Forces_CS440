package com.scoreconsolemanager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.userprofiles.Createprofile;

public class Persist {
	
	private File file;
	private FileWriter filewriter;
	private String absPath, profileStr;
	private static boolean pauseImagevisible = false;
	private static boolean settingsChanged = false;
	
	public Persist (int ammo, int score, int fuelcells, int oxygentanks, int units, int lives) {
		//int ammo, int score, int fuelcells, int oxygentanks, int units, int lives
		//orig: //int lives, int ammocount, int foodcans, int fuelcells, int oxgentanks, int unit
		
		Createprofile temp = new Createprofile ();
		String fileName = temp.getUsername();
		profileStr = temp.getUsername()+"-"+temp.getName()+"-"+temp.getGender();
		
		//profileStr = String.valueOf(lives)+"-"+temp.getUsername()+"-"+temp.getName()+"-"+temp.getGender()+"-"+
		//String.valueOf(ammocount)+"-"+String.valueOf(foodcans)+"-"+String.valueOf(fuelcells)+"-"+
		//String.valueOf(oxgentanks)+"-"+String.valueOf(units);
		//File usrNme = new File ("C:/Users/Profiles/"+usrnme+".txt");
		file = new File ("C:/Users/Profiles/"+fileName+".txt");
		absPath = "C:/Users/Profiles/"+fileName+".txt";
		if (file.exists()) {
			try {
				filewriter = new FileWriter (absPath);
				filewriter.write(profileStr);
				filewriter.write("\n");
				filewriter.write(String.valueOf(ammo));
				filewriter.write("\n");
				filewriter.write(String.valueOf(score));
				filewriter.write("\n");
				filewriter.write(String.valueOf(fuelcells));
				filewriter.write("\n");
				filewriter.write(String.valueOf(oxygentanks));
				filewriter.write("\n");
				filewriter.write(String.valueOf(units));
				filewriter.write("\n");
				filewriter.write(String.valueOf(lives));
				filewriter.write("\n");
				filewriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}
	
	public Persist() {
		
	}
	
	public boolean settingsChanged() {
		return settingsChanged;
	}
	
	public void setSettings( boolean settingsChanged ) {
		this.settingsChanged = settingsChanged;
	}
	
	public boolean pauseImageisVisible() {
        return pauseImagevisible;
    }
	
	public void pauseImagesetVisible(boolean pauseImagevisible) {
        this.pauseImagevisible = pauseImagevisible;
    }
	
	
	
	}
