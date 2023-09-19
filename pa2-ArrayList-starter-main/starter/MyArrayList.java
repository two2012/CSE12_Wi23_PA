/* 
  Name: Wen Guo
  Email: w5guo@ucsd.edu
  PID: A17630856
  Sources Used: PA2 Write-up
   
  This file is a class that is a data structure 
  similar to Java’s ArrayLists with generic types
*/

/**
 * this is a class that is a data structure
 * similar to Java’s ArrayLists with generic types
 */
public class MyArrayList<E> implements MyList<E> {

    // Instance Variables for MyArrayList
    Object[] data;
    int size;

    // to avoid magic numbers
    private static final int FIVE = 5;
    private static final int THREE = 3;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    /**
     * Construct a new instance of MyArrayList.
     */
    public MyArrayList() {
        this.data = new Object[FIVE];
        this.size = 0;
    }

    /**
     * Construct a new instance of MyArrayList with inital capacity.
     * 
     * @param initialCapacity initial capacity
     */
    public MyArrayList(int initialCapacity) {
        // check if inital capacity is less than 0
        if (initialCapacity < ZERO) {
            throw new IllegalArgumentException();
        } else {
            this.data = new Object[initialCapacity];
            this.size = ZERO;
        }

    }

    /**
     * Construct a new instance of MyArrayList copy from array that provide.
     * 
     * @param arr that copy from
     */
    public MyArrayList(E[] arr) {
        // check if arr is null
        if (arr == null) {
            this.data = new Object[FIVE];
            this.size = ZERO;
        } else {
            this.data = arr;
            this.size = arr.length;
        }

    }

    /**
     * Add an element to the end of the list
     *
     * @param element the element to append
     */
    @Override
    public void append(E element) {
        // if size is 0 assign element to the first element in array
        // then icrease size by 1
        if (size == ZERO) {
            data[ZERO] = element;
            size++;

        } else {
            // if size is at capacity expand capacity
            if (size == this.getCapacity()) {
                this.expandCapacity(getCapacity() + ONE);
            }

            // assign element next to last element and increase the size
            data[size] = element;
            size++;
        }

    }

    /**
     * Increase the capacity of underlying array if needed
     *
     * @param requiredCapacity minimum capacity after expanding
     */
    @Override
    public void expandCapacity(int requiredCapacity) {

        int currentCapacity = data.length;
        // requiredCapacity is strictly less than the initial capacity
        if (requiredCapacity < currentCapacity) {
            throw new IllegalArgumentException();
        }

        // current capacity is 0
        if (currentCapacity == ZERO) {
            // required capacity less than or equal to 5
            if (requiredCapacity <= FIVE) {
                this.data = new Object[FIVE];

                // required capacit greater than 5
            } else {
                this.data = new Object[requiredCapacity];

            }

            // current capacity is not 0
        } else {
            // current capacity is greater than or equal to required capacity
            if (currentCapacity + THREE >= requiredCapacity) {
                // create a temp array with appropriate capacity
                Object[] temArray = new Object[currentCapacity + THREE];
                // copy elements from data to temp array
                for (int i = ZERO; i < size; i++) {
                    temArray[i] = data[i];
                }
                // assign data to capacity expended array
                this.data = temArray;

                // current capacity is less than required capacity
            } else {
                // create a temp array with appropriate capacity
                Object[] temArray = new Object[requiredCapacity];
                // copy elements from data to temp array
                for (int i = ZERO; i < size; i++) {
                    temArray[i] = data[i];
                }
                // assign data to capacity expended array
                this.data = temArray;

            }
        }
    }

    /**
     * Get the element at the given index
     *
     * @param index the index at which to return the element
     * @return the element at the index
     */
    @Override
    public E get(int index) {
        // if index invalid throw an exception
        if (index < ZERO || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (E) data[index];
    }

    /**
     * Get the amount of elements array can hold
     *
     * @return number of elements that can be held
     */
    @Override
    public int getCapacity() {

        return data.length;
    }

    /**
     * Add an element at the specified index
     *
     * @param index   position to insert the element
     * @param element the element to insert
     */
    @Override
    public void insert(int index, E element) {
        // index is invalid
        if (index < ZERO || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // size is 0
        if (size == ZERO) {
            // element will be only element in the array
            data[ZERO] = element;
            // increasing size by 1
            size++;

        } else {

            // the array is at capacity
            if (size == getCapacity()) {

                expandCapacity(size + ONE);
            }

            // create a temp array with same capacity as data
            Object[] temArray = new Object[getCapacity()];
            for (int i = ZERO; i <= size; i++) {
                // before index copy form data to temp array
                if (i < index) {
                    temArray[i] = data[i];
                }
                // at index insert the element
                if (i == index) {
                    temArray[i] = element;
                }
                // after index copy the rest element to temp array
                if (i > index) {
                    temArray[i] = data[i - ONE];
                }
            }

            data = temArray;
            size++;

        }

    }

    /**
     * Add an element to the beginning of the list
     *
     * @param element the element to prepend
     */
    @Override
    public void prepend(E element) {
        // if size is 0 assign element to the first element in array
        // then icrease size by 1
        if (size == 0) {
            data[ZERO] = element;
            size++;

        } else {
            // if size is at capacity expand capacity
            if (size == this.getCapacity()) {
                this.expandCapacity(getCapacity() + ONE);
            }
            // create a temp array
            Object[] temArray = new Object[this.getCapacity()];

            for (int i = ZERO; i <= size; i++) {
                // when i is 0 put element to the temp array
                if (i == ZERO) {
                    temArray[i] = element;
                } else {
                    // copy the data to temp array
                    temArray[i] = data[i - ONE];
                }
            }
            // assign temp array to data and inrease size by 1
            data = temArray;
            size++;
        }

    }

    /**
     * Remove the element at the specified index and return the removed element
     *
     * @param index the index at which to remove the element
     * @return the removed element
     */
    @Override
    public E remove(int index) {
        // if index invalid throw an exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // create a array with the same capacity with data
        Object[] temArray = new Object[getCapacity()];
        for (int i = ZERO; i < size - ONE; i++) {
            // before index copy data to temp array
            if (i < index) {
                temArray[i] = data[i];
            }
            // skip the element at index
            if (i >= index) {
                temArray[i] = data[i + ONE];
            }
        }

        data = temArray;
        // get the element at index
        Object removed = data[index];
        size--;

        return (E) removed;
    }

    /**
     * Replaces an element at the specified index with a new element and return
     * the original element
     *
     * @param index   the index at which to replace
     * @param element the element with which to replace
     * @return the original element
     */
    @Override
    public E set(int index, E element) {
        // if index invalid throw an exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // get the element to be replaced
        Object replaced = data[index];
        // create a array with the same capacity with data
        Object[] temArray = new Object[getCapacity()];
        for (int i = ZERO; i < size; i++) {
            // put element to index location of the array
            if (i == index) {
                temArray[i] = element;
            } else {
                // copy other elements from data to temp array
                temArray[i] = data[i];
            }
        }
        data = temArray;
        return (E) replaced;
    }

    /**
     * Get the number of elements in the list
     *
     * @return number of elements in the list
     */
    @Override
    public int size() {

        return this.size;
    }

}