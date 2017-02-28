import java.
public class RegularQ<E> {
	private int num = 100;
	private int front, rear;
	private int count = 0;
	private E[] Queue;
	
	public RegularQ(){
		Queue = (E[]) new Object[num];
		front = rear = count = 0;
	}
	
	public void insertEnd(E insert){
		if (QSize() == Queue.length){
			doubleQ();
		}
		Queue[rear] = insert;
		rear = (rear++) % Queue.length;
		count++;
	}
	
	public E Next(){
		E next = Queue[front];
		Queue[front] = null;
		front = (front++) % Queue.length;
		count--;
		return next;
	}
	
	public E getFront(){
		E next = Queue[front];
		return next;
	}
	
	public E getRear(){
		E last = Queue[rear];
		return last;
	}
	
	public boolean isEmpty(){
		return (count == 0);
	}
	
	public int QSize(){
		return count;
	}
	
	public void doubleQ(){
		E[] expand = (E[]) new Object[Queue.length * 2];
		
		for(int i = 0; i < count; i++){
			expand[i] = Queue[front];
			front = (front++) % Queue.length;
		}
		
		front = 0;
		rear = count;
		Queue = expand;
	}
}
