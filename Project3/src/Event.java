
public class Event implements Comparable {
	private double time;
	private int type;
	private double duration;
	
	private static int DIAL_IN = 1;
	private static int HANG_UP = 0;
	
	
	public Event(double time, int type){
		this.time = time;
		this.type = type;
		duration = 0;
	}
	
	public Event(double time, int type, double duration){
		this.time = time;
		this.type = type;
		this.duration = duration;
	}
	
	public double getTime(){
		return time;
	}
	
	public int getType(){
		return type;
	}
	
	public double getDuration(){
		return duration;
	}
	
	public void setConnectionTime(double time){
		duration = time - this.time;
	}
	
	public void setTime(double time){
		this.time = time;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
