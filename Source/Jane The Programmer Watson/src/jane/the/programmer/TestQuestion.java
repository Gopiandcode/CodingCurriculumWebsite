package jane.the.programmer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TestQuestion {

	private String pName;
	private int x,y;
	String total = "The lord of the files";
	
	Patient p;
	
	public TestQuestion(String pName,int x,int y){
		this.pName = pName;
		this.x = x;
		this.y = y;
		p = new Patient(pName, x, y);
	}
	public TestQuestion(Patient p,int x,int y){
		this.x = x;
		this.y = y;
		this.p = p;
		this.pName = p.name;
	}

	
	public static String wrapString(String s, String deliminator, int length) {
	    String result = "";
	    int lastdelimPos = 0;
	    for (String token : s.split(" ", -1)) {
	        if (result.length() - lastdelimPos + token.length() > length) {
	            result = result + deliminator + token;
	            lastdelimPos = result.length() + 1;
	        }
	        else {
	            result += (result.isEmpty() ? "" : " ") + token;
	        }
	    }
	
	    return result;
	}
	

	public void draw(Graphics2D g,int row){
		
		g.setColor(Color.gray);
		g.fillRect(x+1, y+(row*60),200-41, 60);;
		g.setColor(Color.black);
		drawString(g, pName, x, y+20+(row*60), 160);
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
