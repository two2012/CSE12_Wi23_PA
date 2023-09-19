import java.util.Collection;

/**
 * Name: Wen Guo
 * Email: w5guo.ucsd.edu
 * PID: A17630856
 * Sources: Writeup
 * 
 * 
 * This class implements the MinHeapInterface and provides the implementation
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    protected MyMinHeap<E> heap;

    /**
     * Default constructor
     * Creates an empty heap
     */
    public MyPriorityQueue() {

        heap = new MyMinHeap<>();

    }

    /**
     * Constructor that takes in a collection of elements
     * 
     * @param collection the collection of elements
     */
    public MyPriorityQueue(Collection<? extends E> collection) {

        // check if the collection is null
        if (collection == null) {
            throw new NullPointerException();
        }

        // create a new heap
        heap = new MyMinHeap<>(collection);

    }

    /**
     * insert the element into the heap
     * 
     * @param element the element to be inserted
     */
    public void push(E element) {
        // check if the element is null
        if (element == null) {
            throw new NullPointerException();
        }
        // add the element to the heap
        heap.insert(element);
    }

    /**
     * peek the element at the top of the heap
     * 
     * @return the element at the top of the heap
     */
    public E peek() {
        // check if the heap is empty
        if (heap.size() == 0) {
            return null;
        }

        // return the element at the top of the heap
        return heap.getMin();
    }

    /**
     * remove the element at the top of the heap
     * 
     * @return the element at the top of the heap
     */
    public E pop() {
        // check if the heap is empty
        if (heap.size() == 0) {
            return null;
        }

        // remove the element at the top of the heap
        return heap.remove();
    }

    /**
     * get the size of the heap
     * 
     * @return the size of the heap
     */
    public int getLength() {
        return heap.size();
    }

    /**
     * clear the heap
     */
    public void clear() {
        heap.clear();
    }

}
