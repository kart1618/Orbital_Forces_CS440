package com.spacecraft;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Launchgame extends JFrame{

	public Launchgame () {
		Windowinit win = new Windowinit();
		win.drawPanel();
		win.initialMenu();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Launchgame frame = new Launchgame ();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
