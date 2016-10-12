import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentPrinter {
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
				rec.readFromFile(students);
				System.out.println(rec);
			}	
			Menu.listMenu();
		}
		catch(EOFException e){
			Menu.listMenu();
		}
	}
	
	public static void printSecondList(RandomAccessFile students, Student rec) throws IOException{
		try{
			students.seek(5 * Student.SIZE); //We want to display the list of students past the first 5.
		}
		catch(NullPointerException e){
			System.out.println("There currently is no list of students, please create one!");
			Menu.displayMenu();
		}
		
		try{
			while (true){
				rec.readFromFile(students);
				System.out.println(rec);
			}	
		}
		catch(EOFException e){
			Menu.listMenu();
		}
	}
	
	public static void printRecord(SingleLinkedList<StudentPair> studentLinked) throws IOException{
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
	}
	
	private static void printFromIndex(SingleLinkedList<StudentPair> studentLinked){
    	Scanner input = new Scanner(System.in);
    	int sumFound = 0;
    	
		System.out.println("Enter the starting ID of the index to display: ");
		int user = input.nextInt();
		StudentPair Current = StudentHandler.searchIndex(studentLinked, user);
    	if (Current != null){
    		System.out.println(Current);
    		for(int j = Current.getAddress(); j < studentLinked.size(); j++){
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
