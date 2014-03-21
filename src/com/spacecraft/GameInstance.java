package com.spacecraft;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.ImageIcon;



import com.media.AudioPlayer;
import com.media.Videoplayer;
import com.scoreconsolemanager.Persist;
import com.scoreconsolemanager.Scorestats;
import com.userprofiles.Createprofile;

import java.util.ArrayList;
import java.util.Random;

public class GameInstance extends JPanel implements Runnable, ActionListener {
	
	private Thread animator;
	private Thread asteroid1;
	private Thread asteroid2;
	private Thread asteroid3;
	private Thread asteroid4;
	private Thread asteroid5;
	private Thread asteroid6;
	private Thread radiation1;
	private Thread radiation2;
	private Thread radiation3;
	private Thread soundTrack;
	private AudioPlayer playSound;
	private Videoplayer videoPlayer = new Videoplayer();
	private Targetplanet tp = new Targetplanet();
	private Scorestats sc = new Scorestats();
	Asteroid1 asteroid1Innerclass = new Asteroid1();
	Asteroid2 asteroid2Innerclass = new Asteroid2();
	Asteroid3 asteroid3Innerclass = new Asteroid3();
	Asteroid4 asteroid4Innerclass = new Asteroid4();
	Asteroid5 asteroid5Innerclass = new Asteroid5();
	Asteroid6 asteroid6Innerclass = new Asteroid6();
	Radiation1 radiation1Innerclass = new Radiation1();
	Radiation2 radiation2Innerclass = new Radiation2();
	Radiation3 radiation3Innerclass = new Radiation3();
	Pauseimage pauseImage = new Pauseimage();
	SoundTrack soundtrack1 = new SoundTrack();
	Persist prst = new Persist();
	private Image star, cosmos, asteroid, asteroid_fade, asteroid_hearl, pause;
	private Spacecraft craft;
	private Timer timer;
	private int gameTime;
	private int B_WIDTH;
	private int B_HEIGHT;
	private Windowinit win2 = new Windowinit();
	private int x,y,x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7,x8,y8,x9,y9,x10,y10;
	private final int DELAY = 50;
	private boolean sessioninPlay = true;
	
	/**
	 * Create the panel.
	 */
	
	public GameInstance() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		craft = new Spacecraft ();
		//craft
		timer = new Timer(5, this);
        timer.start();
        ImageIcon i1 = new ImageIcon(this.getClass().getResource("/images/cosmos.gif"));
        cosmos = i1.getImage();
        ImageIcon i3 = new ImageIcon(this.getClass().getResource("/images/star.gif"));
        star = i3.getImage();
        ImageIcon i4 = new ImageIcon(this.getClass().getResource("/images/asteroid.jpg"));
        asteroid = i4.getImage();
        ImageIcon i5 = new ImageIcon(this.getClass().getResource("/images/asteroid_hearl.gif"));
        asteroid_hearl = i5.getImage();
        ImageIcon i6 = new ImageIcon(this.getClass().getResource("/images/asteroid_fade.gif"));
        asteroid_fade = i6.getImage();
        ImageIcon i7 = new ImageIcon(this.getClass().getResource("/images/pause.jpg"));
        pause = i7.getImage();
        
         x = y = 10;
         x1 = 100;
         y1 = -40;
         x2 = 250;
         y2 = -40;
         x3 = 800;
         y3 = -40;
         x4 = 500;
         y4 = -30;
         x5 = 800;
         y5 = 900;
         x6 = 700;
         y6 = -10;
         x7 = 900;
         y7 = -100;
         x8 = 900;
         y8 = 500;
	}
	
	private class SoundTrack implements Runnable
	{
		public void run ()
		{
			try {
					try {
						playSound = new AudioPlayer();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			catch (InterruptedException e){
        		System.out.println("Interrupted!");
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
		
	private class Asteroid1 implements Runnable
	{
		private String asteroid1 = "/images/asteroid.jpg";
		long shootInterval;
	    private int width;
	    private int height;
	   
	    
	    private boolean visible;
	    private Image image;
	    
		public Asteroid1 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid1));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX1() {
			return x1;
		}
		
		public int getY1() {
			return y1;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
	    
		public void setVisible(boolean visible) {
	        this.visible = visible;
	    }
		
		public Image getImage() {
	        return image;
	    }

		
		public Rectangle getBounds() {
	        return new Rectangle(x1, y1, width, height);
	    }
	    	    
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		private void asteroid1Cycle() {
	        x1 += 1;
	        y1 += 1;
	        if (isVisible() == false) {
	        	x1 = 100;
	        	y1 = -45;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e) {
	        		System.out.println("Interrupted!");
	        	}
	        }
        	
	        if (y1 > 600) {//was 240
	        	y1 = -40;
	            x1 = 100;
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x1 = y1 = -45;
	        }
	    }
		
		private void alien1Cycle() {
	        x1 -= 1;
	        y1 = 1;
	        if (isVisible() == false) {
	        	//x1 = 100;
	        	x1 = 1200;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e) {
	        		System.out.println("Interrupted!");
	        	}
	        }
        	
	        if (x1 < -15) {//was 240
	        	y1 = 1;
	            x1 = 1500;
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        /*if (new Targetplanet().getTargetplanetreached()) {
	        	x1 = y1 = -45;
	        }*/
	    }
		
		public void run ()
		{
			////
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        		asteroid1Cycle();
	        	if (craft.isPaused() == true)
	        	pausecheck();
	        	repaint();
	        	//if (isVisible() == false)
	        	//	break;
	        	timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.currentTimeMillis();
	        }
		}
			
	}
	
	private class Asteroid2 implements Runnable
	{
		private long shootInterval;
		private String asteroid2 = "/images/asteroid.jpg";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
		
		public Asteroid2 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid2));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX2() {
			return x2;
		}
		
		public int getY2() {
			return y2;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x2, y2, width, height);
	    }
		
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		private void asteroid2Cycle() {
	        x2 += 1;
	        y2 += 1;
			if (isVisible() == false) {
	        	x2 = 250;
	        	y2 = -40;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(5)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (y2 > 600) {//was 240
	        	x2 = 250;
	        	y2 = -40;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x2 = y2 = -45;
	        }
	    }

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	asteroid2Cycle();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	        	repaint();
	            timeDiff = System.nanoTime() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.nanoTime();
	        }
		}
	}

///////////////////////////////////
	private class Asteroid3 implements Runnable
	{
		private long shootInterval;
		private String asteroid3 = "/images/asteroid.jpg";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
	    
		public Asteroid3 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid3));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX3() {
			return x3;
		}
		
		public int getY3() {
			return y3;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x3, y3, width, height);
	    }
		
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		private void asteroid3Cycle() {
	        x3 -= 1;
	        y3 += 1;
			if (isVisible() == false) {
	        	x3 = 850;
	        	y3 = -40;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(5)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (y3 > 600) {//was 240
	        	x3 = 850;
	        	y3 = -40;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try {
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x3 = y3 = -45;
	        }
	    }

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	asteroid3Cycle();
	        	repaint();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	            timeDiff = System.nanoTime() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.nanoTime();
	        }
		}
	}

	//*******
	private class Asteroid4 implements Runnable
	{
		private long shootInterval;
		private String asteroid4 = "/images/asteroid.jpg";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
	    
		public Asteroid4 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid4));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX4() {
			return x4;
		}
		
		public int getY4() {
			return y4;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x4, y4, width, height);
	    }
		
		private void asteroid4Cycle() {
	        x4 -= 1;
	        y4 += 1;
			if (isVisible() == false) {
	        	x4 = 590;
	        	y4 = -30;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(5)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (y4 > 600) {//was 240
	        	x4 = 590;
	        	y4 = -30;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x4 = y4 = -45;
	        }
	    }

	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid4Cycle();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	        	repaint();
	            timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.currentTimeMillis();
	        }
		}
	}

	private class Asteroid5 implements Runnable
	{
		private String asteroid5 = "/images/asteroid_hearl.gif";
		long shootInterval;
	    private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
	    
		public Asteroid5 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid5));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX8() {
			return x8;
		}
		
		public int getY8() {
			return y8;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
	    
		public void setVisible(boolean visible) {
	        this.visible = visible;
	    }

	    public Image getImage() {
	        return image;
	    }

	    public Rectangle getBounds() {
	        return new Rectangle(x8, y8, width, height);
	    }
	    
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		private void asteroid5Cycle() {
	        x8 -= 1;
	        y8 -= 1;
	        if (isVisible() == false) {
	        	x8 = 900;
	        	y8 = 500;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e) {
	        		System.out.println("Interrupted!");
	        	}
	        }
        	
	        if (y8 < -5) {//was 240
	        	y8 = 500;
	            x8 = 900;
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x8 = 900;
	        	y8 = 500;
	        }
	    }
		
		public void run ()
		{
			////
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	asteroid5Cycle();
	        	if (craft.isPaused() == true)
	        	pausecheck();
	        	repaint();
	        	//if (isVisible() == false)
	        	//	break;
	        	timeDiff = System.nanoTime() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.nanoTime();
	        }
		}
			
	}
	
	private class Asteroid6 implements Runnable
	{
		private String asteroid6 = "/images/asteroid_fade.gif";
		long shootInterval;
	    private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
	    
		public Asteroid6 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(asteroid6));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX9() {
			return x9;
		}
		
		public int getY9() {
			return y9;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
	    
		public void setVisible(boolean visible) {
	        this.visible = visible;
	    }

	    public Image getImage() {
	        return image;
	    }

	    public Rectangle getBounds() {
	        return new Rectangle(x9, y9, width, height);
	    }
	    
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		private void asteroid6Cycle() {
	        x9 = 700;
	        y9 -= 1;
	        if (isVisible() == false) {
	        	x9 = 700;
	        	y9 = 900;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e) {
	        		System.out.println("Interrupted!");
	        	}
	        }
        	
	        if (y9 < -5) {//was 240
	        	y9 = 900;
	            x8 = 700;
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x9 = -5;
	        	y9 = -5;
	        }
	    }
		
		public void run ()
		{
			////
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	asteroid6Cycle();
	        	if (craft.isPaused() == true)
	        	pausecheck();
	        	repaint();
	        	//if (isVisible() == false)
	        	//	break;
	        	timeDiff = System.nanoTime() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.nanoTime();
	        }
		}
			
	}

	//**********
///////////////////////////////////
//..........
	
	private class Pauseimage {
		
		private String pauseImage = "/images/pause.jpg";
		private Image image;
		
		Pauseimage() {
			
			ImageIcon ii = new ImageIcon(this.getClass().getResource(pauseImage));
			image = ii.getImage();
		}
				
		public Image getImage() {
	        return image;
	    }
		
	}
	private class Radiation1 implements Runnable
	{
		private long shootInterval;
		private String radiation1 = "/images/radiation.gif";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
		
		public Radiation1 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(radiation1));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX5() {
			return x5;
		}
		
		public int getY5() {
			return y5;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x5, y5, width, height);
	    }
		
		private void asteroid2Cycle() {
	        x5 -= 1;
	        y5 -= 1;
			if (isVisible() == false) {
	        	x5 = 800;
	        	y5 = 900;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(5)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (x5 < 100) {//was 240
	        	x5 = 800;
	        	y5 = 900;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x5 = y5 = -45;
	        }
	    }

	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid2Cycle();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	        	repaint();
	            timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.currentTimeMillis();
	        }
		}
	}

//..........
	//**********
	private class Radiation2 implements Runnable
	{
		private long shootInterval;
		private String radiation2 = "/images/radiation.gif";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
		
		public Radiation2 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(radiation2));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX6() {
			return x6;
		}
		
		public int getY6() {
			return y6;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x6, y6, width, height);
	    }
		
		private void radiation2Cycle() {
	        x6 -= 1;
	        y6 += 1;
			if (isVisible() == false) {
	        	x6 = 500;
	        	y5 = -45;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(5)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (y6 > 600) {//was 240
	        	x6 = 500;
	        	y6 = -45;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x6 = y6 = -45;
	        }
	    }
		
	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	radiation2Cycle();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	        	repaint();
	            timeDiff = System.nanoTime() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }
	            
	            beforeTime = System.nanoTime();
	        }
		}
	}
	
	private class Radiation3 implements Runnable
	{
		private long shootInterval;
		private String radiation3 = "/images/radiation.gif";
		private int width;
	    private int height;
	    private boolean visible;
	    private Image image;
		
		public Radiation3 () {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(radiation3));
	        image = ii.getImage();
	        width = image.getWidth(null);
	        height = image.getHeight(null);
	        visible = true;
	    }
		
		public int getX7() {
			return x7;
		}
		
		public int getY7() {
			return y7;
		}
		
		public boolean isVisible() {
	        return visible;
	    }
		
		public void setVisible(boolean visible) {
	        this.visible=visible;
	    }
		
		public Image getImage() {
	        return image;
	    }
		
		public Rectangle getBounds() {
	        return new Rectangle(x7, y7, width, height);
	    }
		
		private void asteroid3Cycle() {
	        x7 -= 1;
	        y7 = 300;
			if (isVisible() == false) {
	        	x7 = 1400;
	        	y7 = 300;
	        	setVisible(true);
	        	shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	        }
			
	        if (x7 < -100) {//was 240
	        	x7 = 1400;
	        	y7 = 300;
	            shootInterval = (new Random()).nextInt(10)*1000;
	        	try{
	        	Thread.sleep(shootInterval);
	        	}
	        	catch (InterruptedException e){
	        		System.out.println("Interrupted!");
	        	}
	            
	        }
	        
	        if (new Targetplanet().getTargetplanetreached()) {
	        	x7 = y7 = -45;
	        }
	    }

	    private void pausecheck() {
	    	while (true) {
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if (craft.isPaused() == false)
	    			break;
	    		//infinite loop for pause
	    	}
	    }
	    
		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid3Cycle();
	        	if (craft.isPaused() == true)
		        	pausecheck();
	        	repaint();
	            timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	            }

	            beforeTime = System.currentTimeMillis();
	        }
		}
	}

	//************
	public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        asteroid1 = new Thread(asteroid1Innerclass);
        asteroid2 = new Thread(asteroid2Innerclass);
        asteroid3 = new Thread(asteroid3Innerclass);
        asteroid4 = new Thread(asteroid4Innerclass);
        asteroid5 = new Thread(asteroid5Innerclass);
        asteroid6 = new Thread(asteroid6Innerclass);
        radiation1 = new Thread(radiation1Innerclass);
        radiation2 = new Thread(radiation2Innerclass);
        radiation3 = new Thread(radiation3Innerclass);
        soundTrack = new Thread(soundtrack1);
        asteroid1.start();
        asteroid2.start();
        asteroid3.start();
        asteroid4.start();
        asteroid5.start();
        asteroid6.start();
        radiation1.start();
        radiation2.start();
        radiation3.start();
        animator.start();
        soundTrack.start();
        
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }
	
	@Override
	public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
        if (!(new Targetplanet().getTargetplanetreached())) {
        g2d.drawImage(cosmos, 10, 10, null);
        g2d.drawImage(cosmos, 10, 451, null);
        g2d.drawImage(cosmos, 701, 451, null);
        g2d.drawImage(cosmos, 701, 10, null);
        //g2d.drawImage(cosmos, 1035, 10, null);
        //g2d.drawImage(cosmos, 997, 150, null);// was 10
        //g2d.drawImage(cosmos, 10, 585, null);
        //g2d.drawImage(cosmos, 499, 585, null);
        //g2d.drawImage(cosmos, 997, 585, null);
        g2d.drawImage(star, x, y, this);
        g2d.drawImage(asteroid, x1, y1, this);
        g2d.drawImage(asteroid, x2, y2, this);
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        ArrayList ms = craft.getMissiles();
        
        for (int i = 0; i < ms.size(); i++ ) {
            Ammo m = (Ammo) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
        if (asteroid1Innerclass.isVisible())
            g2d.drawImage(asteroid1Innerclass.getImage(), asteroid1Innerclass.getX1(), asteroid1Innerclass.getY1(), this);
        
        if (asteroid2Innerclass.isVisible())
            g2d.drawImage(asteroid2Innerclass.getImage(), asteroid2Innerclass.getX2(), asteroid2Innerclass.getY2(), this);
        
        if (asteroid3Innerclass.isVisible())
            g2d.drawImage(asteroid3Innerclass.getImage(), asteroid3Innerclass.getX3(), asteroid3Innerclass.getY3(), this);
        
        if (asteroid4Innerclass.isVisible())
            g2d.drawImage(asteroid4Innerclass.getImage(), asteroid4Innerclass.getX4(), asteroid4Innerclass.getY4(), this);
        
        if (asteroid5Innerclass.isVisible())
            g2d.drawImage(asteroid5Innerclass.getImage(), asteroid5Innerclass.getX8(), asteroid5Innerclass.getY8(), this);
        
        if (asteroid6Innerclass.isVisible())
            g2d.drawImage(asteroid6Innerclass.getImage(), asteroid6Innerclass.getX9(), asteroid6Innerclass.getY9(), this);
        
        if (radiation1Innerclass.isVisible())
            g2d.drawImage(radiation1Innerclass.getImage(), radiation1Innerclass.getX5(), radiation1Innerclass.getY5(), this);
        
        if (radiation2Innerclass.isVisible())
            g2d.drawImage(radiation2Innerclass.getImage(), radiation2Innerclass.getX6(), radiation2Innerclass.getY6(), this);
        
        if (radiation3Innerclass.isVisible())
            g2d.drawImage(radiation3Innerclass.getImage(), radiation3Innerclass.getX7(), radiation3Innerclass.getY7(), this);
        
        if (prst.pauseImageisVisible())
        	g2d.drawImage(pauseImage.getImage(), 1215, 10, this);
        //
        Scorestats ss = new Scorestats();
        String banner = "STATS";
        String usrName = "User Name:"+new Createprofile().getUsername();
        
        
        String lives = "Lives: "+ sc.getLivescount();
        String score = "Score: "+ ss.getScore();
        String ammo = "Ammo: "+ ss.getAmmocount();
        String oxygentanks = "Oxygen Tanks: "+ss.getOxygentankscount();
        String fuelcells = "Fuel Cells: "+ss.getFuelcellscount();
        String units = "Units: "+ ss.getUnitscount();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(banner, (2200 - metr.stringWidth(banner)) / 2,
                     50 / 2);
        g.drawString(usrName, (2200 - metr.stringWidth(usrName)) / 2,
                90 / 2);
        g.drawString(lives, (2200 - metr.stringWidth(lives)) / 2,
                116 / 2);
        g.drawString(ammo, (2200 - metr.stringWidth(ammo)) / 2,
                141 / 2);
        g.drawString(score, (2200 - metr.stringWidth(score)) / 2,
                166 / 2);
        g.drawString(units, (2200 - metr.stringWidth(units)) / 2,
                191 / 2);
        g.drawString(fuelcells, (2200 - metr.stringWidth(fuelcells)) / 2,
                216 / 2);
        g.drawString(oxygentanks, (2200 - metr.stringWidth(oxygentanks)) / 2,
                241 / 2);
        //
        }
        
        else {
        	g2d.drawImage(new Targetplanet().getImage(), 497, 15, this);
        	craft.setX(5);
        	craft.setY(5);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
	
	public boolean doWeland() {
		
		sessioninPlay = false;
		int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Target Planet Reached ! Would you like to Land ?", "Orbital Forces",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		    	int conquer = JOptionPane.showConfirmDialog(null, "Number of Units you Command: "+ new Scorestats().getUnitscount()+ 
		    		"\nNumber of Units required to Conquer this planet:"+ new Targetplanet().getUnitsreqd()+"\nConquer?");
	    		
		    	if (conquer == JOptionPane.YES_OPTION && sc.getUnitscount() > tp.getUnitsreqd()) {
		    		videoPlayer.landingAVI();
		    		
		    		JOptionPane.showMessageDialog(null, "You won the battle !");
		    		
		    		sc.setAmmocount(sc.getAmmocount()+tp.getPlanetammo());
		    		sc.setFuelcellscount(sc.getFuelcellscount()+tp.getPlanetfuelcells());
		    		sc.setOxygentankscount(sc.getOxygentankscount()+tp.getPlanetoxygentanks());
		    		sc.setScore(sc.getScore()+tp.getScoreattr());
		    		sc.setUnitscount(sc.getUnitscount()+tp.getUnitsreqd());
		    		//sc.setLivescount(sc.getLivescount());
		    		
		    		new Persist(sc.getAmmocount(), sc.getScore(), sc.getFuelcellscount(), sc.getOxygentankscount(), sc.getUnitscount(), 3); // <- sc.getLivescount()
		    		sessioninPlay = true;
		    		//win2.trgtPlanetselect();
		    		tp.setTargetplanetreached(false);
		    		return false;
		    		
		    	}
		    	
		    	else if (conquer == JOptionPane.NO_OPTION) {
		    		return true;
		    	}
		    	
		    	else {
		    		JOptionPane.showMessageDialog(null, "You lost the battle !");
		    		return true;
		    	}
		    	
		    }
		    
		    else
		    return true;
    }

	public void actionPerformed(ActionEvent e) {
		ArrayList ms = craft.getMissiles();
		
		for (int i = 0; i < ms.size(); i++) {
            Ammo m = (Ammo) ms.get(i);
            if (m.isVisible())
                m.move();
            else ms.remove(i);
        }
		
		if (craft.isVisible())
			craft.move();//introduce the concept of 'lives' here
		else
		{
			craft.setX(5);
			craft.setY(5);
			//livesCntr++;
			//System.out.println("Before Decrementing: "+sc.getLivescount());
			if(sessioninPlay) {
			sc.setLivescount(sc.getLivescount() - 1);  //Decrementing lives by 1.
			}
			repaint();
			craft.setVisible(true);
			craft.move();
		}
        checkCollisionswithastroid1();
        checkCollisionswithastroid2();
        checkCollisionswithastroid3();
        checkCollisionswithastroid4();
        checkCollisionswithastroid5();
        checkCollisionswithastroid6();
        checkCollisionswithradiation1();
        checkCollisionswithradiation2();
        checkCollisionswithradiation3();
        repaint();  
    }
	
	public void checkCollisionswithastroid1() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid1Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid1Innerclass.setVisible(false);
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid1Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 50);
            }               
        }
    }

	public void checkCollisionswithastroid2() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid2Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid2Innerclass.setVisible(false);
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid2Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 100);
            }               
        }
    }
	
	public void checkCollisionswithastroid3() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid3Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid3Innerclass.setVisible(false);
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid3Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 100);
            }               
        }
    }
	
	public void checkCollisionswithastroid4() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid4Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid4Innerclass.setVisible(false);  
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid4Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 50);
            }               
        }
    }

	public void checkCollisionswithastroid5() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid5Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid5Innerclass.setVisible(false);  
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid5Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 100);
            }               
        }
    }

	public void checkCollisionswithastroid6() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = asteroid5Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid6Innerclass.setVisible(false);  
            }
        	
	ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

           if (r1.intersects(r4)) {
            	asteroid6Innerclass.setVisible(false);
            	m.setVisible(false);
            	new Scorestats().setScore(new Scorestats().getScore() + 100);
            }               
        }
    }
	
	public void checkCollisionswithradiation1() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = radiation1Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid4Innerclass.setVisible(false);  
            }
    }

	public void checkCollisionswithradiation2() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = radiation2Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid4Innerclass.setVisible(false);  
            }
    }

	public void checkCollisionswithradiation3() {

        Rectangle r3 = craft.getBounds();
        Rectangle r4 = radiation3Innerclass.getBounds();
               if (r3.intersects(r4)) {
                craft.setVisible(false);
                asteroid4Innerclass.setVisible(false);  
            }
    }

	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }

    public void run() {

        int gameDuration, timeUp, timeSlices;
        
       while(true) {
    	   
        //gameDuration = (new Targetplanet().getGametime())*1000;
        gameDuration =  20000;//120000;
        timeUp = 0;
        timeSlices = 2000;
        try {
        	while (timeUp != gameDuration) {
        		timeUp += timeSlices;
        	Thread.sleep(timeSlices);
        	if (sc.getLivescount() == 0) {
        		Thread.interrupted();
        		break;
        		}
        	}
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        
        if (sc.getLivescount() == 0) break;
        
        if (!(craft.isPaused()) && craft.getPausetogglevalue()==1) {
        	
        	tp.setTargetplanetreached(true);
        	repaint();
        
        	if (doWeland()) break;
       
        }
        else
        	craft.setPausetogglevalue(1);
       }
       
       		
		try {
			AudioPlayer playsound = new AudioPlayer(2);
			Thread.sleep(2000);
			 System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
      
    }
}
