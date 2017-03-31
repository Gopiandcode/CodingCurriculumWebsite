package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {

	public double x,y;
	public double dx,dy;
	public double mass = 0;
	public boolean alive = true;
	
	
	
	public void update(){
		x+=dx;
		y+=dy;
	}



	public void draw(Graphics2D g){
		g.setColor(Color.blue);
		g.fillOval((int)x,(int)y, 25, 25);
	}
	
	

}
