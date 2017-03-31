package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class RockCrusherEntity extends Entity{

	
	public RockCrusherEntity(int x, int y) {
		// TODO Auto-generated constructor stub
		
		super(x, y);
		name = "RockCrusher";
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y,100, 100);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 26));
		g.drawString(name, x, y);
		TestQuestion.drawString(g, speech, x, y-100, 350);
	}

	@Override
	public void speak(String speech) {
		this.speech = speech;
	}

}
