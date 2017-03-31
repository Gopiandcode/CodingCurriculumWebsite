package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.LoaderHandler;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	
	VariableTracker<Integer> track;
	
	PatientCaseContainer pContainer;
	TestCaseContainer container;
	
	boolean triggered = false;
	classLoader loader;
	
	boolean unProtected;
	boolean canAccess;
	
	ArrayList<Symptom> s1 = new ArrayList<>();
	ArrayList<Symptom> s2 = new ArrayList<>();
	ArrayList<Symptom> s3 = new ArrayList<>();
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,500,"Next patient"));
		pContainer = new PatientCaseContainer();
		container = new TestCaseContainer();
		s1.add(new Symptom("Shivers", 600, 40));
		s1.add(new Symptom("Fever", 600, 40));
		s1.add(new Symptom("Sweat", 600, 40));
		s2.add(new Symptom("Rash", 600, 40));
		s2.add(new Symptom("Runny nose", 600, 40));
		s3.add(new Symptom("Boils", 600, 40));
		s3.add(new Symptom("Fever", 600, 40));
		s3.add(new Symptom("Pneumonia", 600, 40));
		container.cases.add(new TestQuestion(new Patient("Jv", 600, 40, new Cure("rest"),s1), 600, 40));
		container.cases.add(new TestQuestion(new Patient("Francesco", 600, 40, new Cure("rest/paracetamol"),s2), 600, 40));
		container.cases.add(new TestQuestion(new Patient("David", 600, 40, new Cure("penicilin"),s3), 600, 40));
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		try {
			Field f = loader.p.getField("symptoms");
			unProtected = true;
			if(!f.getType().getName().equals("Symptom")){
				
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			unProtected = false;
			try {
				Method m = loader.p.getMethod("getSymptom", null);
				Object o = loader.p.newInstance();
				try {
					String v = (String)m.invoke(o, null);
					if(v instanceof String){
						canAccess = true;
					}else{
						JOptionPane.showMessageDialog(null, "I cannot access the patient data");
						System.exit(1);
					}
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(triggered && !container.cases.isEmpty()){
			pContainer.setPatient(container.cases.get(0).p);
			container.remove();
			if(unProtected){
				pContainer.cases.add(new TestQuestion("Hiccups",  200, 41));
			}
			triggered = false;
		}else if (triggered && container.cases.isEmpty()){
			JOptionPane.showMessageDialog(null, "All patients checked");
			triggered = false;
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
		g.setColor(Color.black);
		container.draw(g);
		for(Button b:buttons){
			b.draw(g);
		}
		pContainer.draw(g);

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
