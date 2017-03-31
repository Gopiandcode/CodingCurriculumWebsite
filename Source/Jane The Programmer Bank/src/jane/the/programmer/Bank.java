package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Bank extends Entity{

	public Bank(int x, int y) {
		super(x, y);
		name = "Bank";
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y,100, 100);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 26));
		g.drawString(name, x, y);
		TestQuestion.drawString(g, speech, x, y-50, 250);
	}

	@Override
	public void speak(String speech) {
		this.speech = speech;
	}
}
