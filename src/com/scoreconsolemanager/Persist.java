package com.scoreconsolemanager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.userprofiles.Createprofile;

public class Persist {
	
	private File file;
	private FileWriter filewriter;
	private String absPath, profileStr;
	
	public Persist (int lives, int ammocount, int foodcans, int fuelcells, int oxgentanks, int units) {
		
		Createprofile temp = new Createprofile ();
		String fileName = new Createprofile().getUsername();
		
		profileStr = String.valueOf(lives)+"-"+temp.getUsername()+"-"+temp.getName()+"-"+temp.getGender()+"-"+
		String.valueOf(ammocount)+"-"+String.valueOf(foodcans)+"-"+String.valueOf(fuelcells)+"-"+
		String.valueOf(oxgentanks)+"-"+String.valueOf(units);
		
		file = new File ("C:/Users/Profiles/"+fileName+".txt");
		absPath = "C:/Users/Profiles/"+fileName+".txt";
		if (file.exists()) {
			try {
				filewriter = new FileWriter (absPath);
				filewriter.write(profileStr);
				filewriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}
	}
