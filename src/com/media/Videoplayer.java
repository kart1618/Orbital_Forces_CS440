package com.media;
import java.io.IOException;
import java.io.*;
import javax.swing.JFrame;

import com.sun.media.protocol.avi.DataSource;

public class Videoplayer extends JFrame {
	
    public void takeoffAVI() {
    	try  {
    		Process p=Runtime.getRuntime().exec("cmd /c vlc C:\\Users\\Karthik\\workspace_j2ee\\Orbital_Forces\\src\\clips\\takeoff.avi");
    		
    		try {
				Thread.sleep(33000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		p=Runtime.getRuntime().exec("cmd /c taskkill /F /IM vlc*");
    		}
    catch(IOException e) {}
    }
    public void landingAVI() {
    	try  {
    		//Process p=Runtime.getRuntime().exec("cmd /c vlc C:\\videos\\landing.avi");
    		Process p=Runtime.getRuntime().exec("cmd /c vlc C:\\Users\\Karthik\\workspace_j2ee\\Orbital_Forces\\src\\clips\\landing.avi");
    		
    		try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		p=Runtime.getRuntime().exec("cmd /c taskkill /F /IM vlc*");
    		}
    catch(IOException e) {}
    }
}