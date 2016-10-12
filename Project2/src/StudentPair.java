
public class StudentPair implements Comparable<StudentPair>{
	private int ADDRESS;
	private int KEY;
	
	public StudentPair(int Key, int Address){
		this.ADDRESS = Address;
		this.KEY = Key;
	}
	
	public void set(int Key, int Address){
		this.ADDRESS = Address;
		this.KEY = Key;
	}
	
	public int getAddress(){
		return this.ADDRESS;
	}
	
	public int getKey(){
		return this.KEY;
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
		return "Student Address: " + this.ADDRESS + " Student Key: " + this.KEY;
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