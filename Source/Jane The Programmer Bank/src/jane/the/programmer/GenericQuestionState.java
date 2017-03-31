package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	int timer = 0;
	boolean trigger = false;
	
	TestCaseContainer container;
	
	classLoader loader;
	
	BadGuy badguy;
	
	Bank bank;
	
	int money1 = 100;
	int money2 = 100;
	int money3 = 100;
	
	Map<String, Integer> tests = new HashMap<String, Integer>();
	
	
	VariableTracker<Integer> track = new VariableTracker<Integer>(100,100,100,"Kim");
	VariableTracker<Integer> track2 = new VariableTracker<Integer>(100,100,150,"John");
	VariableTracker<Integer> track3 = new VariableTracker<Integer>(100,100,200,"Ben");
	
	ArrayList<VariableTracker<Integer>> trackers = new ArrayList<>();
	
	Object account;
	boolean hasWithdrawmethod;
	boolean isMoneyProtected;

	Image i;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
	
		buttons.add(new GenericButton(this,600,500,"Run Tests"));
		container = new TestCaseContainer();
		badguy = new BadGuy(40, 300);
		bank = new Bank(350, 300);
		classLoader loader = new classLoader();
		try {
			account = loader.loadClass(null).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = account.getClass().getMethod("withdraw", int.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if((Integer)method.invoke(account, new Integer(100))==100){
				hasWithdrawmethod = true;
			}
		}catch (NullPointerException e){
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Field money = account.getClass().getField("money");
			if(money.getModifiers()==Modifier.PUBLIC){
				isMoneyProtected = false;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			isMoneyProtected = true;
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		for(TestQuestion question:container.cases){
			tests.put(question.name(), 100);
		}
		
		try {
			i =  ImageIO.read(getClass().getResource("Image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trackers.add(track);
		trackers.add(track2);
		trackers.add(track3);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
		if(timer>=150){
			if(!container.cases.isEmpty()){
				bank.speak(container.cases.get(0).getTestCase().split(":")[0]+", you want"
						+ " to withdraw "+ container.cases.get(0).getTestCase().split(":")[1]);
				
				if(timer>=300){
					if(hasWithdrawmethod||!isMoneyProtected){
					bank.speak("Ok then");
					}else{
						bank.speak("No, there is no access");
					}
					if(timer>=400){
						if(timer==400&&(hasWithdrawmethod||!isMoneyProtected)){
						tests.put(container.cases.get(0).getTestCase().split(":")[0],
								tests.get(container.cases.get(0).getTestCase().split(":")[0])-Integer.parseInt(container.cases.get(0).getTestCase().split(":")[1]));
						}
						
						if(timer >=550){
							badguy.visible = true;
							if(timer >=650){
								badguy.speak("Withdraw 50");
								if(timer >=750){
									if(isMoneyProtected){
										bank.speak("No, Unauthorised");
									}else{
									bank.speak("Ok");
									}
									if(timer >=800){
										bank.speak("");
										badguy.speak("");
										badguy.visible = false;
										if(!isMoneyProtected){
										tests.put(container.cases.get(0).getTestCase().split(":")[0],
												tests.get(container.cases.get(0).getTestCase().split(":")[0]) -50);
										}
										timer = 0;
										container.remove();
									}
								}
							}
						}
						
						
						
					}
				}
				
				
		}
		}
		if(trigger){
			System.out.println(timer);
			timer++;
		}
		
		
		for (VariableTracker<Integer> t:trackers) {
			try{
			t.update(tests.get(t.getName()));
			}catch(NullPointerException e){
				
			}
		}
		
	}
	

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.gray);
		g.fillRect(10, 10, 600-40, 600-60);
		g.drawImage(i, 10, 10, 600-40, 600-60, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		container.draw(g);
		for(Button b:buttons){
			b.draw(g);
		}
		badguy.draw(g);
		bank.draw(g);
		
		for (VariableTracker<Integer> t:trackers) {
			t.draw(g);
		}
		
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
