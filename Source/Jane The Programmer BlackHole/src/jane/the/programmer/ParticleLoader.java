package jane.the.programmer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

import javax.swing.JFileChooser;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ParticleLoader extends ClassLoader{

	Class c;
	public ParticleLoader() {
		//compileClass();
		try {
			c = loadClass(null);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hasAllMethods();
	}
	
	public void compileClass(){
		//Create a file chooser
		File file = new File("Submission/");
		final JFileChooser fc = new JFileChooser(file);
		//In response to a button click:
		   int returnVal = fc.showOpenDialog(null);
		   file = fc.getSelectedFile();
		   System.out.println(file.getAbsolutePath());
		   JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		   compiler.run(null, null, null, file.getAbsolutePath());
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
		   File file = new File("Submission/");
		   file.mkdir();
		   
		final JFileChooser fc = new JFileChooser(file);
		//In response to a button click:
		   int returnVal = fc.showOpenDialog(null);
		   file = fc.getSelectedFile().getParentFile();
		   System.out.println(file.getParentFile());
		   
		   try {
		       // Convert File to a URL
		       URL url = file.toURI().toURL();          // file:/c:/myclasses/
		       URL[] urls = new URL[]{url};
		       System.out.println(file.getAbsolutePath());
		       // Create a new class loader with the directory
		       ClassLoader cl = new URLClassLoader(urls);
		      
		       // Load in the class; MyClass.class should be located in
		       // the directory file:/c:/myclasses/com/mycompany
		       
		       Class cls = cl.loadClass("Particle");
		       System.out.println("Hellow");
		       return cls;
		   } catch (MalformedURLException e) {e.printStackTrace();
		   } catch (ClassNotFoundException e) {e.printStackTrace();
		   }
		   return null;
	    }
}
