package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JOptionPane;

public class EventButton extends Button{

	public EventButton() {
		// TODO Auto-generated constructor stub
		x=200;
		y=170;
		width = 150;
		height = 263;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
	}


	@Override
	public void onClick(Point p) {
		// TODO Auto-generated method stub
		if(isInside(p)){
			JOptionPane.showMessageDialog(null,"I see you have pressed the left button");
		}
	}

}
