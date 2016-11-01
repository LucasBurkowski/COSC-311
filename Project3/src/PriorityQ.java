
public class PriorityQ <E extends Comparable>{
	public class Events<E>{
		protected E element;
		protected Events<E> link;
		
		public Events(E elem, Events<E> link){
			this.element = elem;
			this.link = link;
		}
		
		public E getElement(){
			return element;
		}
		
		public Events<E> getLink(){
			return link;
		}
		
		public void setLink(Events<E> next){
			link = next;
		}
	}
	
	public int size;
	public int LENGTH = 10;
	public Events<E> events;
	
	public PriorityQ(){
		size = 0;
		events = null;
	}
	
	public void dialIn(E elem){
		Events<E> next = new Events(elem, events);
		if(isEmpty()){
			next.setLink(next);
		}
		else if(size >= LENGTH){
			
		}
		else{
			next.setLink(events.getLink());
			events.setLink(next);
		}
		events = next;
		size++;
	}
	
	public void addToFront(E elem){
		Events<E> next = new Events(elem, events);
		if(isEmpty()){
			next.setLink(next);
		}
		else if(size >= LENGTH){
			
		}
		else{
			next.setLink(events.getLink());
			events.setLink(next);
		}
		events = next.getLink();
		size++; 
	}
	
	public E removeFirst(){
		if(isEmpty()){
			return null;
		}
		else{
			Events<E> newNode = events.getLink();
			if(newNode == events){
				events = null;
			}
			else{
				events.setLink(newNode.getLink());
			}
			size--;
			return newNode.getElement();
		}
	}
	
	public E getEvent(int x){
		Events<E> first = events.getLink();
		for(int i = 0; i < x; i++){
			first = first.getLink();
		}
		return first.getElement();
	}
	
	public E peek(){
		if(!isEmpty()){
			Events<E> first = events.getLink();
			return first.getElement();
		}
		else{
			return null;
		}
	}
	
	public int length(){
		return size;
	}
	
	boolean isFull(){
		return size>LENGTH;
	}
	
	boolean isEmpty(){
		return events == null;
	}
	
	
	
	
}
