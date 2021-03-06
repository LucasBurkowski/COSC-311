/*
 * Lucas Burkowski
 * https://github.com/LucasBurkowski/COSC-311/tree/master/COSC%20311%20HMWRK%201-12
 * COSC 311
 * HW 01/12
 * WINTER 2017
 */
public class deleteKth {
	public static int[] array = {0,2,4,6,1};
	public static LinkedList newList;
	public static int K = 2;
	
	public static void main(String[] args){
		Node current = new Node(0);
		Node nextNode;
		for(int i = 0; i < array.length; i++){
			if (newList == null){
				newList = new LinkedList(array[i]);
				current = newList.head;
			}
			else{
			nextNode = new Node(array[i]);
			newList.add(current, nextNode);
			current = current.next;
			}
		}
		current = newList.head;
		int length = newList.getLength();
		length = length - K;
		System.out.println(length);
		for(int i = 1; i < length && current != null; i++){
			current = current.next;
		}
		
		current.setNext(current.next.next);
		current = newList.head;
		System.out.println("List After Removal: ");
		newList.print();
	}
}

class LinkedList{
	Node head;
	int length = 0;
	
	public LinkedList(int value){
		head = new Node(value);
	}
	
	public void add(Node current, Node nextNode){
		current.setNext(nextNode);
	}
	
	public int getLength(){
		Node temp = head;
		while(temp.next != null){
			length++;
			temp = temp.next;
		}
		length++;
		return length;
	}
	
	public void print(){
		Node temp = head;
		while (temp.next != null){
			System.out.print(temp.data);
			temp = temp.next;
		}
		System.out.print(temp.data);
	}
}

class Node{
	Node next;
	int data;
	
	public Node(int value){
		next = null;
		data = value;
	}
	public void setNext(Node nextNode){
		next = nextNode;
	}
}


