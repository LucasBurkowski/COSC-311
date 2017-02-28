/*
 * Lucas Burkowski
 * COSC 311
 * PP1 02/27
 * WINTER 2017
 */

public class Customer {
	int maxTicks = 8;
	int ticksWaited;
	int currentWait;
	boolean isAlive;
	boolean served;
	
	public Customer(){
		ticksWaited = 0;
		isAlive = true;
	}
	
	public void kill(){
		isAlive = false;
	}
	
	public void setService(boolean served){
		this.served = served;
	}
	
	public void setWait(int wait){
		this.currentWait += wait;
		if(currentWait >= 8){
			this.isAlive = false;
		}
	}
}
