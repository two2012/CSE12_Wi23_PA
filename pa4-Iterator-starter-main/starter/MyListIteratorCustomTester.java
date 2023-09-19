/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: PA4 Write-up
 * 
 * This file is for custom tests for MyListIterator class
 * Iterator values were set manually to decouple its dependency on next() 
 * during testing.
 */
// DO NOT CHANGE THE METHOD NAMES

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This file is for custom tests for MyListIterator class
 * Iterator values were set manually to decouple its dependency on next() 
 * during testing.
 */
public class MyListIteratorCustomTester {

    private MyLinkedList listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Java");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Hello");
        listLen2.add("World");
        listLen2Iter = listLen2.new MyListIterator();

    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextEnd() {

        listLen1Iter.left = listLen1.tail.prev;
        listLen1Iter.right = listLen1.tail;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = true;

        listLen1Iter.next();



    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousStart() {
        listLen1Iter.left = listLen1.head;
        listLen1Iter.right = listLen1.head.getNext();
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = false;
        
        listLen1Iter.previous();
        
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test(expected = NullPointerException.class)
    public void testAddInvalid() {

        listLen2Iter.add(null);
    
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantSet() {

        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = false;

        
        listLen2Iter.set("Python");
         

    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {

        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;

        try {
            listLen2Iter.set(null);
            fail();
        } catch (NullPointerException e) {
            
        }
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantRemove() {
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = false;

        listLen1Iter.remove();


    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = true;
        assertFalse(listLen1Iter.hasNext());

    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        listLen1Iter.left = listLen1.head;
        listLen1Iter.right = listLen1.head.getNext();
        listLen1Iter.idx = 0;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = false;
        assertFalse(listLen1Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        listLen2Iter.left = listLen2.head;
        listLen2Iter.right = listLen2.head.getNext();
        listLen2Iter.idx = 0;
        listLen2Iter.forward = false;
        listLen2Iter.canRemoveOrSet = false;
        assertEquals(-1, listLen2Iter.previousIndex());

    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen2Iter.left = listLen2.tail.getPrev();
        listLen2Iter.right = listLen2.tail;
        listLen2Iter.idx = 2;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = false;
        assertEquals(2, listLen2Iter.nextIndex());
    }
}
