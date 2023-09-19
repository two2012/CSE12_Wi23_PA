import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CustomTester {

    /**
     * Helper method to initialize all instance variables of MyDeque
     *
     * @param data the data array
     */
    static <E extends Comparable<E>> MyMinHeap<E> initMinHeap(List<E> data) {
        MyMinHeap<E> heap = new MyMinHeap<>();
        heap.data = new ArrayList<>(data);
        return heap;
    }

    /**
     * test constructor with null input
     */
    @Test
    public void testMinHeapConstructorNull() {
        try {
            MyMinHeap<Integer> heap = new MyMinHeap<>(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
    
        }
    }

    /**
     * test getMinChildIdx 
     * 1. no child (-1)
     * 2. only one child
     *    1. only Left child (left)
     * 3. two children
     *    1. same value (left)
     *    2. left Bigger (right)
     *    3. right Bigger (left) 
     */
    @Test
    public void testGetMinChildIdx() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5, 1, 4, 7, 2, 6, 6, 3));
        // 1. no child (-1)
        assertEquals("no child", -1, heap.getMinChildIdx(6));
        assertEquals("no child", -1, heap.getMinChildIdx(7));
        assertEquals("no child", -1, heap.getMinChildIdx(4));

        // 2. only one child (left)
        assertEquals("only one child(left)", 7, heap.getMinChildIdx(3));

        // 3. two children (same value)
        assertEquals("two children(same value)", 5, heap.getMinChildIdx(2));

        // 3. two children (left bigger)
        assertEquals("two children(left bigger)", 4, heap.getMinChildIdx(1));

        // 3. two children (right bigger)
        assertEquals("two children(right bigger)", 1, heap.getMinChildIdx(0));
        
        
        
    }

    

    /**
     * test percolateUp method
     * 1. index == 0(root) (array no change)
     * 2. leaf 
     *     1. == its parent (array no change)
     *     2. > its parent (array no change)
     *     3. < its parent (array change)
     * 3. node
     *     1. == its parent (array no change)
     *     2. > its parent (array no change)
     *     3. < its parent (array change)
     */
    @Test
    public void testPercolateUp(){
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3));

        // 1. index == 0(root) (array no change)
        heap.percolateUp(0);
        assertEquals("index == 0(root)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3), heap.data);

        // 2. leaf (== its parent)
        heap.percolateUp(5);
        assertEquals("leaf (== its parent)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3), heap.data);
        // 2. leaf (> its parent)
        heap.percolateUp(6);
        assertEquals("leaf (> its parent)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3), heap.data);
        // 2. leaf (< its parent)
        heap.percolateUp(7);
        assertEquals("leaf (< its parent)", Arrays.asList(3, 5, 4, 5, 2, 4, 6, 7), heap.data);

        // 3. node (== its parent)
        heap.percolateUp(3);    
        assertEquals("node (== its parent)", Arrays.asList(3, 5, 4, 5, 2, 4, 6, 7), heap.data);

        // 3. node (> its parent)
        heap.percolateUp(1);
        assertEquals("node (> its parent)", Arrays.asList(3, 5, 4, 5, 2, 4, 6, 7), heap.data);


        // 3. node (< its parent)
        heap = initMinHeap(Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3));
        heap.percolateUp(2);
        assertEquals("node (< its parent)", Arrays.asList(4, 5, 5, 7, 2, 4, 6, 3), heap.data);



    }


    /**
     * test percolateDown method
     * 1. leaf (array no change)
     * 2. node 
     *     1. == min of its children (array no change)
     *     2. < min of its children (array no change)
     *     3. > min of its children (array change)
     *     
     */
    @Test
    public void testPercolateDown(){
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3,1,8,9));

        // 1. leaf (array no change)
        heap.percolateDown(7);
        assertEquals("leaf (array no change)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3,1,8,9), heap.data);

        // 2. node (== min of its children)
        heap.percolateDown(2);
        assertEquals("node (== min of its children)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3,1,8,9), heap.data);

        // 2. node (< min of its children)
        heap.percolateDown(2);
        assertEquals("node (< min of its children)", Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3,1,8,9), heap.data);

        // 2. node (> min of its children)
        heap.percolateDown(0);
        assertEquals("node (> min of its children)", Arrays.asList(4, 5, 4, 7, 2, 5, 6, 3,1,8,9), heap.data);

        

    }


    /**
     * test deleteIndex method
     * 1. node (need percolateDown)
     * 2. last element (no need percolateDown)
     * 3. multyple times
     */
    @Test
    public void testDeleteIndex(){
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3,1,8,9));

        // 1. node (need percolateDown)
        heap.deleteIndex(2);
        assertEquals("node (need percolateDown)", Arrays.asList(5, 5, 4, 7, 2, 9, 6, 3,1,8), heap.data);

        // 2. last element (no need percolateDown)
        heap.deleteIndex(9);
        assertEquals("leaf (no need percolateDown)", Arrays.asList(5, 5, 4, 7, 2, 9, 6, 3,1), heap.data);

        // 3. multyple times
        heap.deleteIndex(0);
        heap.deleteIndex(1);
        heap.deleteIndex(2);
        heap.deleteIndex(3);
        assertEquals("multyple times", Arrays.asList(1,2,6,9,3), heap.data);


    }


    /**
     * test insert method
     * 1. null (NullPointerException)
     * 2. multyple times
     */
    @Test
    public void testInsert(){
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5, 5, 4, 7, 2, 4, 6, 3));

        // 1. null (NullPointerException)
        try{
            heap.insert(null);
            fail("NullPointerException expected");
        }catch(NullPointerException e){

        }

        // 2. multyple times
        heap.insert(1);
        heap.insert(8);
        heap.insert(9);
        assertEquals("multyple times", Arrays.asList(1, 5, 4, 5, 2, 4, 6, 3, 7, 8, 9), heap.data);
    }

    /**
     * test getMin method
     * 1. empty array (null)
     */
    @Test
    public void testGetMin(){
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList());

        // 1. empty array (null)
        assertNull("empty array (null)", heap.getMin());

    }

    /**
     * test remove method
     * 1. empty array (null)
     */
    @Test
    public void testRemove(){
        // 1. empty array (null)
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList());
        assertNull("empty array (null)", heap.remove());

    }

}

