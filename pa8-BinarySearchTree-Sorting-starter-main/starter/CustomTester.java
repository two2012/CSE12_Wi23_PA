
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources: writeup
 * 
 * 
 * this is the tester for the MyBST class that implement tests
 * that can correctly distinguish between good and bad implementations.
 */
public class CustomTester {

    MyBST<Integer, Integer> tree;

    /**
     * this is the setup method that create a tree
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root = new MyBST.MyBSTNode<>(10, 1, null);
        MyBST.MyBSTNode<Integer, Integer> eight = new MyBST.MyBSTNode<>(8, 1, root);
        MyBST.MyBSTNode<Integer, Integer> fifteen = new MyBST.MyBSTNode<>(15, 22, root);
        MyBST.MyBSTNode<Integer, Integer> six = new MyBST.MyBSTNode<>(6, 2, eight);
        MyBST.MyBSTNode<Integer, Integer> nine = new MyBST.MyBSTNode<>(9, 30, eight);
        MyBST.MyBSTNode<Integer, Integer> eleven = new MyBST.MyBSTNode<>(11, 50, fifteen);
        MyBST.MyBSTNode<Integer, Integer> sixteen = new MyBST.MyBSTNode<>(16, 22, fifteen);
        MyBST.MyBSTNode<Integer, Integer> four = new MyBST.MyBSTNode<>(4, 48, six);
        MyBST.MyBSTNode<Integer, Integer> thirteen = new MyBST.MyBSTNode<>(13, 48, eleven);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(eight);
        root.setRight(fifteen);
        eight.setLeft(six);
        eight.setRight(nine);
        fifteen.setRight(sixteen);
        fifteen.setLeft(eleven);
        six.setLeft(four);
        eleven.setRight(thirteen);
        tree.size = 9;
    }

    /**
     * this is the test for successor method
     * 1. the node with right child
     * 2. the node with left child
     * 3. the node with no child
     */
    @Test
    public void testSuccessor() {
        // the node with right child
        MyBST.MyBSTNode<Integer, Integer> eleven = tree.root.getRight().getLeft();
        assertSame(eleven.getRight(), eleven.successor());
        assertEquals(9, tree.size);

        // the node with left child
        MyBST.MyBSTNode<Integer, Integer> six = tree.root.getLeft().getLeft();
        assertSame(six.getParent(), six.successor());
        assertEquals(9, tree.size);

        // the node with no child
        MyBST.MyBSTNode<Integer, Integer> thirteen = tree.root.getRight().getLeft().getRight();
        assertSame(thirteen.getParent().getParent(), thirteen.successor());
        assertEquals(9, tree.size);

        MyBST.MyBSTNode<Integer, Integer> four = tree.root.getLeft().getLeft().getLeft();
        assertSame(four.getParent(), four.successor());
        assertEquals(9, tree.size);

    }

    /**
     * this is the test for insert method with
     * 1. the key is null
     * 2. the key is already in the tree
     */
    @Test
    public void testInsert() {
        // the key is null
        try {
            tree.insert(null, 1);
            fail();
        } catch (NullPointerException e) {
            assertEquals(9, tree.size);
        }

        // the key is already in the tree
        int oldValue = tree.insert(10, 5);
        assertEquals(1, oldValue);
        assertEquals(9, tree.size);
        assertNull(tree.root.getParent());
        assertEquals(8, (int) tree.root.getLeft().getKey());
        assertEquals(15, (int) tree.root.getRight().getKey());

    }


    /**
     * this is the test for remove method with
     * 1. the key is null
     * 2. the key is not in the tree
     * 3. the node is the root
     * 4. the node has one child
     * 5. the node has two children
     *
     */
    @Test
    public void testRemove(){
        // the key is null
        assertNull(tree.remove(null));
        assertEquals(9, tree.size);

        // the key is not in the tree
        assertNull(tree.remove(100));
        assertEquals(9, tree.size);

        // the node is the root
        int oldValue = tree.remove(10);
        assertEquals(1, oldValue);
        assertEquals(8, tree.size);
        assertEquals(11, tree.root.getKey().intValue());
        assertEquals(13, tree.root.getRight().getLeft().getKey().intValue());
        
        // the node has one child
        oldValue = tree.remove(6);
        assertEquals(2, oldValue);
        assertEquals(7, tree.size);
        assertEquals(4, tree.root.getLeft().getLeft().getKey().intValue());
        assertEquals(8, tree.root.getLeft().getLeft().getParent().getKey().intValue());

        // the node has two children
        oldValue = tree.remove(15);
        assertEquals(22, oldValue);
        assertEquals(6, tree.size);
        assertEquals(16, tree.root.getRight().getKey().intValue());
        assertEquals(13, tree.root.getRight().getLeft().getKey().intValue());
        assertEquals(16, tree.root.getRight().getLeft().getParent().getKey().intValue());

    }


    /**
     * this is the test for inorder method with
     * 1. the tree is empty
     * 2. null
     */
    @Test
    public void testInorder(){
        // the tree is empty
        MyBST<Integer, Integer> emptyTree = new MyBST<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> emptyList = new ArrayList<>();
        assertEquals(emptyList, emptyTree.inorder());

        // null
        MyBST.MyBSTNode<Integer, Integer> nullNode = null;
        tree.root = nullNode;
        assertEquals(emptyList, tree.inorder());
       
    }


    /**
     * this is the test for search method with
     * 1. the key is null
     * 2. the key is not in the tree
     * 3. the key is in the tree
     */
    @Test
    public void testSearch(){
        // the key is null
        assertNull(tree.search(null));

        // the key is not in the tree
        assertNull(tree.search(100));
        assertEquals(9, tree.size);
        assertEquals(10, tree.root.getKey().intValue());

        // the key is in the tree
        assertEquals(22, tree.search(15).intValue());
        assertEquals(1, tree.search(10).intValue());
        assertEquals(1, tree.search(8).intValue());
        assertEquals(48, tree.search(4).intValue());
        assertEquals(30, tree.search(9).intValue());
        assertEquals(9, tree.size);
    }
}