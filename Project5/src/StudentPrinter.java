import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class StudentPrinter {
	
	/**
     * Print the full list SingleLinkedlist<StudentPair> of StudentPairs 
     * @param students - RandomAccessFile of Student objects
     * @param rec - Student Object
     */
	public static void printAllStudents(RandomAccessFile students, Student rec) throws IOException{
		try{
			students.seek(0);
		}
		catch(NullPointerException e){
			System.out.println("There currently is no list of students, please create one!");
			Menu.displayMenu();
		}
		
		try{
			while(true){
				Student.readFromFile(students);
				System.out.println(rec);
			}	
		}
		catch(EOFException e){
			Menu.listMenu();
		}
	}
	
	/**
     * Print the short list SingleLinkedlist<StudentPair> of StudentPairs 
     * @param students - RandomAccessFile of Student objects
     * @param rec - Student Object
     */
	public static void printShortList(RandomAccessFile students, Student rec) throws IOException{
		try{
			students.seek(0);
		}
		catch(NullPointerException e){
			System.out.println("There currently is no list of students, please create one!");
			Menu.displayMenu();
		}
		
		try{
			for (int i = 0; i < 5; i++){
				Student.readFromFile(students);
				System.out.println(rec);
			}	
			Menu.listMenu();
		}
		catch(EOFException e){
			Menu.listMenu();
		}
	}
	
	/**
     * Print the second list of Students in the RandomAccessFile
     * @param students - RandomAccessFile of Student objects
     * @param rec - Student Object
     */
	public static void printSecondList(RandomAccessFile students, Student rec) throws IOException{
		Main.checkRandom();
		try{
			students.seek(5 * Student.SIZE); //We want to display the list of students past the first 5.
		}
		catch(NullPointerException e){
			System.out.println("There currently is no list of students, please create one!");
			Menu.displayMenu();
		}
		
		try{
			while (true){
				Student.readFromFile(students);
				System.out.println(rec);
			}	
		}
		catch(EOFException e){
			Menu.listMenu();
		}
	}
	
	/**
     * Print the SingleLinkedlist<StudentPair> of StudentPairs 
     * @param studentIndex - The SingleLinkedList<StudentPair> to print
     */
	public static void printRecord(HashTable studentIndex) throws IOException{
		Main.checkRandom();
		Main.checkIndex();
		Scanner input = new Scanner(System.in);
		int start = 0;
		int end = 36;
		System.out.println("Enter the number of the the index to start from: ");
		start = input.nextInt();
		System.out.print("Enter the number of the last index to display: ");
		end = input.nextInt();
		studentIndex.printValues(start, end);
		Menu.displayMenu();
		input.close();
	}
	
	/**
     * Print the StudentPair at a given index
     * @param studentLinked - The SingleLinkedList<StudentPair> to search for the index
     */
	private static void printFromIndex(BinarySearch<StudentPair> studentLinked){
    	@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
    	@SuppressWarnings("unused")
		int sumFound = 0;
    	
		System.out.println("Enter the starting ID of the index to display: ");
		int user = input.nextInt();
		StudentPair temp = new StudentPair(user, 0);
		Node<StudentPair> Current = studentLinked.search(studentLinked.root, temp);
    	if (Current != null){
    		studentLinked.print(Current);
    	}
    	else{
    		System.out.println("The ID you entered was not found.");
    	}
    }

}
