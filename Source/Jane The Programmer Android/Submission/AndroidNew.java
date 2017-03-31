import jane.the.programmer.Android;
import jane.the.programmer.Camera;
import jane.the.programmer.Listener;


public class AndroidNew extends Android{

	

	public void init(){
		addListener(new Listener());
		super.init()
	}
	
	public int takePhoto(){
		return super.camera.takePhoto();
	}
	
}