package jane.the.programmer;
import javax.swing.JFrame;


public class Frame {

	public static final int WIDTH = 800,HEIGHT = 600;
	
	public Frame(String title){
		JFrame f = new JFrame();
		f.setSize(WIDTH, HEIGHT);

		System.out.println(1 * 2 * 3 / 4 + 5 / 6 + 7);
		f.setTitle(title);
		f.setLocationRelativeTo(null);
		f.add(new Panel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public static void main(String args[]){
		new Frame("Lesson 1");
	}
	
}
