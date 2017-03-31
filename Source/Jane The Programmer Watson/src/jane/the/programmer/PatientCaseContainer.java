package jane.the.programmer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class PatientCaseContainer {

	ArrayList<TestQuestion> cases = new ArrayList<>();
	
	String name = "Remaining people";
	
	Patient p;
	Cure c;
	
	public PatientCaseContainer(){
		p = new Patient("Jack",20, 216,new Cure(name));
		c = p.cure;
		for(Symptom s:p.symptoms){
			cases.add(new TestQuestion(s.getname(),200,41));
		}
		cases.add(new TestQuestion("Cold", 200, 41));
		cases.add(new TestQuestion("Shivers", 200, 41));
		
	}
	
	public void remove(){
		if(!cases.isEmpty()){
			cases.remove(0);
		}
	}
	
	public void setPatient(Patient p){
		this.p = p;
		cases.clear();
		for(Symptom s:p.symptoms){
			cases.add(new TestQuestion(s.getname(),200,41));
		}
		this.c = p.cure;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.drawRect(200, 40, 200-40, 350);
		g.drawRect(20, 215, 200-40, 60);
		g.drawRect(400, 215, 200-40, 60);
		Iterator<TestQuestion> iter = cases.iterator();
		int i = -1;
		while(iter.hasNext()){
			TestQuestion question = iter.next();
			i++;
			question.draw(g, i);
		}
		p.draw(g);
		c.draw(g);
	}
}
