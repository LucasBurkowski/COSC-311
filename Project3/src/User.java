
public class User {
	private double dial;
	private double hang;
	private double callTime;
	
	public User(double dial){
		this.dial = dial;
	}
	
	public void hang(double hang){
		this.hang = hang;
	}
	
	public double getDial(){
		return dial;
	}
	
	public double getHang(){
		return hang;
	}
	
	public double getCallTime(){
		return callTime;
	}
	
	public void setCallTime(User next){
		callTime = next.getHang() - getDial();
		if(callTime < 0){
			callTime = 0;
		}		
	}
	
	public void setCallTime(double time){
		callTime = time-getDial();
	}
}
