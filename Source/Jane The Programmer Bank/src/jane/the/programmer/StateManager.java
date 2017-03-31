package jane.the.programmer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class StateManager {
	
	public State[] gameStates;
	public int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVELSEL = 2;
	public static final int PAUSE = 3;
	public static final int GAMEOVER = 4;
	public static final int Test = 5;
	
	public StateManager() {
		
		gameStates = new State[6];
		
		currentState = MENUSTATE;
		setState(currentState);
		
		
	}
	
	private void unloadState(int state){
		gameStates[state] = null;
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE){
			gameStates[state] = new GenericQuestionState(this);
		}

		
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(state);
		gameStates[currentState].init();
		
	}
	
	public void update() {
		if(gameStates[currentState] != null){
		gameStates[currentState].update();
		}
	}
	
	public void draw(java.awt.Graphics2D g) {
		if(gameStates[currentState] != null){
		gameStates[currentState].draw(g);
		}
		
	}


	public void keyPressed(KeyEvent key) {
		if(gameStates[currentState] != null){
		gameStates[currentState].keyPressed(key);
		}
	}
	
	public void mousePressed(MouseEvent e){
		if(gameStates[currentState] != null){
			gameStates[currentState].mousePressed(e);;
			}
	}
	
	public State getGamestate(){
		return gameStates[currentState];
	}
	

	
}









