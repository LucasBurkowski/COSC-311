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
				rec.readFromFile(students);
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
		if (user == 1){
			System.out.println(studentLinked.toString());
		}
		if (user == 2){
			System.out.println("Enter the index number to start from: ");
			user = input.nextInt();
			printFromIndex(studentLinked, user);
		}
		else{
			System.out.println("Enter either 1 or 2:");
			printRecord(studentLinked);
		}
		Menu.displayMenu();
	}
	
	public static void printFromIndex(SingleLinkedList<StudentPair> studentLinked, int user){
    	
    	for(int i = 0; i < studentLinked.size(); i++){
			StudentPair Current = studentLinked.get(i);
    		if (user == Current.getKey()){
    			for(int j = 0; j < studentLinked.size() - i; j++){
    				Current = studentLinked.get(j);
    				System.out.println(Current.toString());
    				//System.out.println(studentLinked.get(i).getAddress() + " " + studentLinked.get(i).getKey());
    			}
    		}
    	}
    }

}
