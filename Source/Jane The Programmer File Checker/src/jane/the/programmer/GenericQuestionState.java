package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	int timer = 0;
	boolean trigger;
	
	boolean docFiles = false;
	boolean exeFiles = false;
	boolean newFiles = false;
	boolean canVerifyAll = false;
	boolean canVerifyAllBad = false;
	
	TestCaseContainer container;
	TestCaseContainer success;
	TestCaseContainer fail;
	classLoader loader;
	
	RockCrusherEntity store;
	RockCrusher sorter;
	
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,500,"Run Tests"));
		container = new TestCaseContainer();
		success = new TestCaseContainer(100, 100);
		fail = new TestCaseContainer(300, 100);
		store = new RockCrusherEntity(40, 300);
		sorter = new RockCrusher();
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		for(Method methods:loader.methlist){
			if(methods.getGenericParameterTypes()[0].toString().equals("class jane.the.programmer.DocFile")){
					docFiles = true;	
			}
			if(methods.getGenericParameterTypes()[0].toString().equals("class jane.the.programmer.ExeFile")){
				exeFiles = true;	
			}
			if(methods.getGenericParameterTypes()[0].toString().equals("class jane.the.programmer.Verifiable")){
				canVerifyAll = true;	
			}
			if(methods.getGenericParameterTypes()[0].toString().equals("class jane.the.programmer.UnVerifiable")){
				canVerifyAllBad = true;	
			}
		}
		System.out.print(docFiles);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(trigger&&!container.cases.isEmpty()){
			
				if(container.cases.get(0).s.verify()&&((docFiles&&!newFiles)||canVerifyAll)){
					TestQuestion q = container.cases.get(0);
					q.setPos(success);
					success.cases.add(q);
					container.remove();
			
			}else if((exeFiles&&!newFiles)||canVerifyAllBad){
				TestQuestion q = container.cases.get(0);
				q.setPos(fail);
				fail.cases.add(q);
				container.remove();
		
			}else{
				JOptionPane.showMessageDialog(null, "Error, cannot determine if file is valid");
			}
			
			trigger = false;
		}
		else if(container.cases.isEmpty()&&!newFiles){
			container.cases.add(new TestQuestion("Moon.MP4", 600,40, new Verifiable()));
			container.cases.add(new TestQuestion("Nasa.txt", 600,40, new Verifiable()));
			container.cases.add(new TestQuestion("fuelPod.mdl", 600,40, new UnVerifiable()));
			container.cases.add(new TestQuestion("Program.7zip", 600,40, new UnVerifiable()));
			container.cases.add(new TestQuestion("report.doc", 600,40, new Verifiable()));
			newFiles = true;
			if(!canVerifyAll)
			JOptionPane.showMessageDialog(null, "There appears to be a lot of file types, would it be wise to write verify methods for all of them?");
		}
	}
	

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.gray);
		g.fillRect(10, 10, 600-40, 600-60);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		container.draw(g);
		for(Button b:buttons){
			b.draw(g);
		}
	//	store.draw(g);
		success.draw(g);
		fail.draw(g);
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		for(Button b:buttons){
			b.onClick(e.getPoint());
		}
	}

}
