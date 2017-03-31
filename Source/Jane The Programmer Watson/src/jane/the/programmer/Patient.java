package jane.the.programmer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Patient {

	public String name;

	int x,y;
	
	Cure cure;
	ArrayList<Symptom> symptoms = new ArrayList<>();
	
	public Patient(String name,int x,int y){
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public Patient(String name,int x,int y,Cure cure){
		this.x = x;
		this.y = y;
		this.name = name;
		this.cure = cure;
	}
	
	public Patient(String name,int x,int y,Cure cure,ArrayList<Symptom> symptoms){
		this.x = x;
		this.y = y;
		this.name = name;
		this.cure = cure;
		this.symptoms = symptoms;
	}
	
	
	
	public void draw(Graphics2D g){
		
		g.setColor(Color.darkGray);
		g.fillRect(20, 215,200-41, 59);;
		g.setColor(Color.white);
		drawString(g, name, 20+10, 215+20, 160);
	}
	
	public void drawString(Graphics2D g, String s, int x, int y, int width)
	{
	    // FontMetrics gives us information about the width,
	    // height, etc. of the current Graphics object's Font.
	    FontMetrics fm = g.getFontMetrics();

	    int lineHeight = fm.getHeight();

	    int curX = x;
	    int curY = y;

	    String[] words = s.split(" ");

	    for (String word : words)
	    {
	        // Find out thw width of the word.
	        int wordWidth = fm.stringWidth(word + " ");

	        // If text exceeds the width, then move to next line.
	        if (curX + wordWidth >= x + width)
	        {
	            curY += lineHeight;
	            curX = x;
	        }

	        g.drawString(word, curX, curY);

	        // Move over to the right for next word.
	        curX += wordWidth;
	    }
	}
	
	
}
