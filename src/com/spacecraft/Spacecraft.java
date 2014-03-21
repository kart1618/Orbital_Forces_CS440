package com.spacecraft;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.media.AudioPlayer;
import com.scoreconsolemanager.Persist;
import com.scoreconsolemanager.Scorestats;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Spacecraft {

    private String craft = "/images/usse.jpg";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int moveLeft;
    private static int pauseToggle;
	int moveRight, moveUp, moveDown, shoot;
    private Image image;
    private int width, height;
    private int sniperWidth, sniperHeight;
    boolean visible;
    private boolean pause;
    private ArrayList missiles;
    ImageIcon ii;
    ImageIcon ii2;
    private final int CRAFT_SIZE = 20;
    private AudioPlayer playSounds;
    private Scorestats sc = new Scorestats();
    private Properties prop = new Properties();
    
	public Spacecraft() {
        ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        
        missiles = new ArrayList();
        
        x = 5;
        y = 5;
        pauseToggle = 1;
        visible = true;
        pause = false;
        
        		
    }


    public void move() {
        x += dx;
        y += dy;
        
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }
    
    public int getMoveleft () {
    	return moveLeft;
    }
    
    public void setMoveleft (int moveLeft) {
    	this.moveLeft =  moveLeft;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    

    public ArrayList getMissiles() {
        return missiles;
    }
    
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }
    
    public void setPause(boolean pause) {
    	this.pause = pause;
    }
    public boolean isVisible() {
        return visible;
    }
    
    public boolean isPaused() {
    	return pause;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public Rectangle getSniperbounds() {
        return new Rectangle(x, y, sniperWidth, sniperHeight);
    }
    
    public int getPausetogglevalue() {
    	return pauseToggle;
    }
    
    public void setPausetogglevalue(int pauseToggle) {
    	this.pauseToggle = pauseToggle;
    }
    
    public void keyPressed(KeyEvent e) {

    	try {
    		int key = e.getKeyCode();
	        char c = (char)e.getKeyCode();
    		/////////
    		if(new Persist().settingsChanged()) {
    			
    		prop.load(new FileInputStream ("settings.properties"));
				        
	        if (key == KeyEvent.VK_SPACE) {
	        	try {
	        		if(!(this.isPaused()) && new Scorestats().getAmmocount() > 0) {
	        		fire();
					playSounds = new AudioPlayer(1);
	        		}
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	sc.setAmmocount(sc.getAmmocount()-1);
	        }

	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveLeft"))) {
	        	if(!(this.isPaused()))
		            dx = -1;
	        }
	        
	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveRight"))) {
	        	if(!(this.isPaused()))
	            dx = 1;
	        }

	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveUp"))) {
	        	if(!(this.isPaused()))
	            dy = -1;
	        }

	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveDown"))) {
	        	if(!(this.isPaused()))
	            dy = 1;
	        }
	        
	        if (key == KeyEvent.VK_P) {
	        	pauseToggle = pauseToggle + 1;
	        	if ( pauseToggle % 2 == 0 ) {
	        		this.setPause(true);
	        		try {
						Thread.sleep(500);
						new Persist().pauseImagesetVisible(true);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
	        	}
	        	else {
	        		this.setPause(false);
	        		try {
						Thread.sleep(500);
						new Persist().pauseImagesetVisible(false);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        }
    	}
    		else {
    			//
    			if (key == KeyEvent.VK_A) {
    				if(!(this.isPaused()))
    	            dx = -1;
    	        }

    	        if (key == KeyEvent.VK_D) {
    	        	if(!(this.isPaused()))
    	            dx = 1;
    	        }

    	        if (key == KeyEvent.VK_W) {
    	        	if(!(this.isPaused()))
    	            dy = -1;
    	        }

    	        if (key == KeyEvent.VK_S) {
    	        	if(!(this.isPaused()))
    	            dy = 1;
    	        }
    	        
    	        if (key == KeyEvent.VK_SPACE) {
    	        	try {
    	        		if(!(this.isPaused()) && new Scorestats().getAmmocount() > 0) {
    	        		fire();
    					playSounds = new AudioPlayer(1);
    	        		}
    				} catch (LineUnavailableException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    	        	sc.setAmmocount(sc.getAmmocount()-1);
    	        }
    	        
    	        if (key == KeyEvent.VK_P) {
    	        	pauseToggle = pauseToggle + 1;
    	        	if ( pauseToggle % 2 == 0 ) {
    	        		this.setPause(true);
    	        		try {
    	        			new Persist().pauseImagesetVisible(true);
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    	        	}
    	        	else {
    	        		this.setPause(false);
    	        		try {
    						new Persist().pauseImagesetVisible(false);
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    	        	}
    	        }

    			//
    		}
		} 
    	catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	///////////////////////////////////
    }

    public void fire() {
        missiles.add(new Ammo(x + CRAFT_SIZE, y + CRAFT_SIZE/2));
    }

    public void keyReleased(KeyEvent e) {
    /*   
     * 
     * int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
        
        if (key == KeyEvent.VK_SPACE) {
        	try {
				playSounds = new AudioPlayer(1);
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	sc.setAmmocount(sc.getAmmocount()-1);
        }
        
        if (key == KeyEvent.VK_P) {
        	pauseToggle = pauseToggle + 1;
        	if ( pauseToggle % 2 == 0 ) 
        		this.setPause(true);
        	else
        		this.setPause(false);
        }
        */
    	

    	try {
    		
    		int key = e.getKeyCode();
	        char c = (char)e.getKeyCode();
	        
	        if(new Persist().settingsChanged()) {
	        	
	        	prop.load(new FileInputStream ("settings.properties"));
	        	
	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveLeft"))) {
	        	if(!(this.isPaused()))
		            dx = 0;
	        }
	        
	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveRight"))) {
	        	if(!(this.isPaused()))
	            dx = 0;
	        }

	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveUp"))) {
	        	if(!(this.isPaused()))
	            dy = 0;
	        }

	        if (String.valueOf(c).equalsIgnoreCase(prop.getProperty("moveDown"))) {
	        	if(!(this.isPaused()))
	            dy = 0;
	        }
	        
	       
	       }
	        
	        else {
	        	if (key == KeyEvent.VK_A) {
	                dx = 0;
	            }

	            if (key == KeyEvent.VK_D) {
	                dx = 0;
	            }

	            if (key == KeyEvent.VK_W) {
	                dy = 0;
	            }

	            if (key == KeyEvent.VK_S) {
	                dy = 0;
	            }
	           
	        }
	        
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
    	
   }