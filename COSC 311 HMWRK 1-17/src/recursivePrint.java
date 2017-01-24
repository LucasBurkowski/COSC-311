/*
 * Lucas Burkowski
 * https://github.com/LucasBurkowski/COSC-311/tree/master/COSC%20311%20HMWRK%201-10
 * COSC 311
 * HW 01/17
 * WINTER 2017
 */
public class recursivePrint {
	
	public static void main(String[] args){
		int[] data = {5,4,3,1,2,5,7,8,3};
		int[] next = {-1,3,0,6,5,1,0,-1,3};
		int head = 5;
		Node[] linkedList = Node.add(data, next);
		Node.print(head, linkedList);
	}
}

class Node{
	int next;
	int data;
	
	public Node(int data, int next){
		this.next = next;
		this.data = data;
	}
	
	public static Node[] add(int[] data, int[] next){
		Node[] list = new Node[data.length];
		for(int i = 0; i < data.length; i++){
			list[i] = new Node(data[i], next[i]);
		}
		return list;
	}
	
	public static void print(int head, Node[] linkedList){
		if(linkedList == null)
			return;
		else if(head == -1){
			return;
		}else{
			System.out.print(linkedList[head].data + " ");
			head = linkedList[head].next;
			print(head,linkedList);
		}
	}
}


