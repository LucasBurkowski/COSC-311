/*
 * Lucas Burkowski 9/21/2016
 * 
 *  Menu Class: This class prints and handles menu selection from the main
 *  menu and the record menu("listMenu").
 *  It contains the following classes: displayMenu(), mainMenu(), and listMenu().
 *  These classes display menu options and read user input to call methods in 
 *  various classes to perform the desired actions.
 */
import java.io.IOException;
import java.util.Scanner;

public class Menu {
	
	/*
	 * displayMenu() is used to display all the user options available in the
	 * main menu.
	 */
	public static void displayMenu() throws IOException{ 
		System.out.println("Please select an option: "+ "\n"+
						   "Create File (1) | "+
						   "Display File (2) | "+
						   "Build Index (3) | "+
						   "Display Index (4) |"+
						   "Retrieve a Record (5) | "+
						   "Modify a Record (6) | "+ 
						   "Add a New Record (7) | "+
						   "Delete a Record (8) | "+
						   "Exit (9)");
		mainMenu();
	}
	
	/*
	 * mainMenu() handles the user input from the keyboard and calls the method
	 * that corresponds to that input.
	 */
	public static void mainMenu() throws IOException{
		Scanner input = new Scanner(System.in);
		int Selection = input.nextInt();
		switch (Selection){
			case 1: Main.createFile();
					  break;
			case 2: StudentPrinter.printShortList(Main.students, Main.studentList);
					  break;
			case 3: Main.createIndex();
					  break;
			case 4: StudentPrinter.printRecord(Main.studentIndex);
					  break;
			case 5: Main.retrieveRecord();
					  break;
			case 6: Main.modifyRecord();
					  break;
			case 7: Main.newRecord();
					  break;
			case 8: Main.deleteStudent();
					  break;
			case 9: input.close();
					  System.exit(0);
			          break;
			default: System.out.println();
					 System.out.println("Please enter a valid selection! ");
					 mainMenu();
					 break;
		}
		input.close();
	}
	
	/*
	 * listMenu() displays the options the user has for when the students are 
	 * displayed. It then takes user input and calls the matching method.
	 */
	public static void listMenu() throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to do: ");//prompt the user
		System.out.println("Return to Main Menu (M) | View Next Screen (N) | Display All Students (A)");
		System.out.println();
		String user = input.nextLine();
		switch (user){//match user input to method calls
			case "M": displayMenu();
					  break;
			case "N": StudentPrinter.printSecondList(Main.students, Main.studentList);
			          break;
			case "A": StudentPrinter.printAllStudents(Main.students, Main.studentList);
					  break;
			default: System.out.println("Please enter valid input!");
					 System.out.println();
					 listMenu();//recursive call for valid input.
					 break;
		}
		input.close();
	}
}
