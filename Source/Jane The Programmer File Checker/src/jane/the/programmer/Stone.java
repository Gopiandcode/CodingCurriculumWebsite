package jane.the.programmer;

import javax.swing.JOptionPane;

public class Stone {


	
	public void crush(){
		JOptionPane.showInputDialog(null, "The" + this.getClass().getSimpleName() +" Is Crushed!");
	}
	
	
	
}
