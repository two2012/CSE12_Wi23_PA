/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources: pa6 writeup
 * 
 * this is a generic class that implements the DequeInterface
 * and implements all the methods in the interface
 */


public class MyDeque<E> implements DequeInterface<E> {

    Object[] data;
    int size;
    int front;
    int rear;

    // to avoid magic numbers
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int TEN = 10;
    

    /**
     * Constructor to create new MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyDeque(int initialCapacity) {
        // check if initialCapacity is valid
        if (initialCapacity < ZERO) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * Adds the specified element to the front of this MyDeque.
     *
     * @param element the element to add to the deque
     */
    @Override
    public void addFirst(E element) {
        // check if the element is null
        if (element == null) {
            throw new NullPointerException();
        }
        // check if the size is equal to the capacity
        if (size == data.length) {
            expandCapacity();
        }
        if (size == 0) {
            data[0] = element;
            size++;
            return;
        }
        // check if the front is 0
        if (front == 0) {
            data[data.length - 1] = element;
            // set the front to the last index of the array
            front = data.length - 1;
        } else {
            data[front - 1] = element;
            // decrement the front
            front--;
        }
        // increment the size
        size++;
    }

    /**
     * Adds the specified element to the rear of this MyDeque.
     *
     * @param element the element to add to the deque
     */
    @Override
    public void addLast(E element) {
        // check if the element is null
        if (element == null) {
            throw new NullPointerException();
        }
        // check if the size is equal to the capacity
        if (size == data.length) {
            expandCapacity();
        }
        if (size == 0) {
            data[0] = element;
            size++;
            return;
        }
        // check if the rear is the last index of the array
        if (rear == data.length - 1) {
            data[ZERO] = element;
            // set the rear to the first index of the array
            rear = ZERO;
        } else {
            data[rear + ONE] = element;
            // increment the rear
            rear++;
        }
        size++;

    }

    /**
     * Doubles the current capacity. If the capacity is 0,
     * set the capacity to a default value of 10.
     * This method should preserve the current size
     * and elements in the list.
     */
    @Override
    public void expandCapacity() {
        // get the original capacity
        int originalCapacity = data.length;
        // check if the capacity is 0
        if (originalCapacity == ZERO) {
            data = new Object[TEN];
        } else {
            // double the capacity
            Object[] temp = new Object[originalCapacity * TWO];
            // copy the elements from the original array to the new array
            for (int i = ZERO; i < originalCapacity; i++) {
                // check if the element is null
                if (data[(front + i) % originalCapacity] != null ) {
                    temp[i] = data[(front + i) % originalCapacity];
                }
            }
            // set the new array to the original array
            data = temp;
            // reset the front and rear
            front = 0;
            if (size != 0) {
                rear = size - 1;
            } else {
                rear = 0;
            }
        }
    }

    /**
     * Returns the element at the front of this MyDeque.
     * Returns null if there is no such element.
     *
     * @return the element at the front of the deque, or null if there is 
     *         no such element
     */
    @Override
    public E peekFirst() {
        // check if the size is 0
        if (size == ZERO) {
            return null;
        }
        // get the element at the front
        E returnElement = (E) data[front];
        return returnElement;
    }

    /**
     * Returns the element at the rear of this MyDeque.
     * Returns null if there is no such element.
     *
     * @return the element at the rear of the deque, or null if there is no such
     *         element
     */
    @Override
    public E peekLast() {
        // check if the size is 0
        if (size == ZERO) {
            return null;
        }
        // get the element at the rear
        E returnElement = (E) data[rear];
        return returnElement;
    }

    /**
     * Removes the element at the front of this MyDeque.
     * Returns the element removed, or null if there was no such
     * element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E removeFirst() {
        // check if the size is 0
        if (size == ZERO) {
            return null;
        }
        // get the element at the front
        E returnElement = (E) data[front];
        // set the element at the front to null
        data[front] = null;
        size--;
        if (size != 0) {
            if (front == data.length - ONE) {
                front = 0;
            } else {
                front++;
            }
        }
        return returnElement;
    }

    /**
     * Removes the element at the rear of this MyDeque.
     * Returns the element removed, or null if there was no such
     * element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E removeLast() {
        // check if the size is 0
        if (size == ZERO) {
            return null;
        }
        // get the element at the rear
        E returnElement = (E) data[rear];
        // set the element at the rear to null
        data[rear] = null;
        size--;
        
        // check if the size is not 0
        if (size != 0) {
            // check if the rear is the first index of the array
            if (rear == ZERO) {
                rear = data.length - ONE;
            } else {
                rear--;
            }
        }
        return returnElement;
    }

    /**
     * Returns the number of elements in this deque.
     * 
     * @return the number of elements in the deque
     */
    @Override
    public int size() {
        return this.size;
    }

}
