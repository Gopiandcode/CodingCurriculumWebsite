package jane.the.programmer;


public class Android {

	public Camera camera = new Camera();
	public Listener listener;
	private int active;
	public int leftButton;
	
	public Android(){
		
	}
	public void init(){
		active = 1;
	}
	
	public int takePhoto(){
		return 0;
	}
	public void addListener(Listener listener){
		this.listener = listener;
		leftButton = 1;
		//System.out.println(leftButton);
		//setActive(0);
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	
	}
}
