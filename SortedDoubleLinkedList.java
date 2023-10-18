

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T> {
	
	Comparator<T> c;
	
	public SortedDoubleLinkedList(Comparator<T> comparableObject) {
			c = comparableObject;
	}
	/**
	 * This method adds an entry at the appropriate position.
	 * @param data
	 * @return modified object SortedDoubleLinkedList
	 */
	public SortedDoubleLinkedList<T> add(T data) {
	    Node newNode = new Node(data, null, null);

	    if (head == null) {
	        // The list is empty, so set head and tail to the new node.
	        head = newNode;
	        tail = newNode;
	    } else if (c.compare(data, head.getData()) <= 0) {
	        // Insert at the beginning.
	        newNode.setNext(head);
	        head.setPrev(newNode);
	        head = newNode;
	    } else {
	        Node current = head;
	        while (current.getNext() != null && c.compare(data, current.getNext().getData()) > 0) {
	            current = current.getNext();
	        }
	        // Insert the new node after 'current'.
	        newNode.setNext(current.getNext());
	        if (current.getNext() != null) {
	            current.getNext().setPrev(newNode);
	        } else {
	            tail = newNode;
	        }
	        current.setNext(newNode);
	        newNode.setPrev(current);
	    }

	    size++;
	    return this;
	}
	/**
	 * addToFront is not supported for SortedDoubleLinkedList
	 * @author Dagmawi
	 */
	public SortedDoubleLinkedList<T> addToFront(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	
	/**
	 * addToEnd is not supported for SortedDoubleLinkedList
	 * @author Dagmawi
	 */
	public SortedDoubleLinkedList<T> addToEnd(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * Returns an iterator for this sorted double linked list.
	 * @return A ListIterator for this sorted double linked list.
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
}
