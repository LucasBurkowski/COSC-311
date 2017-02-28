/*
 * Lucas Burkowski
 * COSC 311
 * PP1 02/27
 * WINTER 2017
 */
public class LinkedList<E> {
	private E element;

	private LinkedList<E> link; 

	public LinkedList(E element, LinkedList<E> link) {
		this.element = element;
		this.link = link;
	} 
	
	public E getElement() {
		return element;
	} 
	
	public LinkedList<E> getLink() {
		return link;
	} 
	
	public void setLink(LinkedList<E> newLink) {
		link = newLink;
	} 
} 