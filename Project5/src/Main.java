/*
 * Lucas Burkowski 9/21/2016
 * 
 *  Main Class: This class handles the initial run command for the program.
 *  It contains the following classes: createFile(), createIndex(), retrieveRecord(),
 *  mewRecord(), modifyRecord(), and deleteRecord(). These methods correspond with the 
 *  main menu options available when the program is run. 
 */
import java.util.*;
import java.io.*;
	

public class Main {
	public static RandomAccessFile students; 
	public static Student studentList;
	public static HashTable studentIndex;
	public static int  Address;
	
	public static void main(String[] args) throws IOException{
		Menu.displayMenu();
	}
	
	/*
	 *createFile() is used when the user wants to create a database.
	 *It reads a text file containing a list of student info. It reads
	 *each line of the file adding it to a random access file the user 
	 *specifies.
	 */
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
	
	/*
	 *createIndex() is used when the user wants to create an index.
	 *It reads the database and for each entry creates a new node and
	 *a StudentPair that contains an ID value and an Address value.
	 */
	public static void createIndex() throws IOException{
		Address = 0;
		studentIndex = new HashTable();
		BinarySearch tree = new BinarySearch(); 
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a name for the Random Access File: ");
		String raFile = input.nextLine(); //setting user keyboard input to the name for the RandomAccessFile
		students = new RandomAccessFile(raFile,"rw");
		try{
			students.seek(0);
			while(true){
				StudentPair Current;
				Student.readFromFile(students); //read the info on the text file
				Current = new StudentPair(Student.getID(), Address);	
				studentIndex.insert(Current.getKey(), Current);
				Address++;
			}
			
		}
		catch(EOFException e){//if text file doesn't exist, ask for new input
			System.out.println("Index Created!");
			Menu.displayMenu();
		} 
		input.close();
	}
	
	/*
	 *retrieveRecord() is used when the user wants to get a record.
	 *It first searches a linkedlist for a node with a specific StudentPair,
	 *then prints the database entry that corresponds with it.
	 */
	public static void retrieveRecord() throws IOException{
		checkRandom();
		checkIndex();
		System.out.println("Enter a student's ID to retrieve their record.");
		Scanner input = new Scanner(System.in);//get record number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			retrieveRecord();//recursive call to ask for valid input
		}
		else{//set the position of the RandomAccessFile to the record number * the byte length of the record
			int pos = input.nextInt();//subtract one because records start 1 and not 0
			StudentPair temp = new StudentPair(pos, 0);
			try{
				Node next = studentIndex.getKey(temp.getKey());
				StudentPair Current = (StudentPair) next.data;
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
		
	/*
	 *deleteStudent() is used when the user wants to delete a record.
	 *It first searches a linkedlist for a node with a specific StudentPair,
	 *then deletes the database entry that corresponds with it.
	 */
	public static void newRecord() throws IOException{
		checkRandom();
		checkIndex();
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
			newStudent = new StudentPair(ID, Address);
			studentIndex.insert(ID, newStudent);
			Address++;
			students.seek(students.length());
			studentList.set(firstname, lastname, ID, GPA);
			studentList.writeToFile(students);
			System.out.println(studentList);
			
		}
		catch(NullPointerException e){
			System.out.println("The current record doesn't exist, please check your input and try again!");
			Main.deleteStudent();
		}
		
		Menu.displayMenu();
		input.close();
	}
	
	/*
	 *deleteStudent() is used when the user wants to delete a record.
	 *It first searches a linkedlist for a node with a specific StudentPair,
	 *then deletes the database entry that corresponds with it.
	 */
	public static void deleteStudent() throws IOException{
		checkRandom();
		checkIndex();
		StudentPair removeStudent;
		System.out.println("Enter a student ID number to delete it.");
		Scanner input = new Scanner(System.in);//get ID number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			deleteStudent();//recursive call to ask for valid input
		}
		else{
			int pos = input.nextInt();//subtract one because records start 1 and not 0
			StudentPair temp = new StudentPair(pos, 0);
			Node next = studentIndex.getKey(temp.getKey());
			removeStudent = (StudentPair) next.data;
			if (removeStudent != null){
				try{
					String delete = "DELETED";
					delete = Student.pad(delete, 20);
					students.seek(removeStudent.getAddress() * Student.SIZE);
					studentList = new Student();
					studentList.set(delete, delete, 0, 0);
					studentList.writeToFile(students);
					studentIndex.remove(removeStudent.getKey());
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
	
	/**
	 * modify a record in the list of students 
	 */
	public static void modifyRecord() throws IOException{
		checkRandom();
		checkIndex();
		StudentPair modStudent;
		Scanner input = new Scanner(System.in);
		String firstname;
		String lastname;
		double GPA;
		System.out.println("Enter the first name for the record:");
		firstname = input.nextLine();
		System.out.println("Enter the last name for the record:");
		lastname = input.nextLine();
		System.out.println("Enter the GPA for the record:");
		GPA = input.nextDouble();
		
		System.out.println("Enter a record number to modify it.");
		//Scanner input = new Scanner(System.in);//get record number from user
		
		if (!input.hasNextInt()) {//make sure we are getting an integer from the user
			System.out.println("Please Enter a vaild number!");
			modifyRecord();//recursive call to ask for valid input
		}
		else{
			int pos = input.nextInt(); 
			StudentPair temp = new StudentPair(pos, 0);
			Node next = studentIndex.getKey(temp.getKey());
			modStudent = (StudentPair) next.data;
			//StudentHandler.modifyRecord(students, studentList, pos);
			firstname = Student.pad(firstname, 20);
			lastname = Student.pad(lastname, 20);
			if (modStudent != null){
				try{
					students.seek(modStudent.getAddress() * Student.SIZE);
					studentList = new Student();
					studentList.set(firstname, lastname, modStudent.getKey(), GPA);
					studentList.writeToFile(students);
					System.out.println(studentList);
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
			input.close();
		}
		input.close();
	}
	
	/*
	 * checkRandom() is used to make sure we have created a random access file
	 * before we run methods that require it.
	 */
	public static void checkRandom() throws IOException{
		if (students == null){
			System.out.println("You must create a file before you run this command.");
			Menu.mainMenu();
		}
	}
	
	/*
	 * checkIndex() is used to make sure we have created a SingleLinkedList<StudentPair>
	 * before we run methods that require it.
	 */
	public static void checkIndex() throws IOException{
		if(studentIndex == null){
			System.out.println("You must create an index before you run this command.");
			Menu.mainMenu();
		}
	}
}
