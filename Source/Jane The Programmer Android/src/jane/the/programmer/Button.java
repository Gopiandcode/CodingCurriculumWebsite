package jane.the.programmer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Button {

	protected int x,y,width,height;
	

	
	GenericQuestionState state;
	
	public abstract void draw(Graphics2D g);
	
public abstract void onClick(Point p);
	
	protected boolean isInside(Point p){
		if(new Rectangle(x, y, width, height).contains(p)){
			return true;
		}
		else{
			return false;
		}
	}

}
