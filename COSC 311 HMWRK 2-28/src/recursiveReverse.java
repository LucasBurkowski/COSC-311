/*Lucas Burkowski
 *
 * COSC 311
 * HMWRK 02/28
 * WINTER 2017
 */
public class recursiveReverse {
	static Integer[] test = {1,2,3,4,5,6};
	static LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static void main(String[] Args){
		list.fill(test);
		System.out.println(print());
		reverse(list.head);
		System.out.println(print());
	}
	
	public static void reverse(Node<Integer> current){
		if (current == null){
			return;
		}
		if (current.next == null){
			list.head = current;
		}else{
			reverse(current.next);
			Node<Integer> last = current.next;
			last.next = current;
			current.next = null;
		}
	}
	
	public static String print(){
		StringBuilder output = new StringBuilder();
		Node<Integer> current = list.head;
		while(current.next != null){
			output.append(current.data + ", ");
			current = current.next;
		}
		return output.toString();
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
}

class Node<E>{
	Node<E> next;
	E data;
	
	public Node(E value){
		next = null;
		data = value;
	}
}