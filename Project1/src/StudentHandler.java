/*
 * Lucas Burkowski 9/21/2016
 * 
 *  Menu Class: This class prints and handles menu selection from the main
 *  menu and the record menu("listMenu").
 *  It contains the following classes: deleteRecord(), modifyRecord(), and newRecord().
 *  These classes display menu options and read user input to call methods in 
 *  various classes to perform the desired actions.
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class StudentHandler {
	
	public static void deleteRecord(RandomAccessFile students, Student rec, int pos) throws IOException{
		try{
			String delete = "DELETED";
			delete = Student.pad(delete, 20);
			students.seek(pos * Student.SIZE);
			rec = new Student();
			rec.set(delete, delete, 0, 0);
			rec.writeToFile(students);
			System.out.println(rec);
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.deleteStudent();
		}
		Menu.displayMenu();
	}
	
	/**
	 * modify a record in the list of students 
	 * @param students 
	 * @param size the length of the resulting string
	 * @return a string of length size
	 */
	public static void modifyRecord(RandomAccessFile students, Student rec, int pos) throws IOException{
		Scanner input = new Scanner(System.in);
		String firstname;
		String lastname;
		int ID;
		double GPA;
		System.out.println("Enter the first name for the record:");
		firstname = input.nextLine();
		System.out.println("Enter the last name for the record:");
		lastname = input.nextLine();
		System.out.println("Enter the ID for the record:");
		ID = input.nextInt();
		System.out.println("Enter the GPA for the record:");
		GPA = input.nextDouble();
		
		firstname = Student.pad(firstname, 20);
		lastname = Student.pad(lastname, 20);
		try{
			students.seek(pos * Student.SIZE);
			rec = new Student();
			rec.set(firstname, lastname, ID, GPA);
			rec.writeToFile(students);
			System.out.println(rec);
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.deleteStudent();
		}
		Menu.displayMenu();
		input.close();
	}
	
	public static void newRecord(RandomAccessFile students, Student rec) throws IOException{
		Scanner input = new Scanner(System.in);
		String firstname;
		String lastname;
		int ID;
		double GPA;
		System.out.println("Enter the first name for the record:");
		firstname = input.nextLine();
		System.out.println("Enter the last name for the record:");
		lastname = input.nextLine();
		System.out.println("Enter the ID for the record:");
		ID = input.nextInt();
		System.out.println("Enter the GPA for the record:");
		GPA = input.nextDouble();
		
		firstname = Student.pad(firstname, 20);
		lastname = Student.pad(lastname, 20);
		try{
			students.seek(students.length());
			rec = new Student();
			rec.set(firstname, lastname, ID, GPA);
			rec.writeToFile(students);
			System.out.println(rec);
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.deleteStudent();
		}
		Menu.displayMenu();
		input.close();
	}
	
}
