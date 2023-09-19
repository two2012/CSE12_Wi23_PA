/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: PA3 Write-up
 * 
 * This is a custom tester file for PA3. It contains test cases 
 * for the MyLinkedList and Node classes.
 */
/**
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of MyLinkedList. You will only receive points
 * if your test passes when run on a good implementation and fails for the
 * corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This is a custom tester file for PA3. It contains test cases
 * for the MyLinkedList and Node classes.
 */
public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<String> stringList;
	private MyLinkedList<String> emptyList;
	private String[] strData = { "AAA", "BBB", "CCC", "DDD" };
	

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyList = new MyLinkedList<>();
		stringList = new MyLinkedList<>();
		MyLinkedList<String>.Node node0 = stringList.new Node(strData[0]);
		MyLinkedList<String>.Node node1 = stringList.new Node(strData[1]);
		MyLinkedList<String>.Node node2 = stringList.new Node(strData[2]);
		MyLinkedList<String>.Node node3 = stringList.new Node(strData[3]);

		stringList.head.setNext(node0);
		node0.setPrev(stringList.head);
		node0.next = node1;
		node1.prev = node0;
		node1.next = node2;
		node2.prev = node1;
		node2.next = node3;
		node3.prev = node2;
		node3.next = stringList.tail;
		stringList.tail.prev = node3;
		stringList.size = 4;

		
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		// TODO: add your test here
		// { "AAA", "BBB", "CCC", "DDD" }
		boolean ret1 = stringList.add("888");
		boolean ret2 = stringList.add("777");
		boolean ret3 = stringList.add("666");
		
		assertEquals(7, stringList.size());

		assertEquals(true, ret1);
		assertEquals(true, ret2);
		assertEquals(true, ret3);

		assertEquals("888", stringList.getNth(4).data);
		assertEquals("777", stringList.getNth(5).data);
		assertEquals("666", stringList.getNth(6).data);

		assertEquals("666", stringList.tail.prev.data);

		assertEquals("DDD", stringList.getNth(4).prev.data);
		assertEquals("777", stringList.getNth(4).next.data);
		assertEquals("888", stringList.getNth(5).prev.data);
		assertEquals("666", stringList.getNth(5).next.data);
		assertEquals("777", stringList.getNth(6).prev.data);
		assertEquals(null, stringList.getNth(6).next.data);





		
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		// TODO: add your test here
		//// { "AAA", "BBB", "CCC", "DDD" }

		stringList.add(0,"111");
		stringList.add(0,"222");
		stringList.add(0,"333");

		assertEquals(7, stringList.size());

		assertEquals("333", stringList.getNth(0).data);
		assertEquals("222", stringList.getNth(1).data);
		assertEquals("111", stringList.getNth(2).data);

		assertEquals("333", stringList.head.next.data);

		assertEquals(null, stringList.getNth(0).prev.data);
		assertEquals("222", stringList.getNth(0).next.data);
		assertEquals("333", stringList.getNth(1).prev.data);
		assertEquals("111", stringList.getNth(1).next.data);
		assertEquals("222", stringList.getNth(2).prev.data);
		assertEquals("AAA", stringList.getNth(2).next.data);


		
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		// TODO: add your test here
		// { "AAA", "BBB", "CCC", "DDD" }
		stringList.add(2,"fff");
		stringList.add(3,"ttt");
		stringList.add(4,"sss");

		assertEquals(7, stringList.size());
		assertEquals("fff", stringList.get(2));
		assertEquals("ttt", stringList.get(3));
		assertEquals("sss", stringList.get(4));

		assertEquals("BBB", stringList.getNth(2).prev.data);
		assertEquals("ttt", stringList.getNth(2).next.data);
		assertEquals("fff", stringList.getNth(3).prev.data);
		assertEquals("sss", stringList.getNth(3).next.data);
		assertEquals("ttt", stringList.getNth(4).prev.data);
		assertEquals("CCC", stringList.getNth(4).next.data);
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomRemoveFromEmpty() {
		// TODO: add your test here
		
		emptyList.remove(0);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		// // { "AAA", "BBB", "CCC", "DDD" }
		String removed1 = stringList.remove(2);
		String removed2 = stringList.remove(2);

		assertEquals("CCC", removed1);
		assertEquals("DDD", removed2);
		assertEquals(2, stringList.size());
		assertEquals("BBB", stringList.tail.prev.data);
		
		
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomSetIdxOutOfBounds() {
		// TODO: add your test here
		// // { "AAA", "BBB", "CCC", "DDD" }
		stringList.set(9, "UUU");
	}
}
