/*
 * Lucas Burkowski
 * COSC 311
 * PP1 02/27
 * WINTER 2017
 */
import java.util.Random;

public class PP1 {
	
	static int totalTicks = 50;
	static double mean = 0.2;
	static int finalCost;
	static int finalBenefit;
	static Queue<Customer> customers = new Queue<Customer>();
	static CSVWriter outFile = new CSVWriter("OneServerSim");
	static CSVWriter outFile2 = new CSVWriter("TwoServerSim");
	
	public static void main(String[] Args){
		oneServer();
		twoServer();
	}
	
	public static void oneServer(){
		finalCost = 0;
		finalBenefit = 0;
		int totalWait = 0;
		int totalServed = 0;
		outFile.initFile();
		
		Server serv = new Server();
		for (int i = 0; i < totalTicks; i++){
			totalServed += removeServed(serv);
			totalWait += updateWait(serv);
			removeDead();
			Arrival();
			fillServer(serv);
			finalCost += 3;
			outFile.addToFile(i, customers.size, totalServed, totalWait, finalBenefit, finalCost);
		}
		System.out.println("Final Cost: " +finalCost);
		System.out.println("Final Benefit: " +finalBenefit);
		outFile.writeFile();
		
	}
	
	public static void twoServer(){
		finalCost = 0;
		finalBenefit = 0;
		int totalWait = 0;
		int totalServed = 0;
		outFile2.initFile();
		
		Server serv = new Server();
		Server serv2 = new Server();
		for (int i = 0; i < totalTicks; i++){
			totalServed += removeServed(serv) + removeServed(serv2);
			totalWait += updateWait(serv) + updateWait(serv2);
			removeDead();
			Arrival();
			fillServer(serv);
			fillServer(serv2);
			finalCost += 3;
			finalCost += 3;
			outFile2.addToFile(i, customers.size, totalServed, totalWait, finalBenefit, finalCost);
		}
		System.out.println("Final Cost: " +finalCost);
		System.out.println("Final Benefit: " +finalBenefit);
		outFile2.writeFile();
	}
	
	public static int numArrivals(){
		return getPoissonRandom(mean);
	}
	
	public static void Arrival(){
		for(int i = 0; i < numArrivals(); i++){
			customers.arrive(new Customer());
		}
	}
	
	public static int updateWait(Server currentServer){
		int accruedTime = 0;
		for(int i = 0; i < customers.size; i++){
			customers.getLink(i).setWait(1);
			accruedTime++;
		}
		currentServer.currentServiceTime -= 1;
		return accruedTime;
	}
	
	public static int removeServed(Server currentServer){
		if (customers.empty() == false && customers.peek().isAlive == true && customers.peek().served == true){
			customers.leave();
			finalBenefit++;
			return 1 + removeServed(currentServer);
		}
		if (currentServer.occupied == true && currentServer.currentServiceTime <= 0 && customers.empty() == false){
			customers.leave();
			currentServer.currentServiceTime = 0;
			currentServer.occupied = false;
			finalBenefit++;
			return 1;
		}
		return 0;
	}
	
	public static void removeDead(){
		if(customers.empty() == false && customers.peek().isAlive == false){
			customers.leave();
			finalCost += 10;
			removeDead();
		}
	}
	
    public static void fillServer(Server currentServer){
    	Random r = new Random();
    	if (currentServer.occupied == false){
    		currentServer.occupied = true;
    		currentServer.currentServiceTime = r.nextInt(6 - 2 + 1) + 2;
    	}
    }
    
    private static int getPoissonRandom(double mean) {
	    Random r = new Random();
	    double L = Math.exp(-mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}
}
