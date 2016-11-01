import java.util.Scanner;

public class BankSim {
	private double totalDuration;
	private double avgDialIn;
	private double avgConTime;
	private double clock;
	private int modemCount;
	private int waitQueue;
	private int dial_ins;
	private int hang_ups;
	
	private static int DIAL_IN = 1;
	private static int HANG_UP = 0;
	
	private double currentTime;
	double lambda = avgDialIn;
	Poisson gen = new Poisson (1/lambda);
	
	public void main(String[] args){
		Scanner Keyboard = new Scanner(System.in);
		
		System.out.println("Enter the length of time for the simulation: ");
		totalDuration = Keyboard.nextDouble();
		System.out.println("Enter the average time between dial-in attempts: ");
		avgDialIn = Keyboard.nextDouble();
		System.out.println("Enter the average connection time: ");
		avgConTime = Keyboard.nextDouble();
		System.out.println("Enter the number of modems used in the bank: ");
		modemCount = Keyboard.nextInt();
		System.out.println("Finally, enter the size of the waiting queue: ");
		waitQueue = Keyboard.nextInt();
	}
	
	public void runSim(){
		
		PriorityQ<Event> events = new PriorityQ<Event>();
		RegularQ<User> users = new RegularQ<User>();
		clock = gen.nextInt();
		Event firstCall = new Event(clock, DIAL_IN, gen.nextInt());
		
		while(clock < totalDuration){
			Event newEvent = events.peek();
			
			if(newEvent.getTime() == DIAL_IN){
				dial_ins++;
				dial_in(firstCall, events, users);
			}
			else{
				hang_up(firstCall,events, users);
			}
			clock = events.peek().getTime();
		}
		
	}
	public void dial_in(Event e, PriorityQ<Event> events, RegularQ<User> user){
		events.removeFirst();
		if(user.isEmpty()){
			Event newHangup = new Event(clock+e.getDuration(), HANG_UP);
			events.addToFront(newHangup);
			System.out.println("immediate connection @" +clock);
		}
		Event newCall = new Event(gen.nextInt()+clock, DIAL_IN, gen.nextInt());
		if(events.isEmpty()){
			events.dialIn(newCall);
		}
		else{
			if(newCall.compareTo(events.peek()) == 0){
				events.dialIn(newCall);
			}
			else{
				events.addToFront(newCall);
			}
		}
		
	}
	
	public void hang_up(Event e, PriorityQ<Event> events, RegularQ<User> user){
		events.removeFirst();
		Event newHang = new Event(clock + events.peek().getDuration(), HANG_UP);
		
		if(newHang.compareTo(events.peek()) == 0){
			events.dialIn(newHang);
		}
		else{
			events.addToFront(newHang);
		}
	}
}
