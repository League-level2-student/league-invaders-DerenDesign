import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Timer alienSpawn;
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    Rocketship rocketship = new Rocketship(250, 700, 50, 50);
    ObjectManager objectmanager = new ObjectManager(rocketship);
    int currentState = MENU;
    Timer frameDraw;
    Font titleFont;
    Font titleFont2;
    Font titleFont3;
    Font titleFont4;
    GamePanel(){
    	 frameDraw = new Timer(1000/60,this);
    	 frameDraw.start();
    	  titleFont = new Font("Arial", Font.PLAIN, 45);
    	  titleFont2 = new Font("Arial", Font.PLAIN, 30);
    	  titleFont3 = new Font("Arial", Font.PLAIN, 25);
    	  titleFont4 = new Font("Arial", Font.PLAIN, 55);
    	  
    	  if (needImage) {
  	        loadImage("space.png");
  	    }
    }
    @Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
		
	}
    
    
    void updateMenuState() {  
    	
    }
    void updateGameState() {  
    	objectmanager.update();
    	if(!rocketship.isActive) {
    		currentState = END;
    	}
    	
    }
    void updateEndState()  {  
 
    }
    void startGame() {
    	  alienSpawn = new Timer(1000, objectmanager);
    	   alienSpawn.start();
    }
    void loadImage(String imageFile) {
        if (needImage) {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
    	    gotImage = true;
            } catch (Exception e) {
                
            }
            needImage = false;
        }
        
    }
    void drawMenuState(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    g.setFont(titleFont);
    g.setColor(Color.WHITE);
    g.drawString("LEAGUE INVADERS", 30, 100);
    g.setFont(titleFont2);
    g.setColor(Color.WHITE);
    g.drawString("Press ENTER to start", 90, 350);
    g.setFont(titleFont3);
    g.setColor(Color.WHITE);
    g.drawString("Press SPACE for instructions", 70, 550);
    
    }
    void drawGameState(Graphics g) { 
    	if (gotImage) {
    		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
    	} else {
    		g.setColor(Color.BLUE);
    		g.fillRect(0, 0, WIDTH, HEIGHT);
    	}
    	objectmanager.draw(g);  
    	g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Score:"+objectmanager.getScore(), 50, 50);	
 	}
   
    void drawEndState(Graphics g)  { 
    g.setColor(Color.RED);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    g.setFont(titleFont4);
    g.setColor(Color.WHITE);
    g.drawString("GAME OVER",70, 150);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		   // System.out.println("Action");
		    repaint();
		}else if(currentState == GAME){
		    updateGameState();
		    //System.out.println("Action");
		    repaint();
		}else if(currentState == END){
		    updateEndState();
		    //System.out.println("Action");
		    alienSpawn.stop();
		    repaint();
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			rocketship.up();
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocketship.down();
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocketship.left();
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocketship.right();
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			objectmanager.addProjectile(rocketship.getProjectile());
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == END) {
				rocketship = new Rocketship(250, 700, 50, 50);
			}
		}

		startGame();
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		} 
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		   // System.out.println("UP");
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		   // System.out.println("DOWN");
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    //System.out.println("LEFT");
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		   // System.out.println("RIGHT");
		}
		
	}
	
}
