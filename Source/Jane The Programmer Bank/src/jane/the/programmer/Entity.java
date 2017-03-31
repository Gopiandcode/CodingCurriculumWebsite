package jane.the.programmer;

import java.awt.Graphics2D;

public abstract class Entity {

	protected String name;
	protected int x,y;
	protected String speech = "";
	
	public Entity(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	public abstract void draw(Graphics2D g);
	
	public abstract void speak(String speech);
	
	
}
