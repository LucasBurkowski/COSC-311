import java.util.Collection;

public class recursiveZip {
	static LinkedList<Integer> LinkedList1 = new LinkedList<Integer>(){{add(1); add(2); add(3);}}; 
	static LinkedList<Integer> LinkedList2 = new LinkedList<Integer>(){{add(11); add(21); add(31);}};
	int[] list2 = {11,21,31};
	public static void main(String[] Args){
		System.out.println("First list: "+LinkedList1.toString());
		System.out.println("Second list: "+LinkedList2.toString());
		System.out.println("Zipped list: " +merge(LinkedList1, LinkedList2));
	}
	
	public static LinkedList<Integer> merge(LinkedList<Integer> first, LinkedList<Integer> second){
		LinkedList<Integer> temp = new LinkedList<Integer>();
		if(first == null && second == null){
			return null;
		}
		if(first == null){
			return second;
		}
		if(second == null){
			return first;
		}
		else{
			first.head = first.head.next;
			second.head = second.head.next;
			while(first.head != null && second.head != null){
				temp.add(first.head.data);
				first.head = first.head.next;
				temp.add(second.head.data);
				second.head = second.head.next;
			}
			return temp;
		}
	}
}

class LinkedList<E>{
	Node<E> head;
	
	public LinkedList(){
		head = new Node<E>(null);
	}
	
	public void add(E data){
		if (head == null){
			head = new Node(data);
		}else{
			Node<E> newNode = head;
			while(newNode.next!=null){
				newNode = newNode.next;
			}
			newNode.next = new Node(data);
		}
	}
	
	public void fill(E[] array){
		head = new Node<E>(array[0]);
		for(int i = 0; i < array.length; i++){
			add(array[i]);
		}
	}
	
	public String toString(){
		StringBuilder output = new StringBuilder();
		Node temp = head.next;
		output.append("[ ");
		while(temp != null){
			output.append(temp.data + " ");
			temp = temp.next;
		}
		output.append("]");
		return output.toString();
	}
}

class Node<E>{
	Node<E> next;
	E data;
	
	public Node(E value){
		next = null;
		data = value;
	}
}

