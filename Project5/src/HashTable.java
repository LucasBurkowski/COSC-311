/*
 * Lucas Burkowski 9/21/2016
 * 
 *  HashTable: This class handles the hashing and storage of generic objects
 *  that will be indexed within a binary search tree.
 */
public class HashTable <E, F> {
	
	int TABLE_SIZE = 37; //Max number of elements to hash is 37
	BinarySearch table; 
	
	HashTable(){
		table = new BinarySearch();
	}
	
	public Node getKey(E key){
		int hash = hashKey(key);
		return table.searchKey(table.root, hash); //returns item from tree that has a matching key
	}
	
	public void insert(E key, F value){
		int hash = hashKey(key);
		table.insert((Comparable) hash, (Comparable) value);// inserts an object in the tree
	}
	
	public void printValues(int start, int end){
		try{
			Object Pair = table.searchKey(table.root, start).data;
			System.out.println(Pair.toString());
			if(start < end){
				start++; //increment key to next desired member
				printValues(start, end); //recursive call to find remaining elements in tree
			}
		}
		catch(NullPointerException e){
			if(start < end){
				start++; //increment key to next desired member
				printValues(start, end); //recursive call to find remaining elements in tree
			}
		}
	}
	
	public void remove(int key){
		table.removeKey(table.root, key);//delete a element from the tree based on key
	}
	
	private int hashKey(E key){
		return (((int)key * (int)key) >> 10) % TABLE_SIZE; //hash algorithm
	}
}
