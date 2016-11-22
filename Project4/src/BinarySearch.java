
class Node<E> {

        /** The data value. */
        public  E data;
        /** The link */
        Node<E> left = null;
        Node<E> right = null;
 
        /**
         * Construct a node with the given data value and link
         * @param data - The data value 
         * @param next - The link
         */
        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        /**
         * Construct a node with the given data value
         * @param data - The data value 
         */
        public Node(E data) {
            this(data, null, null);
        }
    }

public class BinarySearch<E extends Comparable<E>>{
	public Node<E> root;
	
	public BinarySearch(){
		root = null;
	}
	
	public BinarySearch(E value){
		root = new Node<E>(value);
	}
	
	public E search(E elem){
		Node<E> searched = search(root, elem);
		return (searched == null) ? null : searched.data;
	}
	
	public Node<E> search(Node<E> start, E elem){
		if(start == null){
			return null;
		}
		int comp = start.data.compareTo(elem);
		
		if(comp > 0){
			return search(start.right, elem);
		}
		
		else if(comp < 0){
			return search(start.right, elem);
		}
		
		else{
			return start;
		}
	}
	
	public boolean insert(E elem){
		return insert(root, elem);
	}
	
	public boolean insert(Node<E> start, E elem){
		if(start == null){
			root = new Node(elem, null, null);
			return true;
		}
		
		int comp = start.data.compareTo(elem);
		
		if(comp > 0){
			if (start.left == null){
				start.left = new Node(elem, null, null);
			}
			return insert(start.left, elem);
		}
		
		else if(comp < 0){
			if (start.right == null){
				start.right = new Node(elem, null, null);
			}
			return insert(start.right, elem);
		}
		
		else{
			return false;
		}
	}
	
	public void remove(E elem){
		root = remove(root, elem);
	}
	
	public Node<E> remove(Node<E> start, E elem){
		if(start == null){
			return null;
		}
		
		int comp = start.data.compareTo(elem);
		
		if(comp > 0){
			start.left = remove(start.left, elem);
		}
		
		else if(comp < 0){
			start.right = remove(start.right, elem);
		}
		
		else{
			if(start.left != null && start.right != null){
				Node<E> left = start.left;
				Node<E> right = start.right;
				
				start = removeBot(start.right, start);
				Node newRight = start.right;
				
				start.left = left;
				start.right = right;
				
				if (start.right.data == start.data){
					start.right = newRight;
				}
			}
			
			else if(start.left == null && start.right == null){
				start = null;
			}
			
			else if(start.left != null){
				start = start.left;
			}
			
			else{
				start = start.right;
			}
		}
		return start;
	}
	
	public Node<E> removeBot(Node<E> start, Node<E> parent){
		if(start == null){
			return null;
		}
		if(start.left == null){
			if(parent != root){
				parent.left = start.right;
			}
			return start;
		}
		return removeBot(start.left, start);
	}
	
	public void print(){
		print(root);
	}
	
	public void print(Node<E> start){
		if (start != null){
			print(start.left);
			System.out.println(start.data);
			print(start.right);
		}
	}

}
