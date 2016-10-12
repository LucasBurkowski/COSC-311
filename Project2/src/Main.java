/*
 * Lucas Burkowski 9/21/2016
 * 
 *  Main Class: This class handles the initial run command for the program.
 *  It contains the following classes: createFile(), retrieveRecord(),
 *  modifyRecord(), and deleteRecord(). These methods correspond with the 
 *  main menu options available when the program is run. 
 */
import java.util.*;
import java.io.*;
	

public class Main {
	public static RandomAccessFile students; 
	public static Student studentList;
	public static SingleLinkedList<StudentPair> studentIndex;
	public static int  Address;
	
	public static void main(String[] args) throws IOException{
		Menu.displayMenu();
	}
	
	public static void createFile() throws IOException{
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a name for the Random Access File: ");
		String raFile = input.nextLine(); //setting user keyboard input to the name for the RandomAccessFile
		students = new RandomAccessFile(raFile,"rw"); //initialization of the students RandomAccessFile
		System.out.println();
		
		
		System.out.println("Please enter the name of the file you wish to load: ");
		String fileName = input.nextLine(); //user setting the file name for our list of students
		
		try{
			studentList = new Student();
			Scanner studentInfo = new Scanner(new FileInputStream(fileName));
			while(studentInfo.hasNext()){
				studentList.readFromTextFile(studentInfo); //read the info on the text file 
				studentList.writeToFile(students);//write the text file info into the RandomAccessFile
			}
			StudentPrinter.printShortList(students, studentList);//print first 5 students and their info 
		}
		catch(FileNotFoundException e){//if text file doesn't exist, ask for new input
			System.out.println(fileName +" does not exist, please make sure you entered the correct name!");
			createFile();//recursive call to ask for new input
		}
		input.close();
	}
	
	public static void createIndex() throws IOException{
		Address = 0;
		studentIndex = new SingleLinkedList<StudentPair>();
		//Scanner input = new Scanner(System.in);
		//StudentIndex studentsIndex = new StudentIndex();
		//System.out.println("Enter a name for the Random Access File: ");
		//String raFile = input.nextLine(); //setting user keyboard input to the name for the RandomAccessFile
		
		try{
			students.seek(0);
			while(true){
				StudentPair Current;
				Student.readFromFile(students); //read the info on the text file
				Current = new StudentPair(Student.getID(), Address);	
				studentIndex.add(Current);
				Address++;
			}
			
		}
		catch(EOFException e){//if text file doesn't exist, ask for new input
			System.out.println("Index Created!");
			Menu.displayMenu();
		} 
		//input.close();
	}
	
	public static void retrieveRecord() throws IOException{
		System.out.println("Enter a student's ID to retrieve their record.");
		Scanner input = new Scanner(System.in);//get record number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			retrieveRecord();//recursive call to ask for valid input
		}
		else{//set the position of the RandomAccessFile to the record number * the byte length of the record
			int pos = input.nextInt();//subtract one because records start 1 and not 0
			StudentPair Current = StudentHandler.searchIndex(studentIndex, pos);
			try{
				students.seek(Current.getAddress() * Student.SIZE);
				Student.readFromFile(students);
				System.out.println(studentList);
			}
			catch(NullPointerException e){
				System.out.println("The current record doesn't exist, please check your input and try again!");
				Main.retrieveRecord();
			}
		}
		Menu.displayMenu();
		input.close();
	}
	
	//this method is used when the user wants modify a record
	public static void modifyRecord() throws IOException{
		System.out.println("Enter a record number to modify it.");
		Scanner input = new Scanner(System.in);//get record number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			modifyRecord();//recursive call to ask for valid input
		}
		else{
			int pos = input.nextInt() - 1; //subtract one because records start 1 and not 0
			StudentHandler.modifyRecord(students, studentList, pos);
		}
		input.close();
	}
	
	public static void newRecord() throws IOException{
		Scanner input = new Scanner(System.in);
		StudentPair newStudent;
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
			studentList.set(firstname, lastname, ID, GPA);
			studentList.writeToFile(students);
			System.out.println(studentList);
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.deleteStudent();
		}
		Student.readFromFile(students);
		newStudent = new StudentPair(Student.getID(), Address);
		studentIndex.add(newStudent);
		
		Menu.displayMenu();
		input.close();
	}	
	
	//this method is used when the user wants to delete a record
	public static void deleteStudent() throws IOException{
		StudentPair removeStudent;
		System.out.println("Enter a student ID number to delete it.");
		Scanner input = new Scanner(System.in);//get ID number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			deleteStudent();//recursive call to ask for valid input
		}
		else{
			int pos = input.nextInt() - 1;//subtract one because records start 1 and not 0
			removeStudent = StudentHandler.searchIndex(studentIndex, pos);
			if (removeStudent != null){
				try{
					String delete = "DELETED";
					delete = Student.pad(delete, 20);
					students.seek(removeStudent.getAddress() * Student.SIZE);
					studentList = new Student();
					studentList.set(delete, delete, 0, 0);
					studentList.writeToFile(students);
					studentIndex.remove(Address);
					System.out.println(removeStudent.getAddress());
				}
				catch(NullPointerException e){
					System.out.println("The current record doesn't exist, please check your input and try again!");
					Main.deleteStudent();
				}
			}
			else{
				System.out.println("No student was found with that ID.");
			}
			Menu.displayMenu();
		}
		input.close();
	}
}
