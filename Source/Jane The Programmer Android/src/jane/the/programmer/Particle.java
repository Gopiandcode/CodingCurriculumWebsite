package jane.the.programmer;

public class Particle {

	public double x,y;
	public double dx,dy;
	public double mass = 0;
	public boolean alive = true;
	
	
	
	public void update(){
		x+=dx;
		y+=dy;
	}
	

}
