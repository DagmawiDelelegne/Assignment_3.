

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javafx.scene.Node;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head;
	protected Node tail;
	protected T data; 
	protected int size;
	
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Adds an entry to the end then returns a modified 
	 * object of BasicDoubleLinkedList<T>
	 * 
	 * @param data
	 * @return BasicDoubleLinkedList<T>
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if(head == null && tail == null) {
			tail = new Node(data, null,null);
			head = tail;
		}
		else {
			Node newTail = new Node(data,tail,null);
			tail.setNext(newTail);
			tail = newTail;
		}
		size++;
		return this;
	}
	
	/**
	 * Adds an entry to the front then returns a modified 
	 * object of BasicDoubleLinkedList<T>
	 * 
	 * @param data
	 * @return BasicDoubleLinkedList<T>
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if(head == null && tail == null) {
			head = new Node(data, null,null);
			tail = head;
		}
		else {
			Node newHead = new Node(data,null,head);
			head.setPrev(newHead);
			head = newHead;
		}
		size++;
		return this;
	}
	
	/**
	 * 
	 * @return the data in the head node.
	 */
	public T getFirst() {
		return head.data;
	}
	/**
	 * 
	 * @return the data in the tail node.
	 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * @return the size of the list.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * an inner class Node used as the two nodes for each data pointing to the next
	 * node and the previous one.
	 * @author Dagmawi
	 *
	 */
	protected class Node{
		private T data;
		private Node prev, next = null;
		
		public Node(T data,Node prev, Node next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		public boolean equals() {
			return false;
		}
		public T getData() {
			return data;
		}
		public Node getPrev() {
			return prev;
		}
		public Node getNext() {
			return next;
		}
		public void setPrev(Node node) {
			this.prev = node;
		}
		public void setNext(Node node) {
			this.next = node;
		}
		
	}
	
	/**
	 * Returns an iterator for this Basic double linked list.
	 * @return A ListIterator for this Basic double linked list.
	 */
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	
	/**
	 * an iterator for BasicDoubleLinkedList
	 * @author Dagmawi
	 *
	 */
	public class DoubleLinkedListIterator implements ListIterator<T> {
	    private Node pointer, pointer2;
	    private boolean hasNext;
	    private boolean hasPrevious;
	    
	   
	    public DoubleLinkedListIterator() {
	        pointer = head; 
	        pointer2 = null;
	        hasNext = true;
	        hasPrevious = false;
	    }
	    /**
	     * @return boolean if there is an element to be retrieved.
	     */
	    @Override
	    public boolean hasNext() {
	        return hasNext;
	    }
	    
	    /**
	     * Iterates to the next element and returns the 
	     * data of the element iterated past.
	     * @return temp
	     */
	    @Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }

	        T temp = pointer.getData();
	        pointer2 = pointer;
	        pointer = pointer.getNext();

	        hasPrevious = true;
	        hasNext = pointer != null;

	        return temp;
	    }
	    
	    /**
	     * checks if there is a previous entry
	     * @return boolean
	     */
	    @Override
	    public boolean hasPrevious() {
	        return hasPrevious;
	    }
	    /**
	     * @return previous entry
	     */
	    @Override
	    public T previous() {
	        if (!hasPrevious()) {
	            throw new NoSuchElementException();
	        }

	        T temp = pointer2.getData();
	        pointer = pointer2;
	        pointer2 = pointer2.getPrev();

	        hasNext = true;
	        hasPrevious = pointer2 != null;

	        return temp;
	    }

	    @Override
	    public int nextIndex() {
	        throw new UnsupportedOperationException();
	    }

	    @Override
	    public int previousIndex() {
	        throw new UnsupportedOperationException();
	    }

	    @Override
	    public void remove() {
	        throw new UnsupportedOperationException();
	    }

	    @Override
	    public void set(T e) {
	        throw new UnsupportedOperationException();
	    }

	    @Override
	    public void add(T e) {
	        throw new UnsupportedOperationException();
	    }
	}

	/**
	 * removes targetedData from the list.
	 * @param targetData
	 * @param comparator
	 * @return modified BasicDoubleLinkedList<T>
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
	    if (size == 0) {
	        return this;
	    }

	    
	    if (comparator.compare(targetData, head.getData()) == 0) {
	        head = head.getNext();
	        if (head != null) {
	            head.setPrev(null);
	        } else {
	            // The list is now empty
	            tail = null;
	        }
	        size--;
	        return this;
	    }

	  
	    if (comparator.compare(targetData, tail.getData()) == 0) {
	        tail = tail.getPrev();
	        tail.setNext(null);
	        size--;
	        return this;
	    }

	   
	    Node current = head.getNext();
	    while (current.getNext() != null) {
	        if (comparator.compare(targetData, current.getData()) == 0) {
	            current.getPrev().setNext(current.getNext());
	            current.getNext().setPrev(current.getPrev());
	            size--;
	            return this;
	        }
	        current = current.getNext();
	    }

	    return this; 
	}
	/**
	 * returns the first element.
	 * @return temp
	 */
	public T retrieveFirstElement() {
		
		if(head == null) {
			return null;
		}
		T temp = head.getData();
		if(size == 1) {
			head=null;
			tail=null;
			size--;
			return temp;
		}
		else {
		head = head.getNext();
		head.setPrev(null);
		size--;
		return temp;
		}
	}
 	/**
 	 * returns the last element.
 	 * @return temp
 	 */
	public T retrieveLastElement() {
		if(tail == null) {
			return null;
		}
		T temp = tail.getData();
		if(size == 1) {
			head=null;
			tail=null;
			size--;
			return temp;
		}
		else {
		tail = tail.getPrev();
		tail.setNext(null);
		size--;
		return temp;
		}
	}
	/**
	 * an array list of the data.
	 * @return arrayList
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> arrayList = new ArrayList<>();
		DoubleLinkedListIterator iterator = new DoubleLinkedListIterator();
		    
		while (iterator.hasNext()) {
		        arrayList.add(iterator.next());
		    }
		    
		return arrayList;
			}
}
