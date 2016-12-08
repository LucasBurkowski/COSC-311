
public class HashTable <E, F> {
	
	int TABLE_SIZE = 37;
	BinarySearch table;
	
	HashTable(){
		table = new BinarySearch();
	}
	
	public Node getKey(E key){
		int hash = hashKey(key);
		return table.searchKey(table.root, hash);
	}
	
	public void insert(E key, F value){
		int hash = hashKey(key);
		table.insert((Comparable) hash, (Comparable) value);
	}
	
	public void printValues(int start, int end){
		try{
			Object Pair = table.searchKey(table.root, start).data;
			System.out.println(Pair.toString());
			if(start < end){
				start++;
				printValues(start, end);
			}
		}
		catch(NullPointerException e){
			if(start < end){
				start++;
				printValues(start, end);
			}
		}
	}
	
	public void remove(int key){
		table.removeKey(table.root, key);
	}
	
	private int hashKey(E key){
		return (((int)key * (int)key) >> 10) % TABLE_SIZE;
	}
}
