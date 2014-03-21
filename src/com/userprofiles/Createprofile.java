package com.userprofiles;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import com.scoreconsolemanager.Scorestats;

public class Createprofile {

	private static String username;
	private static String name;
	private static String sex;
	private static BufferedWriter out;
	private String profile;
	private static final int INITIAL_AMMO_COUNT =100;
	private static final int INITIAL_SCORE_COUNT = 0;
	private static final int INITIAL_FUELCELL_COUNT = 750;
	private static final int INITIAL_OXYGENTANK_COUNT = 750;
	private static final int INITIAL_UNIT_COUNT = 50;
	private static final int INITIAL_LIVES_COUNT = 3;
	
	public Createprofile(String username, String name, String sex) {
		this.name = name;
		this.username = username;
		this.sex = sex;
		this.profile = this.username+"-"+this.name+"-"+this.sex;
		new Scorestats(INITIAL_AMMO_COUNT, INITIAL_SCORE_COUNT, INITIAL_FUELCELL_COUNT, INITIAL_OXYGENTANK_COUNT, INITIAL_UNIT_COUNT, INITIAL_LIVES_COUNT);
	}
	
	public Createprofile() {
		
	}
	
	public String getUsername () {	
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGender() {
		return this.sex;
	}
	
	public void setUsername (String username) {	
		this.username = username;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setGender(String sex) {
		this.sex = sex;
	}

	public boolean isCreated() {
		if (this.check()) {
			this.persist();
			return true;
		}
		else
			return false;
	}
	
	private void persist()
	{
		try {
			out = new BufferedWriter (new FileWriter ("C:/Users/Profiles/"+username+".txt", true));
			out.write(profile+"\n");
			//out.write("\n");
			out.write(String.valueOf(INITIAL_AMMO_COUNT));
			out.write("\n");
			out.write(String.valueOf(INITIAL_SCORE_COUNT));
			out.write("\n");
			out.write(String.valueOf(INITIAL_FUELCELL_COUNT));
			out.write("\n");
			out.write(String.valueOf(INITIAL_OXYGENTANK_COUNT));
			out.write("\n");
			out.write(String.valueOf(INITIAL_UNIT_COUNT));
			out.write("\n");
			out.write(String.valueOf(INITIAL_LIVES_COUNT));
			out.write("\n");
			out.close();
		}
		catch (IOException e) {
			System.out.println("Failed to persist Profile information! The Stack Trace is as follows:\n"+e);
		}
	}
	
	private boolean check()
	{
		File profileDir =new File ("C:/Users/Profiles");
		File userName = new File ("C:/Users/Profiles/"+username+".txt");
		if (!profileDir.isDirectory()) {
			profileDir.mkdir();
			return true;
		}
		else if (userName.exists())
			return false;
		else
			return true;
	}
	
}
