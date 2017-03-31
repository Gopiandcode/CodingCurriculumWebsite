package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class TestCaseContainer {

	ArrayList<TestQuestion> cases = new ArrayList<>();
	
	String name = "Remaining files";
	
	int x = 600,y = 40;
	
	public TestCaseContainer(){
		
		cases.add(new TestQuestion("Kiran.doc", x, y,new DocFile()));
		cases.add(new TestQuestion("virus.exe", x, y,new ExeFile()));
		cases.add(new TestQuestion("bi_weeklyrepot.pdf", x, y,new DocFile()));
	}
	
	
	public TestCaseContainer(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	
	public void remove(){
		if(!cases.isEmpty()){
			cases.remove(0);
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.drawString(name, 600, 20);
		g.drawRect(x, y, 200-40, 350);
		Iterator<TestQuestion> iter = cases.iterator();
		int i = -1;
		
		while(iter.hasNext()){
			TestQuestion question = iter.next();
			i++;
			question.draw(g, i);
		}
	}
}
