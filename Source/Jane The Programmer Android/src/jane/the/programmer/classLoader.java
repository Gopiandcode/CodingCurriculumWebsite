package jane.the.programmer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class classLoader {

	Class a;
	Class c;
	
	public ArrayList<String> fields = new ArrayList<>();
	
	
	public classLoader() {
		try {
			c = loadClass("Camera");
			a = (Class) loadClass("AndroidNew");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Field method:c.getFields()){
			System.out.println(method.toGenericString());
			fields.add(method.toGenericString().replace("public java.lang.String Book.", ""));
		}
	}
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		   
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
		       URLClassLoader cl = new URLClassLoader(urls);
		       //System.out.println(cl.getResource("jane.the.programmer.Book"));
		       // Load in the class; MyClass.class should be located in
		       // the directory file:/c:/myclasses/com/mycompany
		       Class cls = cl.loadClass(name);
		       System.out.println(cls.getCanonicalName());
		       try {
				cl.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       return cls;
		   } catch (MalformedURLException e) {e.printStackTrace();
		   } catch (ClassNotFoundException e) {e.printStackTrace();
		   }
		   return null;
	    }
	
	
}
