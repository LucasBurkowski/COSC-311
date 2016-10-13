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
     * @param studentLinked - The SingleLinkedList<StudentPair> to print
     */
	public static void printRecord(SingleLinkedList<StudentPair> studentLinked) throws IOException{
		Main.checkRandom();
		Main.checkIndex();
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to print the full list (1), or the list from an index (2)");
		int user = input.nextInt();
		switch (user){
			case 1: System.out.println(studentLinked.toString());;
					break;
			case 2: printFromIndex(studentLinked);
					break;
			default: System.out.println();
					System.out.println("Please enter a valid selection! ");
					printRecord(studentLinked);
					break;
		}
		Menu.displayMenu();
		input.close();
	}
	
	/**
     * Print the StudentPair at a given index
     * @param studentLinked - The SingleLinkedList<StudentPair> to search for the index
     */
	private static void printFromIndex(SingleLinkedList<StudentPair> studentLinked){
    	@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
    	@SuppressWarnings("unused")
		int sumFound = 0;
    	
		System.out.println("Enter the starting ID of the index to display: ");
		int user = input.nextInt();
		StudentPair Current = Main.searchIndex(studentLinked, user);
    	if (Current != null){
    		System.out.println(Current);
    		for(int j = Current.getAddress() + 1; j < studentLinked.size(); j++){
    			Current = studentLinked.get(j);
    			System.out.println(Current.toString());
    			sumFound++;
    		}
    	}
    	else{
    		System.out.println("The ID you entered was not found.");
    	}
    }

}
