package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;

public class VariableTracker<T> {

	 private T t;
	 private int x,y;
	 
	 public VariableTracker(T var){
		 t = var;
		 x = 400;
		 y = 500;
	 }

	 	public void draw(Graphics2D g){
	 		g.setColor(Color.DARK_GRAY);
	 		g.fillRect(x-10, y-30, 40, 40);
	 		g.setColor(Color.black);
	 		g.drawRect(x-10, y-30, 40, 40);
	 		g.setColor(Color.white);
	 		g.drawString(t.toString(), x, y);
	 	}
	 	public void update(T var){
	 		t = var;
	 	}
	 
	    public void set(T t) { this.t = t; }
	    public T get() { return t; }
	
	
	
}
