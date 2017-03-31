package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	int timer = 0;
	boolean trigger;
	
	TestCaseContainer container;
	
	classLoader loader;
	
	RockCrusherEntity store;
	RockCrusher crusher;
	
	boolean canCrush = false;
	boolean wontCrushRuby = false;
	
	Image i;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,500,"Run Tests"));
		container = new TestCaseContainer();
		store = new RockCrusherEntity(40, 300);
		crusher = new RockCrusher();
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		
		
		try {
			i =  ImageIO.read(getClass().getResource("Crusher.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for(Method method:loader.c.getMethods()){
		if(method.getName().equals("crush")){
			System.out.println(method.getParameters()[0].getType().toString());
			if(method.getParameters()[0].getType().toString().contains("Stone")){
				System.out.println("Can");
				canCrush = true;
			}
			else if(method.getParameters()[0].getType().toString().contains("Ruby")){
				wontCrushRuby = true;
			}
		}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(trigger&&!container.cases.isEmpty()){
			System.out.println(canCrush);
			if(container.cases.get(0).s instanceof Ruby && wontCrushRuby){
				crusher.crush((Ruby)container.cases.get(0).s);
			}else if(canCrush){
				crusher.crush(container.cases.get(0).s);
			}
			container.remove();
			trigger = false;
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
	//	store.draw(g);
		
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
