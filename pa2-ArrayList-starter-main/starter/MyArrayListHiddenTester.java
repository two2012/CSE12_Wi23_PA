import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!

    Object[] arrNull = null;
    Integer[] arrValid = { 1, null, null, null, null, null };
    Integer[] arrEmpty = {};
    Integer[] arrAtCapacity = { 1, 2, 3, 4, 5 };
    Integer[] arr = { 1, 99, 100 };

    private MyArrayList testArrayList, invalidInitalCapacity, nullArrInput,
            validArrInput, emptyArrInput, atCapacityInput;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() throws Exception {

        nullArrInput = new MyArrayList<>(arrNull);
        validArrInput = new MyArrayList<>(arrValid);
        emptyArrInput = new MyArrayList<>(arrEmpty);
        atCapacityInput = new MyArrayList<>(arrAtCapacity);
        testArrayList = new MyArrayList<>(arr);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg() {

        invalidInitalCapacity = new MyArrayList<String>(-1);

    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg() {

        assertEquals(5, nullArrInput.getCapacity());
        assertEquals(0, nullArrInput.size);

    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull() {

        assertEquals(6, validArrInput.size);
        assertEquals(6, validArrInput.getCapacity());

    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity() {
        atCapacityInput.append(10);
        assertEquals(6, atCapacityInput.size);
        assertEquals(8, atCapacityInput.getCapacity());

    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull() {
        atCapacityInput.append(null);
        assertEquals(6, atCapacityInput.size);
        assertEquals(8, atCapacityInput.getCapacity());

    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity() {
        atCapacityInput.prepend(10);
        assertEquals(6, atCapacityInput.size);
        assertEquals(8, atCapacityInput.getCapacity());
    }

    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull() {
        atCapacityInput.prepend(null);
        assertEquals(6, atCapacityInput.size);
        assertEquals(8, atCapacityInput.getCapacity());
        assertArrayEquals(new Integer[] { null, 1, 2, 3, 4, 5, null, null }, atCapacityInput.data);

    }

    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBound() {

        testArrayList.insert(-1, 10);
        testArrayList.insert(9, 10);

    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple() {

        for (int i = 0; i < 1000; i++) {
            testArrayList.insert(1, i);
        }

        assertEquals(1005, testArrayList.getCapacity());
        assertEquals(1003, testArrayList.size);

    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound() {

        testArrayList.get(-1);
        testArrayList.get(9);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound() {

        testArrayList.set(-1, 5);
        testArrayList.set(9, 8);
        testArrayList.set(3, 8);

    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple() {
        for (int i = 4; i > 0; i--) {
            atCapacityInput.remove(i);
        }

        assertEquals(5, atCapacityInput.getCapacity());
        assertEquals(1, atCapacityInput.size);

    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound() {

        testArrayList.remove(-1);
        testArrayList.remove(9);

    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandCapacitySmaller() {

        testArrayList.expandCapacity(1);

    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge() {

        testArrayList.expandCapacity(7);
        assertEquals(7, testArrayList.getCapacity());

    }

}
