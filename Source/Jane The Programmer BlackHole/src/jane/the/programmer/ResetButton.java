package jane.the.programmer;

import java.awt.Point;

public class ResetButton extends GenericButton{

	public ResetButton(GenericQuestionState state, int x, int y,String title) {
		super(state, x, y,title);
		// TODO Auto-generated constructor stub
	}
	
	public void onClick(Point p){
		if(isInside(p)){
			state.reset();
		}
	}

}
