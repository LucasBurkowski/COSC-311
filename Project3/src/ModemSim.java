import java.util.Scanner;
import java.util.Timer;

public class ModemSim {
	
	private static double totalDuration;
	private static double avgDialIn;
	private static double avgConTime;
	private double clock;
	private static int modemCount;
	private static int waitQueue;
	private int dial_ins;
	private int hang_ups;
	
	private static int DIAL_IN = 1;
	private static int HANG_UP = 0;
	
	private double startTime;
	private static double currentTime;
	private static Modem[] modems;
	
	private static long timer;
	private static long timerStart;
	static PriorityQ<Event> Events;
	static RegularQ<Caller> Waitlist;
		
	public static void main(String[] args){
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
		AssignEvents();
		modems = new Modem[modemCount];
		Waitlist = new RegularQ();
		initModems();
		runSim();
	}
		
	public static void runSim(){
		timerStart = System.currentTimeMillis();
		while (currentTime < totalDuration){
			for(int i = 0; i < Events.size(); i++){
				System.out.println("what");
				Event currentEvent = (Event) Events.get(i);
				if(currentEvent.getTime() == currentTime){
					System.out.println(currentTime);
					if (currentEvent.getType() == DIAL_IN){
						checkWaitlist();
						dial_in(currentEvent);
					}
					else if (currentEvent.getType() == HANG_UP){
						freeModem();
						currentEvent.setConnectionTime(currentTime); 
					}
				}
			}
			currentTime++;
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
		}
	}
	
	public static void dial_in(Event e){
		int allInUse = 0;
		for (int i = 0; i < modemCount; i++){
			if(modems[i].inUse()){
				allInUse++;
			}
			else{
				
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
	}
	
	public static void checkWaitlist(){
		int allInUse = 0;
		if(!Waitlist.isEmpty()){
			for(int i = 0; i < modemCount; i++){
				if(modems[i].inUse()){
					allInUse++;
				}
				else{
					Waitlist.Next();
					modems[i].setUse(true);
				}
			}
		}
	}
	
	public static void freeModem(){
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
	
}
