import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Student {
	private static int LENGTH = 20;
	private static String firstName;
	private static String lastName;
	private static int ID;
	private static double GPA;
	public static int SIZE = 92;
	
	public void set(String first, String last, int id, double gpa){
		firstName = first;
		lastName = last;
		ID = id;
		GPA = gpa;
	}
	
	public static void readFromFile(RandomAccessFile in)throws IOException {
		firstName = readString(in, LENGTH);
		lastName = readString(in, LENGTH);
		ID = in.readInt();
		GPA = in.readDouble();
	}
	
	public static int getID(){
		return ID;
	}

	private static String readString(RandomAccessFile in, int size)throws IOException{
		char [] str = new char [size];
		for (int i =0; i<str.length; i++)
			str[i] = in.readChar();
		return new String(str);
	}
	
	public void writeToFile(RandomAccessFile output) throws IOException{
		writeString(output, firstName, LENGTH);
		writeString(output, lastName, LENGTH);
		output.writeInt(ID);
		output.writeDouble(GPA);
	}

	private void writeString(RandomAccessFile out, String name, int size) throws IOException {
		char[] output = new char[size];
		output = name.toCharArray();
		for(int i=0; i<output.length; i++){
			out.writeChar(output[i]);
		}
	}
	
	public void readFromTextFile(Scanner in) throws IOException {
		firstName = in.next();
		lastName = in.next();
		ID = in.nextInt();
		GPA = in.nextDouble();
		firstName = pad (firstName, LENGTH);
		lastName = pad (lastName, LENGTH);
	}
	
	/**
	 * Padding a string with trailing blank(s) 
	 * @param s the string
	 * @param size the length of the resulting string
	 * @return a string of length size
	 */
	public static String pad (String s, int size){
		for (int i = s.length(); i <size; i++)
			s += ' ';
		return s;
	}
	
	/**
	 * Create and return a string that represents a student 
	 * @return a string representing a student
	 */
	public String toString(){
		return "first name = " + firstName + "last name = " + lastName + "\tstudent ID = "+ ID +
		       "\tGPA = " + GPA;
	}

}
