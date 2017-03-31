package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	int timer = 0;
	boolean trigger;
	
	TestCaseContainer container;
	
	classLoader loader;
	
	Contraption store;
	
	Image i;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,500,"Run Tests"));
		container = new TestCaseContainer();
		store = new Contraption(40, 300);
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		
		try {
			i =  ImageIO.read(getClass().getResource("Untitled.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(timer>=150){
			if(!container.cases.isEmpty()){
				store.speak(container.cases.get(0).getTestCase().split(":")[0]+", you want"
						+ " the book  "+ container.cases.get(0).getTestCase().split(":")[1]);
				
				if(timer>=300){
					boolean isCommon = false;
					for(String s:container.cases.get(0).fields){
						if(loader.fields.contains(s)){
							isCommon = true;
						}else{
							isCommon = false;
						}
					}
				if(isCommon){
					store.speak("We have it");
					if(timer>=400){
						container.remove();
						timer = 0;
					}
				}else{
					List<String> c = new ArrayList<>(container.cases.get(0).fields);
					c.removeAll(loader.fields);
					String[] s = c.toArray(new String[0]);
		
					String list = "";
					for(String string:s){
						list += string + " ";
					}
					store.speak("I think your description is missing some things: "+list);
					
					
					
					if(timer>400){
						store.speak("");
						timer = 0;
						trigger = false;
					}
				}
				}
				
				
			}
			
		}
		if(trigger){
			timer++;
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
		store.draw(g);
		
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
