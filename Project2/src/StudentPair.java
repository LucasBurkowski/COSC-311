
public class StudentPair implements Comparable<StudentPair>{
	private static int ADDRESS;
	private static int KEY;
	
	public void set(int Key, int Address){
		ADDRESS = Address;
		KEY = Key;
	}
	
	public int getAddress(){
		return ADDRESS;
	}
	
	public int getKey(){
		return KEY;
	}
	
	public boolean equals(StudentPair Comparison){
		if (Comparison.getAddress() == ADDRESS && Comparison.getKey() == KEY){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String printPair(StudentPair input){
		int Address = input.getAddress();
		int Key = input.getKey();
		String output = ("Student Address: " + Address + " Student Key: " + Key); 
		return output;
	}
	
	@Override
	public String toString(){
		return this.getClass().+ "Student Address: " + ADDRESS + " Student Key: " + KEY;
	}
	
	@Override
	public int compareTo(StudentPair anotherStudentPair) {
		if(!(anotherStudentPair instanceof StudentPair)){
			throw new ClassCastException("StudentPair Object Expected");
		}
		int anotherStudentAdd = anotherStudentPair.getAddress();
		int anotherStudentKey = anotherStudentPair.getKey();
		int compare = (anotherStudentAdd - ADDRESS) + (anotherStudentKey - KEY);
		return compare;
	}
	
}