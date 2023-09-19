import java.util.ArrayList;
import java.util.Collection;


/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources: writeup
 * 
 * 
 * This class implements the MinHeapInterface and provides the implementation
 * of the methods in the interface such as insert(), getMin(), remove(), 
 * size(), and clear().
*/
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {

    protected ArrayList<E> data;

    // to avoid magic numbers
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    /**
     * Default constructor
     * Creates an empty heap
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }

    /**
     * Constructor that takes in a collection of elements
     * 
     * @param collection the collection of elements
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);

        for (int i = data.size() - ONE; i >= ZERO; i--) {
            percolateDown(i);
        }

    }

    /**
     * insert the element into the heap
     * 
     * @param element the element to be inserted
     */
    @Override
    public void insert(E element) {
        // check if the element is null
        if (element == null) {
            throw new NullPointerException();
        }
        // add the element to the end of the arraylist
        data.add(element);
        // percolate up the element
        percolateUp(data.size() - ONE);

    }

    /**
     * Return the root element of the heap. If the heap is empty, return null
     * instead.
     * 
     * @return the root element of the heap
     */
    @Override
    public E getMin() {
        // check if the heap is empty
        if (data.size() == ZERO) {
            return null;
        }
        // return the root element
        return data.get(ZERO);
    }

    /**
     * Remove the root element of the heap. If the heap is empty, 
     * return null
     * 
     * @return the root element of the heap
     */
    @Override
    public E remove() {
        // check if the heap is empty
        if (data.size() == ZERO) {
            return null;
        }
        // store the root element
        E root = data.get(ZERO);
        // delete the root element
        deleteIndex(ZERO);
        // return the root element
        return root;
    }

    /**
     * Return the number of elements in this min-heap.
     * 
     * @return the number of elements in this min-heap
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Clear the heap
     */
    @Override
    public void clear() {

        data.clear();

    }

    /**
     * Helper method to swap two elements in the data arraylist
     * 
     * @param from the index of the element to be swapped
     * @param to   the index of the element to be swapped
     */
    protected void swap(int from, int to) {
        E temp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, temp);
    }

    /**
     * Helper method to get the index of the parent of the element at index
     * 
     * @param index the index of the element
     * @return the index of the parent of the element at index
     */
    protected static int getParentIdx(int index) {
        return (index - ONE) / TWO;

    }

    /**
     * Helper method to get the index of the right child of the element at index
     * 
     * @param index the index of the element
     * @return the index of the right child of the element at index
     */
    protected static int getLeftChildIdx(int index) {
        return TWO * index + ONE;
    }

    /**
     * Helper method to get the index of the right child of the element at index
     * 
     * @param index the index of the element
     * @return the index of the right child of the element at index
     */
    protected static int getRightChildIdx(int index) {

        return TWO * index + TWO ;
    }

    /**
     * Helper method to get the index of the minimum child of the element 
     * at index
     * 
     * @param index the index of the element
     * @return the index of the minimum child of the element at index
     */
    protected int getMinChildIdx(int index) {
        // get the index of the left child and right child
        int leftChildIdx = getLeftChildIdx(index);
        int rightChildIdx = getRightChildIdx(index);

        E leftChild = null;
        E rightChild = null;
        // get the left child and right child
        if (leftChildIdx < data.size() && leftChildIdx > ZERO) {
            leftChild = data.get(leftChildIdx);
        }
        if (rightChildIdx < data.size() && rightChildIdx > ZERO) {
            rightChild = data.get(rightChildIdx);
        }

        if (leftChild == null || rightChild == null) {
            // if both children are null, return -1
            if (leftChild == null && rightChild == null) {
                return -ONE;
            } else {
                return leftChildIdx;
            }
        }

        // if both children are not null,
        // return the index of the index of smaller child
        if (leftChild.compareTo(rightChild) <= ZERO) {
            return leftChildIdx;
        } else {
            return rightChildIdx;
        }
    }

    /**
     * Helper method to percolate up the element at index
     * 
     * @param index the index of the element
     */
    protected void percolateUp(int index) {

        // if the element is the root, return
        if (index == ZERO) {
            return;
        }

        // get the index of the parent
        int parentIdx = getParentIdx(index);
        // get the element and its parent
        E parent = data.get(parentIdx);

        // if the element is greater than its parent, return
        // else, swap the element with its parent and percolate up
        if (data.get(index).compareTo(parent) >= ZERO) {
            return;
        } else {
            // swap the element with its parent
            swap(index, parentIdx);
            percolateUp(parentIdx);

        }
    }

    /**
     * Helper method to percolate down the element at index
     * 
     * @param index the index of the element
     */
    protected void percolateDown(int index) {
        // if the element is a leaf, return
        if (index > (data.size() - ONE) / TWO) {
            return;
        }

        // get the index of the minimum child
        int minChildIdx = getMinChildIdx(index);

        // if the element has no child, return
        if (minChildIdx == -ONE) {
            return;
        }

        // if the element is smaller than its minimum child, return
        // else, swap the element with its minimum child and percolatedown
        if (data.get(index).compareTo(data.get(minChildIdx)) <= ZERO) {
            return;
        } else {
            // swap the element with its minimum child
            swap(index, minChildIdx);
            percolateDown(minChildIdx);
        }

    }

    /**
     * Helper method to delete the element at index
     * 
     * @param index the index of the element
     * @return the element at index
     */
    protected E deleteIndex(int index) {
        // get the element at index
        E element = data.get(index);

        // if the element is the last element, remove it and return
        if (index == data.size() - ONE) {
            data.remove(index);
            return element;
        }

        // swap the element with the last element
        swap(index, data.size() - ONE);
        // remove the last element
        data.remove(data.size() - ONE);
        // percolate down the element at index
        percolateDown(index);

        return element;

    }

}
