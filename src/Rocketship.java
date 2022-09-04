import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		// TODO Auto-generated constructor stub
	}

	public void up() {
		if(y > 15) {
        y-=speed;
		}
    }
	public void down() {
		if(y < LeagueInvaders.HEIGHT-90) {
        y+=speed;
		}
    }
	public void left() {
		if(x > 15) {
        x-=speed;
		}
    }
	public void right() {
		if(x < LeagueInvaders.WIDTH-70) {
        x+=speed;
		}
    }
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	     g.fillRect(x, y, width, height);
	}

}
