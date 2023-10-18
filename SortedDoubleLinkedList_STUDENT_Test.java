

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedDoubleLinkedList_STUDENT_Test {

	
	SortedDoubleLinkedList<House> h;
	
	houseComparator c;
	
	public House h1 = new House("jhon",200000.0);
	public House h2 = new House("dagm",230000.0);
	public House h3 = new House("bob",300000.0);
	public House h4 = new House("adam",13200000.0);
	
	@Before
	public void setUp() throws Exception {
		c = new houseComparator();
		h = new SortedDoubleLinkedList<House>(c);
	}
	
	@After
	public void tearDown() throws Exception{
		c = null;
		h = null;
	}
	
	@Test
	public void testAddToEnd() {
		try {
			h.addToEnd(h1);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testAddToFront() {
		try {
			h.addToFront(h2);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	
	@Test
	public void testIteratorSuccessfulNext() {
		h.add(h1);
		h.add(h2);
		h.add(h3);
		h.add(h4);
		ListIterator<House> iterator = h.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(h1, iterator.next());
		assertEquals(h2, iterator.next());
		assertEquals(h3, iterator.next());
		assertEquals(true, iterator.hasNext());
		
	}
	
	
	@Test
	public void testIteratorNoSuchElementException() {
		h.add(h1);
		h.add(h2);
		h.add(h3);
		ListIterator<House> iterator = h.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(h1, iterator.next());
		assertEquals(h2, iterator.next());
		assertEquals(h3, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		h.add(h1);
		h.add(h2);
		h.add(h3);
		h.add(h4);
		ListIterator<House> iterator = h.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(h1, iterator.next());
		assertEquals(h2, iterator.next());
		assertEquals(h3, iterator.next());
		assertEquals(true, iterator.hasNext());
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	
	@Test
	public void testAdd() {
	    // Test adding elements and verify the sorted order
	    h.add(h3);
	    h.add(h1);
	    h.add(h2);
	    h.add(h4);
	    ListIterator<House> iterator = h.iterator();
	    assertEquals(h1, iterator.next());
	    assertEquals(h2, iterator.next());
	    assertEquals(h3, iterator.next());
	    assertEquals(h4, iterator.next());
	}

	@Test
	public void testRemove() {
	    h.add(h1);
	    h.add(h2);
	    h.add(h3);
	    h.add(h4);

	    // Test removing an element and verify the sorted order
	    h.remove(h2,c);
	    ListIterator<House> iterator = h.iterator();
	    assertEquals(h1, iterator.next());
	    assertEquals(h3, iterator.next());
	    assertEquals(h4, iterator.next());

	    // Test removing the first element and verify the sorted order
	    h.remove(h1,c);
	    iterator = h.iterator();
	    assertEquals(h3, iterator.next());
	    assertEquals(h4, iterator.next());

	    // Test removing the last element and verify the sorted order
	    h.remove(h4,c);
	    iterator = h.iterator();
	    assertEquals(h3, iterator.next());

	    // Test removing the remaining element and verify the list is empty
	    h.remove(h3,c);
	    assertFalse(iterator.hasNext());
	    assertEquals(0, h.getSize());
	}

	private class House{
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
	
	private class houseComparator implements Comparator<House>{

		@Override
		public int compare(House o1, House o2) {	
			return o1.getPrice().compareTo(o2.getPrice());
		}
		
	}
	public static void main(String args[]) {
		
	}
}
