package jane.the.programmer;

import java.awt.Point;

public class SimulatorHelper {
	
	//  public static final double G = 6.67300E-11;
	  public static final double G = 10;	
	  public double calcDirection(double dx,double dy){
		  
		  return Math.atan2(dx, dy);
		  
	  }
	  
	public double calcVelocityOfParticle(Particle p,Point b,double m){
		double r = Math.sqrt(Math.pow((p.x-b.x),2)+Math.pow((p.y-b.y),2));
		double v = Math.sqrt((G*m)/r);
		return v;
	}
	
}
