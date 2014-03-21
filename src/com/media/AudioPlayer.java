package com.media;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import com.sun.media.protocol.avi.DataSource;

public class AudioPlayer extends JFrame {
	
	//String fileName = "C:\\Users\\Karthik\\workspace_j2ee\\Orbital_Forces\\src\\clips\\soundtrack.wav";
	InputStream in;
	Clip push;
	AudioInputStream ais1,ais2;
	public AudioPlayer (int playSound) throws LineUnavailableException, IOException {
		if (playSound == 1)
			playMissilesound();
		if (playSound == 2)
			playGameoversound();
			
	}
	
	public AudioPlayer () throws IOException, InterruptedException, LineUnavailableException {
		
        URL url = new URL(
                "file:///C://Users//Karthik//workspace_j2ee//Orbital_Forces//src//sounds//soundtrack.wav");
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            
			try {
				ais1 = AudioSystem.
				    getAudioInputStream( url );
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            clip.open(ais1);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            //------------------------
            /*
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // A GUI element to prevent the Clip's daemon Thread
                    // from terminating at the end of the main()
                    JOptionPane.showMessageDialog(null, "Close to exit!");
                }
            });
            */
            //------------------------
	}

	private void playMissilesound () throws LineUnavailableException, IOException {
    	//push.start();
		URL url = new URL(
                "file:///C://Users//Karthik//workspace_j2ee//Orbital_Forces//src//sounds//missle.wav");
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            
			try {
				ais2 = AudioSystem.
				    getAudioInputStream( url );
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            clip.open(ais2);
            clip.start();
            
    }
	
	private void playGameoversound() throws LineUnavailableException, IOException {
		
		URL url = new URL(
                "file:///C://Users//Karthik//workspace_j2ee//Orbital_Forces//src//sounds//gameover.wav");
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            
			try {
				ais2 = AudioSystem.
				    getAudioInputStream( url );
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            clip.open(ais2);
            clip.start();
	}
}
