import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	int score = 0;
	Rocketship rocketship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	ObjectManager(Rocketship rocketship){
		this.rocketship = rocketship;
		
	}
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	public Integer getScore() { return score; 
    }
	public void update() {
		checkCollision();
		purgeObjects();
		for(int i = 0; i < aliens.size(); i++) {
			Alien alien = aliens.get(i);
			alien.update();
			if(alien.y > LeagueInvaders.HEIGHT) {
				alien.isActive = false;
			}
			
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			projectile.update();
			
			if(projectile.y < 0) {
				projectile.isActive = false;
			}
			
		}
			
		rocketship.update();
	}
	public void draw(Graphics g) {
		rocketship.draw(g);
		for(int i = 0; i < aliens.size(); i++) {
			Alien alien = aliens.get(i);
			alien.draw(g);
			}
		
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			projectile.draw(g);
		}
		
	}
	public void purgeObjects(){
		for(int i = aliens.size()-1; i >= 0 ; i--) {
			if(!aliens.get(i).isActive) {
				aliens.remove(i);
			}
			
			}
		
		for(int i = projectiles.size()-1; i >= 0 ; i--) {
			if(!projectiles.get(i).isActive) {
				projectiles.remove(i);
			}
			
		}
	}
	public void checkCollision() {
		for(int i = 0; i < aliens.size(); i++) {
			if(rocketship.collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				rocketship.isActive = false;
				
			}
			for(int a = 0; a < projectiles.size(); a++) {
			
		
		
			if(projectiles.get(a).collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				projectiles.get(a).isActive = false;
				score++;
			}
		}
		
	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
	
}
