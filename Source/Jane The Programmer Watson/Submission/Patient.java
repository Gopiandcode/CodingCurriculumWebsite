

public class Patient{
		public Symptom symptoms =  new Symptom();
		public Patient(){
			
		}
		public String getSymptom(){
			return symptoms.getName();
		}

}