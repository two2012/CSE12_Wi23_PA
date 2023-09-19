
/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: PA3 Write-up
 * 
 * This file is a class that is a data structure 
 * similar to Java’s LinkedList with generic types
 */
import java.util.AbstractList;

/**
 * This file is a class that is a data structure
 * similar to Java’s ArrayLists with generic types
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    // to avoid magic numbers
    private static final int ZERO = 0;

    /**
     * A Node class that holds data and references
     * to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * 
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * 
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * 
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * 
         * @param element new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * 
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    // Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */
    /**
     * Constructor that creates an empty list and
     * initializes all the necessary variables.
     */
    public MyLinkedList() {
        /* Add your implementation here */
        // set dummy head and tail and size
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = ZERO;

    }

    /**
     * Return the number of (non-sentinel) nodes stored in the list.
     * 
     * @return Return the number of nodes stored in the list
     */
    @Override
    public int size() {

        return this.size; // TODO
    }

    /**
     * Get data of type E within the node at position index.
     * 
     * @param index position index
     * @return data of type E
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        return getNth(index).getElement();
    }

    /**
     * Add a node into this list by index.
     * 
     * @param index index that data need be add
     * @param data  data needs be added
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        // check if data is null
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        // create a node for data
        Node added = new Node(data);

        if (this.isEmpty()) {
            head.setNext(added);
            added.setPrev(head);
            added.setNext(tail);
            tail.setPrev(added);

        } else {
            // get the oth element of the list
            Node current = getNth(index);

            // get previous node of index position
            Node previous = current.getPrev();
            // insert the added node between current and next
            previous.setNext(added);
            added.setNext(current);
            added.setPrev(previous);
            current.setPrev(added);
        }

        size++;

    }

    /**
     * Add a node at the end into this list.
     * 
     * @param data data to be added
     * @return true for add successed
     */
    public boolean add(E data) {
        // check if data is null
        if (data == null) {
            throw new NullPointerException();
        }
        // create a node for data
        Node added = new Node(data);
        // get last element of list
        Node last = this.tail.getPrev();
        // add data to the end of the list
        last.setNext(added);
        added.setNext(tail);
        tail.setPrev(added);
        added.setPrev(last);

        size++;

        return true;
    }

    /**
     * Set the element for the node at the specified index
     * to data and return the element that was previously
     * stored in this node.
     * 
     * @param index position index
     * @param data  updated data
     * @return return the element that was previously
     *         stored in this node.
     */
    public E set(int index, E data) {
        if ( data == null ){
            throw new NullPointerException();
        }
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        // get the node at index position
        Node update = getNth(index);
        // get the element of the node
        E original = update.getElement();
        // change to new data
        update.setElement(data);

        return original;
    }

    /**
     * Remove the node from the specified index in
     * this list and return the data from the removed node.
     * 
     * @param index position index
     * @return return the data from the removed node.
     */
    public E remove(int index) {

        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        // get the node at index position
        Node removed = getNth(index);
        // get the element of the node
        E original = removed.getElement();
        // get the previous and next node of removed node
        Node previous = removed.getPrev();
        Node next = removed.getNext();
        // remove the node
        previous.setNext(next);
        next.setPrev(previous);

        
        size--;

        return original;
    }

    /**
     * Remove all (non-sentinel) nodes from the list.
     */
    public void clear() {
        /* Add your implementation here */
        this.head.setNext(tail);
        this.tail.setPrev(head);
        this.size = ZERO;
    }

    /**
     * Determine if the list is empty.
     * 
     * @return true for empty list
     */
    public boolean isEmpty() {
        // check if size is 0
        if (size == ZERO) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * returns the Node at a specified index
     * 
     * @param index position of index
     * @return the Node at a specified index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        // get the head of list
        Node current = this.head;

        for (int i = ZERO; i < this.size; i++) {
            current = current.getNext();
            // when at index position return node
            if (i == index) {
                return current;
            }
        }
        return current;
    }

    
}
