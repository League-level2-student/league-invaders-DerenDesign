import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
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
	public void update() {
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
			Alien alien = aliens.get(i);
			if(!alien.isActive) {
				aliens.remove(i);
			}
			
			}
		
		for(int i = projectiles.size()-1; i >= 0 ; i--) {
			Projectile projectile = projectiles.get(i);
			if(!projectile.isActive) {
				projectiles.remove(i);
			}
			
		}
	}
}
