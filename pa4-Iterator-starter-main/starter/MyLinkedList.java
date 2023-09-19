
/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: PA4 Write-up
 * 
 * This file is a class that is a data structure 
 * similar to Java’s LinkedList with generic types 
 * and added a MyListIterator class which can use a
 * iterator loop, add, remove and set MyLinkedList
 */
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This file is a class that is a data structure
 * similar to Java’s ArrayLists with generic types
 * and added a MyListIterator class which can use a
 * iterator loop, add, remove and set MyLinkedList
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
     * create a new MyListIterator and return it.
     * @return a new MyListIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    /**
     * create a new MyListIterator and return it.
     * @return a new MyListIterator
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
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
        if (index < 0 || index >= size) {
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
        if (index < 0 || index > size) {
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
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
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

        if (index < 0 || index >= size) {
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
        if (index < 0 || index >= size) {
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

    protected class MyListIterator implements ListIterator<E> {

        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        // to avoid magic number
        private static final int ONE = 1;
        private static final int NONE = -1;

        /**
         * Constructor that creates an ListIterator and
         * initializes all the necessary variables.
         */
        public MyListIterator() {
            this.left = head;
            this.right = head.getNext();
            idx = 0;
            forward = true;
            canRemoveOrSet = false;
        }

        /**
         * Insert the given item into the list immediately before
         * the element that would be returned by next().
         * If we call previous() immediately following add,
         * the newly added item would be returned.
         * 
         * @param e element to add
         */
        @Override
        public void add(E e) {
            if (e == null) {
                throw new NullPointerException();
            }
            
            // create new node for added element
            Node newNode = new Node(e);
            // add new node
            left.setNext(newNode);
            newNode.setNext(right);
            right.setPrev(newNode);
            newNode.setPrev(left);
            left = newNode;
            canRemoveOrSet = false;
            size++;
            idx++;
            

        }

        /**
         * Return true if there is an element node when going in the forward
         * 
         * @return true if there is an element node when going in the forward
         */
        @Override
        public boolean hasNext() {
            //check if next element is null
            if (left.getNext().getElement() != null) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Return true if there is an element node
         * when going in the backward
         * 
         * @return true if there is an element node
         *         when going in the backward
         */
        @Override
        public boolean hasPrevious() {
            //check if previous element is null
            if (right.getPrev().getElement() != null) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Return the next element in the list when going forward,
         * and move the iterator forward by one node.
         * 
         * @return the element that at next position
         */
        @Override
        public E next() {
            Node rNode;
            // check if iterator at the end of the list
            if (right.getNext() == null) {
                throw new NoSuchElementException();
            } else {
                
                // move left and right one element forward
                left = right;
                right = right.getNext();
                // assign left to rNode
                rNode = left;
                canRemoveOrSet = true;
                forward = true;
                idx++;
            }
            return (E) rNode.getElement();
        }

        /**
         * Return the index of the element that would be returned
         * by a call to next(). Return the list size if at the
         * end of the list.
         * 
         * @return the index of the element
         */
        @Override
        public int nextIndex() {
            // check if it is at the end of the list
            if (right.getElement() == null){
                return size;
            } else {
                return idx;
            }
        }

        /**
         * Return the next element in the list when going backward,
         * and move the iterator backward by one node.
         * 
         * @return the element that at previous position
         */
        @Override
        public E previous() {
            Node rNode;
            // check if iterator at the startß of the list
            if (left.getPrev() == null) {
                throw new NoSuchElementException();
            } else {
                
                // move left and right one element backward
                right = left;
                left = left.getPrev();
                // assign right to rNode
                rNode = right;
                canRemoveOrSet = true;
                forward = false;
                idx--;
            }
            return (E) rNode.getElement();
        }

        /**
         * Return the index of the element that would be
         * returned by a call to previous().
         * Return -1 if at the start of the list.
         * 
         * @return the index of the element
         */
        @Override
        public int previousIndex() {
            if (left.getElement() == null){
                return NONE;
            } else {
                return idx - ONE;
            }
            
        }

        @Override
        public void remove() {
            // check if can remove
            if (!canRemoveOrSet){
                throw new IllegalStateException();
            } else {
                // check the move direction
                if (forward) {
                    // remove the element
                    left = left.getPrev();
                    left.setNext(right);
                    right.setPrev(left);
                    idx--;
                } else {
                    right = right.getNext();
                    left.setNext(right);
                    right.setPrev(left);
                }
                size--;
                canRemoveOrSet = false;
            }
            
        }

        /**
         * For the node returned by the most recent
         * next/previous call, replace its value
         * with the new value element
         * 
         * @param e the value of the element
         */
        @Override
        public void set(E e) {
            // check if the element is null
            if (e == null){
                throw new NullPointerException();
            // check if can remove
            } else if (!canRemoveOrSet){
                throw new IllegalStateException();
            } else {
                // check the move direction
                if (forward) {
                    left.setElement(e);
                } else {
                    right.setElement(e);
                }
            }
           
            canRemoveOrSet = false;

        }
    }
}