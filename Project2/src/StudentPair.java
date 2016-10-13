/*
 * Lucas Burkowski 9/21/2016
 * 
 *  StudentPair:
 *  It contains the following classes: StudentPair(), set(), getAddress(), getKey(),
 *  toString(), and compareTo().  These methods correspond with the creation and 
 *  modification of StudentPair Objects to be used in a index. 
 */

public class StudentPair implements Comparable<StudentPair>{
	private int ADDRESS;
	private int KEY;
	
	/**
     * Construct a new StudentPair
     * @param Key - The ID of the Student
     * @param Address - The Location of the Student in the database and index
     */
	public StudentPair(int Key, int Address){
		this.ADDRESS = Address;
		this.KEY = Key;
	}
	
	/**
     * Change a StudentPair's parameters
     * @param Key - The ID of the Student
     * @param Address - The Location of the Student in the database and index
     */
	public void set(int Key, int Address){
		this.ADDRESS = Address;
		this.KEY = Key;
	}
	
	/**
     * Find the Address of the current StudentPair
     * @returns The Address of the StudentPair
     */
	public int getAddress(){
		return this.ADDRESS;
	}
	
	/**
     * Find the Key of the current StudentPair
     * @returns The Key of the StudentPair
     */
	public int getKey(){
		return this.KEY;
	}
	
	/**
     * Obtain a string representation of the StudentPair
     * @return A String representation of the StudentPair
     */
	@Override
	public String toString(){
		return "Student Address: " + this.ADDRESS + " Student Key: " + this.KEY;
	}

	/**
     * Compare a set of StudentPairs
     * @param anotherStudentPair - The StudentPair to compare to the current StudentPair
     * @return 0 if the same, otherwise the StudentPairs are different
     */
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