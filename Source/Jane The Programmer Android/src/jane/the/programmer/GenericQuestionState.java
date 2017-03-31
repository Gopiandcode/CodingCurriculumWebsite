package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();

	Android andy;

	VariableTracker<Integer> track;
	
	TestCaseContainer container;
	classLoader loader;
	
	Image i;
	
	Button leftButton;
	boolean isLeftButton = false;
	
	int x=200,y=170;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,500));
		track = new VariableTracker<Integer>(10);
		container = new TestCaseContainer();
		loader = new classLoader();
		andy = new Android();
		leftButton = new EventButton();
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		andy.init();
		container.remove();
		
		try {
			i =  ImageIO.read(getClass().getResource("Phone.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Object fake = loader.a.newInstance();
			
			
			loader.a.getMethod("init", null).invoke(fake, null);
			
			if((Integer)loader.a.getMethod("getActive", null).invoke(fake, null)==1){
			//	if(andy.getActive()==1){
					container.cases.add(new TestQuestion("Android OS", 600, 40));
			//	}
			}if((Integer)loader.a.getMethod("takePhoto", null).invoke(fake, null)==1){
				container.cases.add(new TestQuestion("Camera", 600, 40));
				
			}if((Integer)loader.a.getField("leftButton").get(fake)==1){
				container.cases.add(new TestQuestion("left Button", 600, 40));
				isLeftButton = true;
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
		g.drawImage(i, x,y,150,263,null);
		//leftButton.draw(g);
		container.draw(g);
	
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isLeftButton){
			leftButton.onClick(e.getPoint());
		}
	}

}
