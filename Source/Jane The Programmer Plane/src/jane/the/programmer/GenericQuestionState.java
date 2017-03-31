package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	int timer = 0;
	boolean trigger;
	
	TestCaseContainer container;
	classLoader loader;
	BookStore store;
	
	String currentPlane = "Land Plane";
	
	Image i;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new StartTestButton(this,600,500,"Run Tests"));
		container = new TestCaseContainer();
		store = new BookStore(40, 300);
		
	}
	
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		loader = new classLoader();
		
		try {
			i =  ImageIO.read(getClass().getResource("Asset.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = 0;
		
			if(loader.c.getName().equals("SeaPlane")){
				for(AnnotatedType t:loader.g.getAnnotatedInterfaces()){
					System.out.println(t.getType().getTypeName());
					if(t.getType().getTypeName().equals("jane.the.programmer.WaterProof")){
						buttons.add(new GenericButton(this,600,40+(45*i),loader.c.getSimpleName()));
						i+=1;
					}
				}
			
		}
			buttons.add(new GenericButton(this,600,40+(45*i),"Land Plane"));
			i+=1;
			buttons.add(new GenericButton(this,600,40+(45*i+1),"Air Plane"));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(trigger){
			if(currentPlane.compareTo("SeaPlane")==0){
				JOptionPane.showMessageDialog(null, "Good work, the sea plane is going to land.");
				trigger = false;
			}else{
				JOptionPane.showMessageDialog(null, "That Plane cannot land in the sea.");
				trigger = false;
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
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		container.draw(g);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawImage(i, 10, 10, 600-40, 600-60, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString(currentPlane, 300, 40);
		for(Button b:buttons){
			b.draw(g);
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		//store.draw(g);
		
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
