/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources: pa6 writeup
 * 
 * This class implements the Stack ADT using a MyDeque instance variable called
 * theStack.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the MyDeque class.
 */
public class CustomTester {

    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }

    /*------------ Deque ---------------*/

    MyDeque<Integer> validNonFull = new MyDeque<>(10);
    MyDeque<Integer> atCapacity = new MyDeque<>(5);
    MyDeque<Integer> frontWrap = new MyDeque<>(10);
    MyDeque<Integer> rearWrap = new MyDeque<>(10);
    MyDeque<Integer> oneElement = new MyDeque<>(5);
    MyDeque<Integer> emptyDeque = new MyDeque<>(5);
    MyDeque<Integer> zeroCapacity = new MyDeque<>(0);


    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        Integer[] d1 = {1, 2, 3, 4, 5};
        Integer[] d2 = {1, 2, 3, 4, null, null, null, null, null, null};
        Integer[] d3 = {1, 2, 3, null, null, null, null, null, null, 4};
        Integer[] d4 = {1, null, null, null, null};
        Integer[] d5 = {4, null, null, null, null, null, null, 1, 2, 3};
        


        initDeque(validNonFull, d2, 4, 0, 3);
        initDeque(atCapacity, d1, 5, 0, 4);
        initDeque(frontWrap, d3, 4, 9, 2);
        initDeque(oneElement, d4, 1, 0, 0);
        initDeque(rearWrap, d5, 4, 7, 0);
        
    }

    /**
     * Tests the constructor of MyDeque.
     * 1. invalid capacity
     * 2. valid capacity
     * 
     */
    @Test
    public void testDequeConstructor(){
        MyDeque<Integer> validDeque = new MyDeque<>(5);

        try {
            MyDeque<Integer> invalidDeque = new MyDeque<>(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            
        }
        assertEquals("check capacity", 5, validDeque.data.length);
        assertEquals("check size", 0, validDeque.size());
        assertEquals("check front", 0, validDeque.front);
        assertEquals("check rear", 0, validDeque.rear);
    }


    /**
     * Tests the addFirst method of MyDeque.
     * 1. null element
     * 2. valid element
     * 3. full capacity
     * 4. front > rear
     * 5. front == rear
     * 6. empty deque
     * 7. add multiple elements
     */
    @Test
    public void testAddFirst() {
        
        // 1. null element
        try {
            validNonFull.addFirst(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
         
        } 
        // 2. valid element
        validNonFull.addFirst(7);
        assertEquals("check size", 5, validNonFull.size());
        assertEquals("check front", 9, validNonFull.front);
        assertEquals("check rear", 3, validNonFull.rear);
        assertEquals("check element", 7, validNonFull.data[9]);

        rearWrap.addFirst(7);
        assertEquals("check size", 5, rearWrap.size());
        assertEquals("check front", 6, rearWrap.front);
        assertEquals("check rear", 0, rearWrap.rear);
        assertEquals("check element", 7, rearWrap.data[6]);

        // 3. full capacity
        atCapacity.addFirst(7);
        assertEquals("check size", 6, atCapacity.size());
        assertEquals("check front", 9, atCapacity.front);
        assertEquals("check rear", 4, atCapacity.rear);
        assertEquals("check element", 7, atCapacity.data[9]);
        // 4. front > rear
        frontWrap.addFirst(7);
        assertEquals("check size", 5, frontWrap.size());
        assertEquals("check front", 8, frontWrap.front);
        assertEquals("check rear", 2, frontWrap.rear);
        assertEquals("check element", 7, frontWrap.data[8]);
        // 5. front == rear
        oneElement.addFirst(7);
        assertEquals("check size", 2, oneElement.size());
        assertEquals("check front", 4, oneElement.front);
        assertEquals("check rear", 0, oneElement.rear);
        assertEquals("check element", 7, oneElement.data[4]);

        // 6. empty deque
        emptyDeque.addFirst(7);
        assertEquals("check size", 1, emptyDeque.size());
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 0, emptyDeque.rear);
        assertEquals("check element", 7, emptyDeque.data[0]);

        // 7. add multiple elements
        emptyDeque.removeFirst();
        for ( int i = 0; i < 100; i++) {
            emptyDeque.addFirst(i);
        }


        assertEquals("check size", 100, emptyDeque.size());
        assertEquals("check front", 140, emptyDeque.front);
        assertEquals("check rear", 79, emptyDeque.rear);
        assertEquals("check capacity", 160, emptyDeque.data.length);
        assertEquals("check element", 0, emptyDeque.data[79]);
        assertEquals("check element", 99, emptyDeque.data[140]);
        assertEquals("check element", 79, emptyDeque.data[0]);
        assertEquals("check element", 80, emptyDeque.data[159]);






        
    }


    /**
     * Tests the addLast method of MyDeque.
     * 1. null element
     * 2. valid element
     * 3. full capacity
     * 4. front > rear
     * 5. front == rear
     * 6. empty deque
     * 7. add multiple elements
     */
    @Test
    public void testAddLast() {
        // 1. null element
        try {
            validNonFull.addLast(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            
        }
        
        // 2. valid element
        validNonFull.addLast(7);
        assertEquals("check size", 5, validNonFull.size());
        assertEquals("check front", 0, validNonFull.front);
        assertEquals("check rear", 4, validNonFull.rear);
        assertEquals("check element", 7, validNonFull.data[4]);

        rearWrap.addLast(7);
        assertEquals("check size", 5, rearWrap.size());
        assertEquals("check front", 7, rearWrap.front);
        assertEquals("check rear", 1, rearWrap.rear);
        assertEquals("check element", 7, rearWrap.data[1]);


        // 3. full capacity
        atCapacity.addLast(7);
        assertEquals("check size", 6, atCapacity.size());
        assertEquals("check front", 0, atCapacity.front);
        assertEquals("check rear", 5, atCapacity.rear);
        assertEquals("check element", 7, atCapacity.data[5]);

        // 4. front > rear
        frontWrap.addLast(7);
        assertEquals("check size", 5, frontWrap.size());
        assertEquals("check front", 9, frontWrap.front);
        assertEquals("check rear", 3, frontWrap.rear);
        assertEquals("check element", 7, frontWrap.data[3]);

        // 5. front == rear
        oneElement.addLast(7);
        assertEquals("check size", 2, oneElement.size());
        assertEquals("check front", 0, oneElement.front);
        assertEquals("check rear", 1, oneElement.rear);
        assertEquals("check element", 7, oneElement.data[1]);

        // 6. empty deque
        emptyDeque.addLast(7);
        assertEquals("check size", 1, emptyDeque.size());
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 0, emptyDeque.rear);
        assertEquals("check element", 7, emptyDeque.data[0]);

        // 7. add multiple elements
        emptyDeque.addLast(6);
        emptyDeque.addLast(5);
        emptyDeque.addLast(4);
        assertEquals("check size", 4, emptyDeque.size());
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 3, emptyDeque.rear);
        assertEquals("check element", 7, emptyDeque.data[0]);
        assertEquals("check element", 6, emptyDeque.data[1]);
        assertEquals("check element", 5, emptyDeque.data[2]);
        assertEquals("check element", 4, emptyDeque.data[3]);


    }


    /**
     * Tests the removeFirst method of MyDeque.
     * 1. empty deque
     * 2. non-contiguous deque
     * 3. capacity == 0
     */
    @Test
    public void testExpandCapacity() {
        // 1. empty deque
        emptyDeque.expandCapacity();
        assertEquals("check capacity", 10, emptyDeque.data.length);
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 0, emptyDeque.rear);
        assertEquals("check size", 0, emptyDeque.size());

        // 2. non-contiguous deque
        frontWrap.expandCapacity();
        assertEquals("check capacity", 20, frontWrap.data.length);
        assertEquals("check front", 0, frontWrap.front);
        assertEquals("check rear", 3, frontWrap.rear);
        assertEquals("check size", 4, frontWrap.size());

        rearWrap.expandCapacity();
        assertEquals("check capacity", 20, rearWrap.data.length);
        assertEquals("check front", 0, rearWrap.front);
        assertEquals("check rear", 3, rearWrap.rear);
        assertEquals("check size", 4, rearWrap.size());

        // 3. capacity == 0
        zeroCapacity.expandCapacity();
        assertEquals("check capacity", 10, zeroCapacity.data.length);
        assertEquals("check front", 0, zeroCapacity.front);
        assertEquals("check rear", 0, zeroCapacity.rear);
        assertEquals("check size", 0, zeroCapacity.size());



    }

    

    /**
     * Tests the removeLast method of MyDeque.
     * 1. empty deque
     * 2. non-empty deque
     * 3. front > rear
     * 4. front == rear
     * 5. remove multiple elements
     */
    @Test
    public void testRemoveLast() {
        // 1. empty deque
        assertEquals(null, emptyDeque.removeLast());
        assertEquals("check size", 0, emptyDeque.size());
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 0, emptyDeque.rear);
        assertEquals("check capacity", 5, emptyDeque.data.length);

        // 2. non-empty deque
        assertEquals(5, atCapacity.removeLast().intValue());
        assertEquals("check size", 4, atCapacity.size());
        assertEquals("check front", 0, atCapacity.front);
        assertEquals("check rear", 3, atCapacity.rear);
        assertEquals("check capacity", 5, atCapacity.data.length);

        // 5. remove multiple elements
        assertEquals(4, atCapacity.removeLast().intValue());
        assertEquals(3, atCapacity.removeLast().intValue());
        assertEquals(2, atCapacity.removeLast().intValue());
        assertEquals("check size", 1, atCapacity.size());
        assertEquals("check front", 0, atCapacity.front);
        assertEquals("check rear", 0, atCapacity.rear);
        assertEquals("check capacity", 5, atCapacity.data.length);

        // 2. non-empty deque
        assertEquals(4,rearWrap.removeLast().intValue());
        assertEquals("check size", 3, rearWrap.size());
        assertEquals("check front", 7, rearWrap.front);
        assertEquals("check rear", 9, rearWrap.rear);
        assertEquals("check capacity", 10, rearWrap.data.length);


        // 3. front > rear
        assertEquals(3, frontWrap.removeLast().intValue());
        assertEquals("check size", 3, frontWrap.size());
        assertEquals("check front", 9, frontWrap.front);
        assertEquals("check rear", 1, frontWrap.rear);
        assertEquals("check capacity", 10, frontWrap.data.length);

        // 4. front == rear
        assertEquals(1, oneElement.removeLast().intValue());
        assertEquals("check size", 0, oneElement.size());
        assertEquals("check front", 0, oneElement.front);
        assertEquals("check rear", 0, oneElement.rear);
        assertEquals("check capacity", 5, oneElement.data.length);
        
        
    }


    /**
     * Tests the removeFirst method of MyDeque.
     * 1. empty deque
     * 2. non-empty deque
     * 3. front > rear
     * 4. front == rear
     * 5. remove multiple elements
     */
    @Test
    public void testRemoveFirst() {

        // 1. empty deque
        assertEquals(null, emptyDeque.removeFirst());
        assertEquals("check size", 0, emptyDeque.size());
        assertEquals("check front", 0, emptyDeque.front);
        assertEquals("check rear", 0, emptyDeque.rear);
        assertEquals("check capacity", 5, emptyDeque.data.length);

        // 3. front > rear
        assertEquals(4, frontWrap.removeFirst().intValue());
        assertEquals("check size", 3, frontWrap.size());
        assertEquals("check front", 0, frontWrap.front);
        assertEquals("check rear", 2, frontWrap.rear);
        assertEquals("check capacity", 10, frontWrap.data.length);

        // 4. front == rear
        assertEquals(1, oneElement.removeFirst().intValue());
        assertEquals("check size", 0, oneElement.size());
        assertEquals("check front", 0, oneElement.front);
        assertEquals("check rear", 0, oneElement.rear);
        assertEquals("check capacity", 5, oneElement.data.length);

        // 2. non-empty deque
        assertEquals(1, rearWrap.removeFirst().intValue());
        assertEquals("check size", 3, rearWrap.size());
        assertEquals("check front", 8, rearWrap.front);
        assertEquals("check rear", 0, rearWrap.rear);
        assertEquals("check capacity", 10, rearWrap.data.length);

        assertEquals(1, atCapacity.removeFirst().intValue());
        assertEquals("check size", 4, atCapacity.size());
        assertEquals("check front", 1, atCapacity.front);
        assertEquals("check rear", 4, atCapacity.rear);
        assertEquals("check capacity", 5, atCapacity.data.length);

        // 5. remove multiple elements
        assertEquals(2, atCapacity.removeFirst().intValue());
        assertEquals(3, atCapacity.removeFirst().intValue());
        assertEquals(4, atCapacity.removeFirst().intValue());
        assertEquals("check size", 1, atCapacity.size());
        assertEquals("check front", 4, atCapacity.front);
        assertEquals("check rear", 4, atCapacity.rear);
        assertEquals("check capacity", 5, atCapacity.data.length);
    }


    

}

