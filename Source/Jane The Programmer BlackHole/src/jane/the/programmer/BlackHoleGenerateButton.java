package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class BlackHoleGenerateButton extends Button{

	String text;
	
	public BlackHoleGenerateButton(GenericQuestionState state, int x, int y,
			String text) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.text = text;
	
	}

	@Override
	public void onClick(Point p){
		if(isInside(p)){
			state.addBlackHole();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		g.setColor(Color.gray);
		g.fillRect(x, y, 160, 40);
		g.setColor(Color.black);
		g.drawString(text, x, y+20);
	}
}
