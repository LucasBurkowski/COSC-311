import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
	
	public static void printRecord(RandomAccessFile students, Student rec, int pos) throws IOException{
		try{
			students.seek(pos * Student.SIZE);
			rec.readFromFile(students);
			System.out.println(rec);
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.retrieveRecord();
		}
		Menu.displayMenu();
	}

}
