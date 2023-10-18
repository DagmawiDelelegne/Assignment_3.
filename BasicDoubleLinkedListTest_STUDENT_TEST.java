

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedListTest_STUDENT_TEST {
	BasicDoubleLinkedList<House> linkedHouse;
    HouseComparator comparator;
    
    public House h1 = new House("John", 200000.0);
    public House h2 = new House("Dagm", 2300000.0);
    public House h3 = new House("Bob", 300000.0);
    public House h4 = new House("Adam", 13200000.0);

    @Before
    public void setUp() throws Exception {
        linkedHouse = new BasicDoubleLinkedList<House>();
        linkedHouse.addToEnd(h1);
        linkedHouse.addToEnd(h2);
        comparator = new HouseComparator();
    }

    @After
    public void tearDown() throws Exception {
        linkedHouse = null;
        comparator = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedHouse.getSize());
    }

    @Test
    public void testAddToEnd() {
        assertEquals(h2, linkedHouse.getLast());
        linkedHouse.addToEnd(h3);
        assertEquals(h3, linkedHouse.getLast());
    }

    @Test
    public void testAddToFront() {
        assertEquals(h1, linkedHouse.getFirst());
        linkedHouse.addToFront(h4);
        assertEquals(h4, linkedHouse.getFirst());
    }

    @Test
    public void testGetFirst() {
        assertEquals(h1, linkedHouse.getFirst());
        linkedHouse.addToFront(h4);
        assertEquals(h4, linkedHouse.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(h2, linkedHouse.getLast());
        linkedHouse.addToEnd(h3);
        assertEquals(h3, linkedHouse.getLast());
    }

    @Test
    public void testToArrayList() {
        ArrayList<House> list;
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        list = linkedHouse.toArrayList();
        assertEquals(h4, list.get(0));
        assertEquals(h1, list.get(1));
        assertEquals(h2, list.get(2));
        assertEquals(h3, list.get(3));
    }

    @Test
    public void testIteratorSuccessfulNext() {
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        ListIterator<House> iterator = linkedHouse.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(h4, iterator.next());
        assertEquals(h1, iterator.next());
        assertEquals(h2, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(h3, iterator.next());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        ListIterator<House> iterator = linkedHouse.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(h4, iterator.next());
        assertEquals(h1, iterator.next());
        assertEquals(h2, iterator.next());
        assertEquals(h3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(h3, iterator.previous());
        assertEquals(h2, iterator.previous());
        assertEquals(h1, iterator.previous());
        assertEquals(h4, iterator.previous());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        ListIterator<House> iterator = linkedHouse.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(h4, iterator.next());
        assertEquals(h1, iterator.next());
        assertEquals(h2, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(h3, iterator.next());
        
        try {
            // no more elements in the list
            iterator.next();
            fail("Did not throw a NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Successfully threw a NoSuchElementException
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        ListIterator<House> iterator = linkedHouse.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(h4, iterator.next());
        assertEquals(h1, iterator.next());
        assertEquals(h2, iterator.next());
        assertEquals(h3, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(h3, iterator.previous());
        assertEquals(h2, iterator.previous());
        assertEquals(h1, iterator.previous());
        assertEquals(h4, iterator.previous());
        
        try {
            // no more elements in the list
            iterator.previous();
            fail("Did not throw a NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Successfully threw a NoSuchElementException
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        linkedHouse.addToFront(h4);
        linkedHouse.addToEnd(h3);
        ListIterator<House> iterator = linkedHouse.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(h4, iterator.next());
        assertEquals(h1, iterator.next());
        assertEquals(h2, iterator.next());
        assertEquals(true, iterator.hasNext());
        
        try {
            // remove is not supported for the iterator
            iterator.remove();
            fail("Did not throw a UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Successfully threw a UnsupportedOperationException
        }
    }

    @Test
    public void testRemove() {
        // remove the first
        assertEquals(h1, linkedHouse.getFirst());
        assertEquals(h2, linkedHouse.getLast());
        linkedHouse.addToFront(h3);
        assertEquals(h3, linkedHouse.getFirst());
        linkedHouse.remove(h3, comparator);
        assertEquals(h1, linkedHouse.getFirst());
        // remove from the end of the list
        linkedHouse.addToEnd(h4);
        assertEquals(h4, linkedHouse.getLast());
        linkedHouse.remove(h4, comparator);
        assertEquals(h2, linkedHouse.getLast());
        // remove from the middle of the list
        linkedHouse.addToFront(h3);
        assertEquals(h3, linkedHouse.getFirst());
        assertEquals(h2, linkedHouse.getLast());
     
    }

    private class House {
        private String owner;
        private Double price;

        public House(String owner, Double price) {
            this.owner = owner;
            this.price = price;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    private class HouseComparator implements Comparator<House> {
        public int compare(House o1, House o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    }
}
