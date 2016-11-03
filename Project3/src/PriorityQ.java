
public class PriorityQ<E> extends SingleLinkedList{
	
	public int size;
	public int LENGTH = 20;
	
	public PriorityQ(){
		size = 0;
	}
	
	public int length(){
		return size;
	}
	
	boolean isFull(){
		return size>LENGTH;
	}
	
	boolean isEmpty(){
		if (head == null){
			return true;
		}
		else{
			return false;
		}
	}
	
}
