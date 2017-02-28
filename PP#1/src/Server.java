/*
 * Lucas Burkowski
 * COSC 311
 * PP1 02/27
 * WINTER 2017
 */
public class Server {
	boolean occupied;
	int currentServiceTime;
	
	public Server(){
		occupied = false;
	}
	
	public void setOccupied(boolean occupied){
		this.occupied = occupied;
	}
	
	public void setServiceTime(int time){
		currentServiceTime = time;
	}
	
	public void handleCustomer(Customer customer){
		if(currentServiceTime == 0 && occupied == true){
			customer.served = true;
		}
	}
	
}
