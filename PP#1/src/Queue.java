/*
 * Lucas Burkowski
 * COSC 311
 * PP1 02/27
 * WINTER 2017
 */
public class Queue<E>{
    
    public LinkedList<E> back; 
    private static int LIMIT = 100;
    public int size; 
    
    public Queue(){
        back=null;
        size=0;
    }

    public void arrive(E element){
    	LinkedList<E> newLink;
        newLink = new LinkedList<E>(element,back); 
        if(empty()){
            newLink.setLink(newLink);
        }
        else{
           newLink.setLink(back.getLink());
           back.setLink(newLink); 
        }
        back=newLink; 
        size++; 
    }
    
    E leave(){
    	LinkedList<E> firstLink = back.getLink();
        if(firstLink==back){
           back = null;   
        }
        else{
           back.setLink(firstLink.getLink());
        }
        size--;
        return firstLink.getElement();
    }
    
    E peek(){
    	LinkedList<E> firstLink = back.getLink();
        return firstLink.getElement();
    }

    int length(){
    	return size;
    }
    
    boolean empty(){
    	return back==null;
    }

    public E getLink(int x){
    	LinkedList<E> first = back.getLink();
        for(int i=0; i<x; i++)
        {
            first=first.getLink();
        }
        return first.getElement();
    }

}
