package com.spacecraft;
import com.userprofiles.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Windowinit extends JFrame {

	private JPanel contentPane;
	private Object[] menu1 = {"New Game", "Settings", "Exit Game"};
	private Object[] menu2 = {"Amazonia", "Cyclopia", "Osiris" , "Simian", "Chapek_9", "Spheron_1"};
	private Object[] menu11 = {"Create Profile"};
	private JTextField userField = new JTextField (20);
	private JTextField usernameField = new JTextField (20);
	private JTextField gender = new JTextField (20);
	ButtonGroup bg = new ButtonGroup ();
	private JPanel myPanel;
	private Object selectedValue, targetPlanet;
	private int result;
	int cntr = 1;

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
	      myPanel.add(new JLabel("User Name: "));
	      myPanel.add(usernameField);
	      //myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Nick Name: "));
	      myPanel.add(userField);
	      myPanel.add(new JLabel ("Gender[M/F]: "));
	      myPanel.add(gender);
	      
	}
	
	public void trgtPlanetselect () {
	//
		targetPlanet = JOptionPane.showInputDialog(null, "Select a target planet to journey towards: ","Orbital Forces",
	    		  JOptionPane.INFORMATION_MESSAGE, null,
	    		  menu2, menu2[0]);
		if (targetPlanet.equals("Amazonia")){
			new Targetplanet().setName("AMAZONIA");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		if (targetPlanet.equals("Cyclopia")){
			new Targetplanet().setName("CYCLOPIA");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		if (targetPlanet.equals("Osiris")){
			new Targetplanet().setName("OSIRIS");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		if (targetPlanet.equals("Simian")){
			new Targetplanet().setName("SIMIAN");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		if (targetPlanet.equals("Chapek_9")){
			new Targetplanet().setName("CHAPEK_9");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		if (targetPlanet.equals("Spheron_1")){
			new Targetplanet().setName("SPHERON_1");
			new Targetplanet().setTargetplanetreached(false);
		}
		
		JOptionPane.showMessageDialog(null, "Target Planet Set! Lock 'n' Load !!");
	}
	
	public void initialMenu () {
		selectedValue = JOptionPane.showInputDialog(null,
	    		  "Choose one", "Input",
	    		  JOptionPane.INFORMATION_MESSAGE, null,
	    		  menu1, menu1[0]);
		
		if (selectedValue.equals("New Game")){
			profileMenu();
				if (cntr == 2) 
					cntr = 1;
				else {
					this.trgtPlanetselect();
					this.gameStart();
				}
				
		}
		else if (selectedValue.equals("Exit Game")) {
			JOptionPane.showMessageDialog(null, "Good Bye "+ (new Createprofile()).getName(), "Orbital Forces", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
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
