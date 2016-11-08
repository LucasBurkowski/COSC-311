import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

public class ModemSim {
	
	private static double totalDuration;
	private static double avgDialIn;
	private static double avgConTime;
	private static double clock;
	private static int modemCount;
	private static int waitQueue;
	private int dial_ins;
	private int hang_ups;
	
	private static int DIAL_IN = 1;
	private static int HANG_UP = 0;
	
	private static double totalWait;
	private static double currentTime;
	private static Modem[] modems;
	
	private static PriorityQ<Event> Events;
	private static RegularQ<Caller> Waitlist;
	
	private static double[] calcAvgWait;
	private static int counter = 0;
	
	private static double pois = 0;
	private static double lambdaCon;
	private static Poisson hangUp;
	private static Event[] remove; 
	
		
	public static void main(String[] args) throws IOException{
		Scanner Keyboard = new Scanner(System.in);
		getInput();
		FileOutputStream out = null;
		modems = new Modem[modemCount];
		Waitlist = new RegularQ();
		calcAvgWait = new double[waitQueue];
		lambdaCon = avgConTime;
		hangUp = new Poisson(1/lambdaCon);
		initModems();
		AssignEvents();
		while (true){
			runSim();
			out = new FileOutputStream("report.txt");
			String output = ("");
			out.write(output.getBytes());
			System.out.println("Simulation complete, find the output in Report.txt.");
			System.out.println("Do you wish to run another simulations, (0) for yes, (1) for no.");
			int cont = Keyboard.nextInt();
			if (cont == 0){
				getInput();
				runSim();
			}
			else{
				System.exit(0);
			}
		}
	}
	
		
	public static void getInput(){
		try{
			Scanner Keyboard = new Scanner(System.in);
			System.out.println("Enter the length of time for the simulation: ");
			totalDuration = Keyboard.nextDouble();
			System.out.println("Enter the average time between dial-in attempts: ");
			avgDialIn = Keyboard.nextDouble();
			System.out.println("Enter the average connection time: ");
			avgConTime = Keyboard.nextDouble();
			System.out.println("Enter the number of modems used in the research center: ");
			modemCount = Keyboard.nextInt();
			System.out.println("Finally, enter the size of the waiting queue: ");
			waitQueue = Keyboard.nextInt();	
		}
		catch(InputMismatchException e){
			System.out.println("Please retry entering the necessary input: ");
			getInput();
		}
	}
	
	public static void runSim(){
		while(currentTime < totalDuration){
			Event e = new Event(currentTime, 0);
			Event current;
			for (int i = 0; i < Events.size(); i++){
				current = (Event) Events.get(i);
				if(current.getTime() == currentTime){
					if (current.getType() == Event.DIAL_IN){
						Events.remove(current);
						dial_in(e);
					}
					else if (current.getType() == Event.HANG_UP){
						freeModem();
					}
				}
			}
			currentTime++;
			System.out.println(currentTime);
		}
	}
			
	
	
	public static void AssignEvents(){
		Events = new PriorityQ<Event>();
		double lambda = avgDialIn;
		Poisson gen = new Poisson (1/lambda);
		double time = 0;
		double hangTime = 0;
		for(int i = 0; i < waitQueue; i++){
			time += gen.nextInt();
			Event DialIn = new Event(time, 1);
			Events.add(DialIn);
			System.out.println(time);
		}
		System.out.println(Events.toString());
	}
	
	public static void dial_in(Event e){
		/*
		int allInUse = 0;
		for (int i = 0; i < modemCount; i++){
			if(modems[i].inUse()){
				allInUse++;
			}
			else{
				Caller newCaller = new Caller();
				newCaller.setDial(e.getTime());
				Waitlist.insertEnd(newCaller);
				Events.remove(e);
				modems[i].setUse(true);
				break;
			}
		}
		
		if(allInUse == modemCount){ //All modems full
			Caller newCaller = new Caller(e.getTime());
			double lambda = avgConTime;
			Poisson gen = new Poisson(1/lambda);
			double hangUpTime = newCaller.getDial() + gen.nextInt();
			Event HangUp = new Event(hangUpTime, 0);
			Events.add(HangUp);
			Waitlist.insertEnd(newCaller);// Add dial_in to waiting queue
		}
		*/
		Caller newCaller = new Caller();
		newCaller.setDial(e.getTime());
		Waitlist.insertEnd(newCaller);
		System.out.println("new caller made with " + e);
	}
	
	public static void createHangUp(Caller current){
		pois += hangUp.nextInt();
		double hangUpTime = current.getDial() + pois;
		Event HangUp = new Event(hangUpTime, 0);
		Events.add(HangUp);
	}
	
	public static void checkWaitlist(){
		int allInUse = 0;
		double time;
		
		for(int i = 0; i < modemCount; i++){
			if(!Waitlist.isEmpty()){
				if(modems[i].inUse()){
				allInUse++;
				}
				else{
					Caller current; 
					if (Waitlist.getFront() == null){
						current = Waitlist.getRear();
						time = currentTime - current.getDial();
						Waitlist.Next();
						findAvgModemBusy(time);
						modems[i].setUse(true);
					}
					else{
						current = Waitlist.getFront();
						time = currentTime - current.getDial();
						Waitlist.Next();
						findAvgModemBusy(time);
						modems[i].setUse(true);
					}
					
				}
			}
		}
	}
	
	public static void occupyModem(){//finds the first open modem and sets it to in use
		for (int i = 0; i < modemCount; i++){
			if (!modems[i].inUse()){
				modems[i].setUse(true);
				break;
			}
		}
	}
	
	
	public static boolean modemFree(){
		int numInUse = 0;
		for(int i = 0; i < modemCount; i++){
			if(modems[i].inUse() == true){
				numInUse++;
			}
		}
		if(numInUse == modemCount){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static void freeModem(){
		System.out.println("freeing modem");
		for(int i = 0; i < modemCount; i++){
			if(modems[i].inUse()){
				modems[i].setUse(false);
				break;
			}
		}
	}
	
	public static void initModems(){
		for(int i = 0; i < modemCount; i++){
			modems[i] = new Modem();
			modems[i].setUse(false);
		}
	}
	
	public static void findAvgModemBusy(double time){
		calcAvgWait[counter] = time;
		counter++;
		System.out.println(counter + "hey");
	}
	
	public static double calcAvgModem(){
		double sum = 0;
		for(int i = 0; i < calcAvgWait.length; i++){
			sum += calcAvgWait[i];
			System.out.println(sum + "sum");
		}
		return sum/calcAvgWait.length;
	}
}
