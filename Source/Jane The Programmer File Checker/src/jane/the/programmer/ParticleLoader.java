package jane.the.programmer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ParticleLoader extends ClassLoader{

	Class c;
	public ParticleLoader(ClassLoader parent) {
		super(parent);
		try {
			c = Class.forName("jane.the.programmer.Particle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean HasAllMethods(){
		
		for(Method method:c.getMethods()){
			if(method.toString().contains("jane.the.programmer.Particle.")){
			String s = method.toGenericString();
			s = s.replace("jane.the.programmer.Particle.", "");
			System.out.println(s);
			
			}
		}
		return false;
	}
	public boolean hasAllMethods(){
		
		for(Field method:c.getFields()){
			if(method.toString().contains("jane.the.programmer.Particle.")){
			String s = method.toGenericString();
			s = s.replace("jane.the.programmer.Particle.", "");
			System.out.println(s);
			}
		}
		return false;
	}
	   public Class loadClass(String name) throws ClassNotFoundException {
	        if(!"reflection.MyObject".equals(name))
	                return super.loadClass(name);

	        try {
	            String url = "file:C:/data/projects/tutorials/web/WEB-INF/" +
	                            "classes/reflection/MyObject.class";
	            URL myUrl = new URL(url);
	            URLConnection connection = myUrl.openConnection();
	            InputStream input = connection.getInputStream();
	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	            int data = input.read();

	            while(data != -1){
	                buffer.write(data);
	                data = input.read();
	            }

	            input.close();

	            byte[] classData = buffer.toByteArray();

	            return defineClass("reflection.MyObject",
	                    classData, 0, classData.length);

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
}
