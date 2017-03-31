package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class TestCaseContainer {

	ArrayList<TestQuestion> cases = new ArrayList<>();
	
	String name = "Remaining people";
	ArrayList<String> caseOne = new ArrayList<String>();
	ArrayList<String> caseTwo = new ArrayList<String>();
	ArrayList<String> caseThree = new ArrayList<String>();
	ArrayList<String> caseFour = new ArrayList<String>();
	
	public TestCaseContainer(){
		caseOne.add("title");
		caseTwo.add("title");
		caseTwo.add("author");
		caseThree.add("title");
		caseThree.add("author");
		caseThree.add("genre");
		cases.add(new TestQuestion("Alice", 600, 40,caseOne,"Much Ado about nothing"));
		cases.add(new TestQuestion("Jeremey", 600, 40,caseTwo));
		cases.add(new TestQuestion("Phil", 600, 40,caseThree,"Security"));
	
	}
	
	public void remove(){
		if(!cases.isEmpty()){
			cases.remove(0);
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.drawString(name, 600, 20);
		g.drawRect(600, 40, 200-40, 350);
		Iterator<TestQuestion> iter = cases.iterator();
		int i = -1;
		while(iter.hasNext()){
			TestQuestion question = iter.next();
			i++;
			question.draw(g, i);
		}
	}
}
