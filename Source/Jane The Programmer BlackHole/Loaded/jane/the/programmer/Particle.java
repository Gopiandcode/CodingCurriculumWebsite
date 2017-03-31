package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {

	public double x;
	public double dx,dy;
	public double mass = 0;
	public boolean alive = true;
	
	
	
	public void update(){
		x+=dx;
		//y+=dy;
	}


	
	

}
