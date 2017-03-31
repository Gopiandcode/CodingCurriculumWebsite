package jane.the.programmer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

	
	protected StateManager sm;
	
	/**This method is called when the state is loaded, use this method to initialise variables
	 * and set up other objects  */
	public abstract void init();
	/**This method is called once every frame to update the contents of the state */
	public abstract void update();
	/**This method is called once every frame to draw the contents of the state */
	public abstract void draw(java.awt.Graphics2D g);
	/**This method is used for keyboard input. */
	public abstract void keyPressed(KeyEvent key);
	
	public abstract void mousePressed(MouseEvent e);
	
}
