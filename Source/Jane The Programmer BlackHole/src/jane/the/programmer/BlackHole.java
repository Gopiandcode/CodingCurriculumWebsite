package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;

public class BlackHole extends Particle {

	public void draw(Graphics2D g){
		
		g.setColor(Color.BLACK);
		g.fillOval((int)x,(int)y, 25, 25);
	}
	public void update(){
		
	}
}
