package jane.the.programmer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

public class GenericQuestionState extends State {


	ArrayList<Button> buttons = new ArrayList<>();
	
	
	
	Particle particle;
	BlackHole particle2;
	
	VariableTracker<Integer> track;
	VariableTracker<Integer> trackBlackHole;
	
	
	private ArrayList<Particle> ps = new ArrayList<Particle>();
	private ArrayList<BlackHole> blackHole = new ArrayList<BlackHole>();
	
	public static double dt = 0.05;
	double mass = 5000;
	private double G = 10.5;
	
	boolean hasMass = false;
	
	ParticleLoader loader;
	boolean canMove = false;
	
	public GenericQuestionState(StateManager sm) {
		this.sm = sm;
		buttons.add(new GenericButton(this,600,440,"Generate Particle"));
		buttons.add(new BlackHoleGenerateButton(this, 600, 390, "Generate BlackHole"));
		buttons.add(new ResetButton(this, 600, 300, "Reset Simulation"));
		loader = new ParticleLoader();
		track = new VariableTracker<Integer>(ps.size(),600,540);
		trackBlackHole = new VariableTracker<Integer>(ps.size(),700,540);
		
	}
	
	
	public void addParticle(){
		ps.add(new Particle());
		ps.get(ps.size()-1).x = new Random().nextInt(600-40);
		ps.get(ps.size()-1).y =new Random().nextInt(600-40);
		ps.get(ps.size()-1).dx =new Random().nextDouble()*10;
		ps.get(ps.size()-1).dy = new Random().nextDouble()*10;
	}
	
	public void reset(){
		sm.setState(sm.currentState);
	}
	
	public void addBlackHole(){
		BlackHole b = new BlackHole();
		System.out.println("Test");
		b.x = new Random().nextInt(600-40);
		b.y =new Random().nextInt(600-40);
		b.mass = 10000;
		ps.add(b);
		blackHole.add(b);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		Class cPartical = loader.c;
	
		try {
			if(cPartical.getField("x") == null&&cPartical.getField("y") == null){
				JOptionPane.showMessageDialog(null, "Simulation can't run, you don't have position variables");
				System.exit(1);
			}
			if(cPartical.getField("dx") != null&&cPartical.getField("dy") != null){
				canMove = true;
			}
			if(cPartical.getField("mass") != null){
				hasMass = true;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		particle = new Particle();
		particle.x = 000;
		particle.y = 200;
		particle.dx = 6.0;
		particle.dy = -3;
		particle2 = new BlackHole();
		particle2.x = 400;
		particle2.y = 300;
		particle2.dx = -0;
		particle2.dy = 0;
		particle2.mass = 10000;
		ps.add(particle);
		ps.add(particle2);
		blackHole.add(particle2);
		for(int i = 2;i<10;i++){
			ps.add(new Particle());
			ps.get(i).x = new Random().nextInt(600-40);
			ps.get(i).y =new Random().nextInt(600-40);
			ps.get(i).dx =new Random().nextDouble()*10;
			ps.get(i).dy = new Random().nextDouble()*10;
			if(!hasMass){
				ps.get(i).mass = 0;
			}
		}
		ps.remove(particle2);
		blackHole.remove(particle2);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(canMove){
		double dx;
		double dy;
		Particle p1;
		Particle p2;
		for(int i = 0; i < ps.size(); i++) {
			p2 = ps.get(i);
			dx = p2.dx;
			dy = p2.dy;
			for (int k = 0; k <  ps.size(); k++) {
				p1 =  ps.get(k);
				if (i == k) {
					dx += 0;
					dy += 0;
				} else {
					double dpx = p1.x - p2.x;
					double dpy = p1.y - p2.y;
					double rng2 = Math.pow(dpx, 2) + Math.pow(dpy, 2);
					
					dx += dt*G *p1.mass/(Math.pow(rng2, 1.5))*(dpx);
					
					dy += dt*G*p1.mass/(Math.pow(rng2, 1.5))*(dpy);
					
				}
				
			}
			// Top wall bounce
			if (p2.y >= 600-70) {
				p2.y = 600-70;
				if (p2.dy > 0)
					dy = -dy;
			}
			// Right wall bounce
			if (p2.x >= 600-40) {
				p2.x = 600-40;
				if (p2.dx > 0)
					dx = -dx;
			}
			// Bottom wall bounce
			if (p2.y <= -0) {
				p2.y = -0;
				if (p2.dy < 0)
					dy = -dy;
			}
			// Left wall bounce
			if (p2.x <= -0) {
				p2.x = 0;
				if (p2.dx < 0)
					dx = -dx;
			}
			ps.get(i).dx = dx;
			ps.get(i).dy = dy;
			ps.get(i).update();

			
			Iterator<Particle> iter = ps.iterator();
			try{
			while (iter.hasNext()) {
				
			    Particle particle = iter.next();
			    for(BlackHole b:blackHole){
			   
			    if (new Rectangle((int)particle.x, (int)particle.y, 25, 25).intersects(new Rectangle((int)b.x-25,(int) b.y-25, 50, 50))&&!(particle instanceof BlackHole))
			        iter.remove();
			  
			    }
			}
			}catch(Exception e){};
			
}
		//ps.get(1).mass+=200;
		track.update(ps.size()-blackHole.size());
		trackBlackHole.update(blackHole.size());
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
		g.drawString("Black hole simulation", 600, 20);
//		g.drawRect(600, 40, 200-40, 350);
		for(Button b:buttons){
			b.draw(g);
		}
		track.draw(g);
		trackBlackHole.draw(g);
	
		Iterator<Particle> iter = ps.iterator();
		while (iter.hasNext()) {
			Particle particle = iter.next();
			drawParticle(g,particle);
		}
		Iterator<BlackHole> iterBlackHole = blackHole.iterator();
		while (iterBlackHole.hasNext()) {
			BlackHole particle = iterBlackHole.next();
			drawBlackHole(g,particle);
		}
		
	}
	public void drawParticle(Graphics2D g,Particle p){
		g.setColor(Color.blue);
		g.fillOval((int)p.x,(int)p.y, 25, 25);
	}
	public void drawBlackHole(Graphics2D g,BlackHole p){
		g.setColor(Color.black);
		g.fillOval((int)p.x,(int)p.y, 25, 25);
	}
	

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1){
			for(Button b:buttons){
				b.onClick(e.getPoint());
			}
		}
	}


}
