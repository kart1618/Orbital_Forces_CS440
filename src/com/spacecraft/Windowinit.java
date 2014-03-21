package com.spacecraft;
import com.media.Videoplayer;
import com.scoreconsolemanager.Persist;
import com.scoreconsolemanager.Scorestats;
import com.userprofiles.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.util.Properties;

public class Windowinit extends JFrame {

	private JPanel contentPane;
	private Object[] menu1 = {"New Game", "Load Game", "Settings", "Help", "Exit Game"};
	private Object[] menu2 = {"Amazonia", "Cyclopia", "Osiris" , "Simian", "Chapek_9", "Spheron_1"};
	private JTextField userField = new JTextField (20);
	private JTextField usernameField = new JTextField (20);
	private JTextField gender = new JTextField (20);
	private JTextField ml = new JTextField(1);
	private JTextField mr = new JTextField(1);
	private JTextField mu = new JTextField(1);
	private JTextField md = new JTextField(1);
	private JTextField sh = new JTextField(1);
	private Videoplayer videoPlayer;
	Targetplanet tp = new Targetplanet ();
	ButtonGroup bg = new ButtonGroup ();
	private int repeatcntl = 1;
	private JPanel myPanel, settings;
	private Object selectedValue, targetPlanet;
	private int result, chgSettingsresult;
	private String line, usrnme;
	int cntr = 1;
	
	String help = "\t\thWelcome to Orbital Forces:\nPlease find the instructions for each option in the main menu:\n\n" +
			"'New Game' - Allows you to create a profile and starts the game.\n" +
			"'Load Game' - Allows you to load the game, previously played.\n" +
			"'Exit Game' - Allow you to quit the game.\n" +
			"\t\tDEFAULT CONTROLS: \nMove Left: 'a', Move Right: 'd', Move Up: 'w', Move Down:'s'\nFire Missiles: 'Space Bar', Pause:'p'";
	/**
	 * Create the frame.
	 */
	public void gameStart() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(0, 0, 1375, 730);
	//setBounds(500, 500, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	//contentPane.setExtendedState(contentPane.MAXIMIZED_BOTH);
	//contentPane.setUndecorated(true);
	setContentPane(contentPane);
	add(new GameInstance());
	setTitle("Orbital Forces");
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		    int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Are you sure you want to quit?", "Orbital Forces",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
				Scorestats temp = new Scorestats();
				new Persist(temp.getAmmocount(), temp.getScore(), temp.getFuelcellscount(), temp.getOxygentankscount(), temp.getUnitscount(), temp.getLivescount());
		    	JOptionPane.showMessageDialog(null, "Goodbye, See you next time "+new Createprofile().getName()+ " !");
		      //dispose();
		    	System.exit(0);
		    }
		  }
		});
    setSize(1375, 730);//800, 580
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(true);
}
	
	
	 
	private boolean profileCreation() {
		if (new Createprofile(usernameField.getText(),userField.getText(),gender.getText()).isCreated())
        	return true;
        else
        	return false;
	}
		
	public void drawPanel () {
		  myPanel = new JPanel();
		  settings = new JPanel ();
		  
		  myPanel.add(new JLabel("User Name: "));
	      myPanel.add(usernameField);
	      //myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("NickName: "));
	      myPanel.add(userField);
	      myPanel.add(new JLabel ("Gender[M/F]: "));
	      myPanel.add(gender);
	      
	      settings.add(new JLabel("Move Left: "));
	      settings.add(ml);	
	      settings.add(new JLabel("Move Right: "));
	      settings.add(mr);
	      settings.add(new JLabel("Move Up: "));
	      settings.add(mu);
	      settings.add(new JLabel("Move Down: "));
	      settings.add(md);
	      
	}
	
	public void trgtPlanetselect () {
		repeatcntl ++;
		
		targetPlanet = JOptionPane.showInputDialog(null, "Select a target planet to journey towards: ","Orbital Forces",
	    		  JOptionPane.INFORMATION_MESSAGE, null,
	    		  menu2, menu2[0]);
		if (targetPlanet.equals("Amazonia") && !(tp.getAttempted(0))) {
			tp.setAttempted(0);
			tp.setName("AMAZONIA");
			tp.setTargetplanetreached(false);
		}
		
		else if (targetPlanet.equals("Cyclopia") && !(tp.getAttempted(1))){
			tp.setAttempted(1);
			tp.setName("CYCLOPIA");
			tp.setTargetplanetreached(false);
		}
		
		else if (targetPlanet.equals("Osiris") && !(tp.getAttempted(2))){
			tp.setAttempted(2);
			tp.setName("OSIRIS");
			tp.setTargetplanetreached(false);
		}
		
		else if (targetPlanet.equals("Simian") && !(tp.getAttempted(3))){
			tp.setAttempted(3);
			tp.setName("SIMIAN");
			tp.setTargetplanetreached(false);
		}
		
		else if (targetPlanet.equals("Chapek_9") && !(tp.getAttempted(4))){
			tp.setAttempted(4);
			tp.setName("CHAPEK_9");
			tp.setTargetplanetreached(false);
		}
		
		else if (targetPlanet.equals("Spheron_1") && !(tp.getAttempted(5))){
			tp.setAttempted(5);
			tp.setName("SPHERON_1");
			tp.setTargetplanetreached(false);
		}
		
		else {
			JOptionPane.showMessageDialog (null, "This voyage has already been attempted!");
			trgtPlanetselect();
		}
		
		if (repeatcntl == 2) {
		int yes_no = JOptionPane.showConfirmDialog(null, "Target Planet Set! Lock 'n' Load !!\nSkip Video?", "Orbital Forces", JOptionPane.YES_NO_OPTION);
	
		if (yes_no == JOptionPane.NO_OPTION) {
			videoPlayer = new Videoplayer();
			videoPlayer.takeoffAVI();
		}
		else 
			JOptionPane.showMessageDialog(null, "Target Planet Set! Lock 'n' Load !!");
	}
}
	
	public void initialMenu () {
		selectedValue = JOptionPane.showInputDialog(null,
	    		  "Choose One", "Orbital Forces",
	    		  JOptionPane.INFORMATION_MESSAGE, null,
	    		  menu1, menu1[0]);
		String[] profile; 
		
		if (selectedValue.equals("New Game")){
			profileMenu();
				if (cntr == 2) {
					cntr = 1;
					initialMenu();
				}
				else {
					this.trgtPlanetselect();
					this.gameStart();
				}
				
		}
		
		else if (selectedValue.equals("Load Game")) {
			usrnme = JOptionPane.showInputDialog("Enter Username: ");
			File usrNme = new File ("C:/Users/Profiles/"+usrnme+".txt");
			if (!(usrNme.exists())) {
				JOptionPane.showMessageDialog(null, "Profile not found!");
				initialMenu();
			}
			else {
				try {
					BufferedReader br =new BufferedReader (new FileReader(usrNme));
					line = br.readLine();
					profile = line.split("-");
					new Createprofile().setUsername(profile[0]);
					new Createprofile().setName(profile[1]);
					new Createprofile().setGender(profile[2]);
					new Scorestats(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), 3);
					
					this.trgtPlanetselect();
					this.gameStart();	
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane
			} 
		}
		
		else if (selectedValue.equals("Settings")) {
			try {
				new Persist().setSettings(true);
				changeSettings();
				initialMenu();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if (selectedValue.equals("Help")) {
		JOptionPane.showMessageDialog(null, help);
			initialMenu();
		}
		
		else if (selectedValue.equals("Exit Game")) {
			JOptionPane.showMessageDialog(null, "Goodbye!");
			System.exit(0);
		}
		
		else {
			JOptionPane.showMessageDialog(null,"Goodbye!");
			System.exit(0);
		}
	}
	
	private void changeSettings() throws FileNotFoundException, IOException {
		char moveLeft, moveRight, moveUp, moveDown;
		Properties prop = new Properties ();
		chgSettingsresult = JOptionPane.showConfirmDialog(null, settings, 
	               "Enter desired keys: ", JOptionPane.OK_CANCEL_OPTION);
		if (chgSettingsresult == JOptionPane.OK_OPTION) {
			moveLeft = ml.getText().charAt(0);
			moveRight = mr.getText().charAt(0);
			moveUp = mu.getText().charAt(0);
			moveDown = md.getText().charAt(0);
			
			prop.setProperty("moveLeft", String.valueOf(moveLeft));
			prop.setProperty("moveRight", String.valueOf(moveRight));
			prop.setProperty("moveUp", String.valueOf(moveUp));
			prop.setProperty("moveDown", String.valueOf(moveDown));
			
			prop.store(new FileOutputStream("settings.properties"), null);
			
			
			}
		// TODO Auto-generated method stub
		
	}



	private void profileMenu() {
		result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Enter your desired User Name, Nike Name and Gender", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
	        if (this.profileCreation()) {
	        	JOptionPane.showMessageDialog(null, "Profile Created!");
	        }
	        else {
	        	JOptionPane.showMessageDialog(null, usernameField.getText()+" is already taken. Please select a new username.");
	        	profileMenu();
	        }
	}
		else if (result == JOptionPane.CANCEL_OPTION)  {
			cntr = 2;
			
		}
			
	}
	
}
