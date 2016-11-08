
public class Caller {
	private double dialIn;
	private double hangUp;
	private double duration;
	
	public Caller(){
		dialIn = 0;
	}
	
	public Caller(double time){
		dialIn = time;
	}
	
	public void Call(double time){
		hangUp = time;
	}
	
	public void setDial(double time){
		dialIn = time;
	}
	
	public void setHang(double time){
		hangUp = time;
	}
	
	public double getDial(){
		return dialIn;
	}
	
	public double getHang(){
		return hangUp;
	}
	
	public void setDuration(Caller last){
		duration = last.getHang() - getDial();
		if (duration < 0){
			duration = 0;
		}
	}
	
	public void setDuration(double time){
		duration = time - getDial();
	}
	
	public void DialInTime(double time){
		dialIn += time;
	}
	
	public double getDuration(){
		return duration;
	}
}
