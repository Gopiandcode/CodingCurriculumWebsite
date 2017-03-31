package jane.the.programmer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;




public class Panel extends JPanel implements Runnable, KeyListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Thread mainThread;
	
	//to maintain a constant fps
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	BufferedImage img;

	
	
	private StateManager sm;
	
	public Panel(){
		image = new BufferedImage(
				800, 600,
				BufferedImage.TYPE_INT_RGB
			);
	g = (Graphics2D) image.getGraphics();
	running = true;
	
	
	sm = new StateManager();
	}
	
	public void addNotify() {
		super.addNotify();
		if(mainThread == null) {
			mainThread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			mainThread.start();
		}
	}
	

	
	@Override
	public void run() {
		
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			//System.out.print(running);

			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	private void update() {
		// TODO Auto-generated method stub
		sm.update();
	}
	private void draw() {
		// TODO Auto-generated method stub
		sm.draw(g);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		sm.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				800, 600,
				null);
		g2.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		sm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
