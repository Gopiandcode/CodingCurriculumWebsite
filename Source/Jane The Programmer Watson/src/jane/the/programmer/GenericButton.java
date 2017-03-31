package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class GenericButton extends Button{

	
	String text;
	
	
	public GenericButton(GenericQuestionState state,int x,int y,String text) {
		// TODO Auto-generated constructor stub
		this.state = state;
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	
	
	public GenericButton(GenericQuestionState state,int x,int y) {
		// TODO Auto-generated constructor stub
		this.state = state;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);
		g.fillRect(x, y, 160, 40);
		g.setColor(Color.black);
		g.drawString(text, x, y+20);
	}

	@Override
	public void onClick(Point p) {
		// TODO Auto-generated method stub
		System.out.println("Cikcdf");
		if(isInside(p)){
			state.triggered = true;
		}
	}

}
