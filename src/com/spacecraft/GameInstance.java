package com.spacecraft;

import javax.imageio.ImageIO;
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.ImageIcon;

import org.imgscalr.Scalr;

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
	private Thread radiation1;
	private Thread radiation2;
	private Thread radiation3;
	Asteroid1 asteroid1Innerclass = new Asteroid1();
	Asteroid2 asteroid2Innerclass = new Asteroid2();
	Asteroid3 asteroid3Innerclass = new Asteroid3();
	Asteroid4 asteroid4Innerclass = new Asteroid4();
	Radiation1 radiation1Innerclass = new Radiation1();
	Radiation2 radiation2Innerclass = new Radiation2();
	Radiation3 radiation3Innerclass = new Radiation3();
	private Image star, cosmos, asteroid;
	private Spacecraft craft;
	private Timer timer;
	private ArrayList asteroids;
	private int gameTime;
	private int B_WIDTH;
	private int B_HEIGHT;
	private Windowinit win2 = new Windowinit();
	private int[][] pos = { 
	        {2380, 29}, {2500, 59}, {1380, 89},
	        {780, 109}, {580, 139}, {680, 239}, 
	        {790, 259}, {760, 50}, {790, 150},
	        {980, 209}, {560, 45}, {510, 70},
	        {930, 159}, {590, 80}, {530, 60},
	        {940, 59}, {990, 30}, {920, 200},
	        {900, 259}, {660, 50}, {540, 90},
	        {810, 220}, {860, 20}, {740, 180},
	        {820, 128}, {490, 170}, {700, 30}
	     };

	private int x,y,x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7;
	
	private final int DELAY = 50;
	
	/**
	 * Create the panel.
	 */
	
	public GameInstance() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
		craft = new Spacecraft ();
		
		timer = new Timer(5, this);
        timer.start();
        ImageIcon i1 = new ImageIcon(this.getClass().getResource("/images/cosmos.gif"));
        cosmos = i1.getImage();
        ImageIcon i3 = new ImageIcon(this.getClass().getResource("/images/star.gif"));
        star = i3.getImage();
        ImageIcon i4 = new ImageIcon(this.getClass().getResource("/images/asteroid.jpg"));
        asteroid = i4.getImage();
         
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
		
		public void run ()
		{
			////
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid1Cycle();
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
	        	try{
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

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid4Cycle();
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

	//**********
///////////////////////////////////
//..........
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

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid2Cycle();
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

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.nanoTime();

	        while (true) {
	        	radiation2Cycle();
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

		public void run ()
		{
			long beforeTime, timeDiff, sleep;

	        beforeTime = System.currentTimeMillis();

	        while (true) {
	        	asteroid3Cycle();
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
        radiation1 = new Thread(radiation1Innerclass);
        radiation2 = new Thread(radiation2Innerclass);
        radiation3 = new Thread(radiation3Innerclass);
        asteroid1.start();
        asteroid2.start();
        asteroid3.start();
        asteroid4.start();
        radiation1.start();
        radiation2.start();
        radiation3.start();
        animator.start();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }
	
	@Override
	public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
        if (!(new Targetplanet().getTargetplanetreached())) {
        g2d.drawImage(cosmos, 10, 10, null);
        g2d.drawImage(cosmos, 499, 10, null);
        g2d.drawImage(cosmos, 997, 150, null);// was 10
        g2d.drawImage(cosmos, 10, 585, null);
        g2d.drawImage(cosmos, 499, 585, null);
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
        
        if (radiation1Innerclass.isVisible())
            g2d.drawImage(radiation1Innerclass.getImage(), radiation1Innerclass.getX5(), radiation1Innerclass.getY5(), this);
        
        if (radiation2Innerclass.isVisible())
            g2d.drawImage(radiation2Innerclass.getImage(), radiation2Innerclass.getX6(), radiation2Innerclass.getY6(), this);
        
        if (radiation3Innerclass.isVisible())
            g2d.drawImage(radiation3Innerclass.getImage(), radiation3Innerclass.getX7(), radiation3Innerclass.getY7(), this);
        //
        Scorestats ss = new Scorestats();
        String banner = "STATS";
        String usrName = "User Name:"+new Createprofile().getUsername();
        String lives = "Lives: "+ss.getLivescount();
        String score = "Score: "+ss.getScore();
        String ammo = "Ammo: "+ss.getAmmocount();
        String oxygentanks = "Oxygen Tanks: "+ss.getoxygentankscount();
        String fuelcells = "Fuel Cells: "+ss.getFuelcellscount();
        String units = "Units: "+ ss.getUnitscount();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
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
        
        else 
        	g2d.drawImage(new Targetplanet().getImage(), 497, 15, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
	
	public boolean doWeland() {
		int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Target Planet Reached ! Would you like to Land ?", "Orbital Forces",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		    	int conquer = JOptionPane.showConfirmDialog(null, "Number of Units you Command: "+ new Scorestats().getUnitscount()+ 
		    		"\nNumber of Units required to Conquer this planet:"+ new Targetplanet().getUnitsreqd()+"\nConquer?");
		    	if (conquer == JOptionPane.YES_OPTION && new Scorestats().getUnitscount() > new Targetplanet().getUnitsreqd()) {
		    		JOptionPane.showMessageDialog(null, "You won the battle !");
		    		//new Targetplanet().setTargetplanetreached(false);
		    		new Scorestats().setAmmocount(new Scorestats().getAmmocount()+new Targetplanet().getPlanetammo());
		    		new Scorestats().setFuelcellscount(new Scorestats().getFuelcellscount()+new Targetplanet().getPlanetfuelcells());
		    		new Scorestats().setOxygentankscount(new Scorestats().getoxygentankscount()+new Targetplanet().getPlanetoxygentanks());
		    		new Scorestats().setScore(new Scorestats().getScore()+new Targetplanet().getScoreattr());
		    		new Scorestats().setUnitscount(new Scorestats().getUnitscount()+new Targetplanet().getUnitsreqd());
		    		//Windowinit win2 = new Windowinit();
		    		win2.trgtPlanetselect();
		    		new Targetplanet().setTargetplanetreached(false);
		    		return false;
		    		//win2.gameStart();
		    		
		    		//System.exit(0);
		    	}
		    	
		    	else if (conquer == JOptionPane.NO_OPTION) {
		    		return true;
		    	}
		    	
		    	else {
		    		JOptionPane.showMessageDialog(null, "You lost the battle !");
		    		return true;
		    	}
		    	
		    }
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
			new Scorestats().setLivescount(new Scorestats().getLivescount() - 1);  //Decrementing lives by 1.
			repaint();
			craft.setVisible(true);
			craft.move();
		}
        checkCollisionswithastroid1();
        checkCollisionswithastroid2();
        checkCollisionswithastroid3();
        checkCollisionswithastroid4();
        checkCollisionswithradiation1();
        checkCollisionswithradiation2();
        checkCollisionswithradiation3();
        repaint();  
    }
	
	public void checkCollisionswithastroid1() {

        Rectangle r3 = craft.getBounds();
        Rectangle r2 = asteroid1Innerclass.getBounds();
        
            if (r3.intersects(r2)) {
                craft.setVisible(false);
                asteroid1Innerclass.setVisible(false);  
            }
        ArrayList ms = craft.getMissiles();

    for (int i = 0; i < ms.size(); i++) {
        Ammo m = (Ammo) ms.get(i);

        Rectangle r1 = m.getBounds();

            if (r1.intersects(r2)) {
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

        int gameDuration;
        while(true) {
        //gameDuration = (new Targetplanet().getGametime())*1000;
        gameDuration = 20000;
        try {
            Thread.sleep(gameDuration);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        
        new Targetplanet().setTargetplanetreached(true);
        repaint();
        if (doWeland()) break;
        }
        
        System.exit(0);
    }
}
