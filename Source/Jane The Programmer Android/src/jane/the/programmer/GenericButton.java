package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class GenericButton extends Button{

	
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
	}



	@Override
	public void onClick(Point p) {
		// TODO Auto-generated method stub
		
	}

}
