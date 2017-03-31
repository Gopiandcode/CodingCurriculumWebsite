package jane.the.programmer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class TestQuestion {

	private String pName;
	private String sName;
	public ArrayList<String> fields = new ArrayList<String>();
	private int x,y;
	int total = 0;
	
	public TestQuestion(String pName,int x,int y,int total){
		this.sName = pName;
		this.pName = pName+ ":" + total;
		this.x = x;
		this.y = y;
		this.total = total;
	}

	public String getTestCase(){
		return pName;
	}
	public String name(){
		return sName;
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
	
	public static void drawString(Graphics2D g, String s, int x, int y, int width)
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
