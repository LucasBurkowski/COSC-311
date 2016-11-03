
public class Modem {
	public boolean inUse;
	
	public Modem(){
		inUse = false;
	}
	
	public Modem(boolean inUse){
		this.inUse = inUse;
	}
	
	public boolean inUse(){
		return inUse;
	}
	
	public void setUse(boolean inUse){
		this.inUse = inUse;
	}
}
