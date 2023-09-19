import java.util.ArrayList;

/**
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources: writeup
 * 
 * 
 * This file contains the implementation of the MyBST class.
 * this class is a binary search tree that stores key-value pairs.
 * with insert, search, remove, successor, and size methods.
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * Return the size of the tree
     * 
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * insert a new node into the tree
     * 
     * @param key   the key of the new node
     * @param value the value of the new node
     * @return the value of the new node
     */
    public V insert(K key, V value) {
        // if key is null, throw NullPointerException
        if (key == null) {
            throw new NullPointerException();
        }

        // if the tree is empty, create a new node as the root
        if (root == null) {
            root = new MyBSTNode<K, V>(key, value, null);
            size++;
            return null;
        }

        MyBSTNode<K, V> currentNode = root;
        MyBSTNode<K, V> parentNode = null;

        while (currentNode != null) {
            // compare the key with the key of the current node
            int compareResult = key.compareTo(currentNode.getKey());

            // if the key is equal to the key of the current node,
            // replace the value of the current node with the new value
            if (compareResult == 0) {
                V oldValue = currentNode.getValue();
                currentNode.setValue(value);
                return oldValue;

            } else if (compareResult < 0) {
                // if the key is less than the key of the current node,
                // go to the left subtree
                parentNode = currentNode;
                currentNode = currentNode.getLeft();

            } else {
                // if the key is greater than the key of the current node,
                // go to the right subtree
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }

        // create a new node and insert it as a child of the current node
        MyBSTNode<K, V> newNode = new MyBSTNode<K, V>(key, value, parentNode);
        // check if the new node is a left child or a right child
        if (key.compareTo(parentNode.getKey()) < 0) {
            parentNode.setLeft(newNode);
        } else {
            parentNode.setRight(newNode);
        }
        size++;
        return null;
    }

    /**
     * Search for a node with the specified key
     * 
     * @param key the key to search for
     * @return the value of the node with the specified key
     */
    public V search(K key) {
        // check if the key is null
        if (key == null) {
            return null;
        }
        // get the root node
        MyBSTNode<K, V> currentNode = root;
        // traverse the tree
        while (currentNode != null) {
            // compare the key with the key of the current node
            int compareResult = key.compareTo(currentNode.getKey());

            // if the key is equal to the key of the current node,
            // return the value of the current node
            if (compareResult == 0) {
                return currentNode.getValue();

            } else if (compareResult < 0) {
                // if the key is less than the key of the current node,
                // go to the left subtree
                currentNode = currentNode.getLeft();
            } else {
                // if the key is greater than the key of the current node,
                // go to the right subtree
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    /**
     * Remove a node with the specified key
     * 
     * @param key the key of the node to remove
     * @return the value of the removed node
     */
    public V remove(K key) {
        // check if the key is null
        if (key == null) {
            return null;
        }
        // get the root node
        MyBSTNode<K, V> currentNode = root;
        MyBSTNode<K, V> parentNode = null;
        V oldValue = null;
        // traverse the tree
        while (currentNode != null) {
            // compare the key with the key of the current node
            int compareResult = key.compareTo(currentNode.getKey());

            // if the key is equal to the key of the current node,
            // remove the current node
            if (compareResult == 0) {
                oldValue = currentNode.getValue();
                // if the current node has zero or one child
                if (currentNode.getLeft() == null ||
                        currentNode.getRight() == null) {
                    // get the child of the current node
                    MyBSTNode<K, V> childOfCurrent 
                            = currentNode.left != null ? 
                            currentNode.left : currentNode.right;

                    if (parentNode == null) {
                        // if the current node is the root node
                        root = childOfCurrent;
                    } else if (parentNode.getLeft() == currentNode) {
                        // if the current node is a left child
                        parentNode.setLeft(childOfCurrent);
                    } else {
                        // if the current node is a right child
                        parentNode.setRight(childOfCurrent);
                    }

                    if (childOfCurrent != null) {
                        // set the parent of the child of the current node
                        childOfCurrent.setParent(parentNode);
                    }

                    size--;
                    return oldValue;

                } else {
                    // the current node has two children
                    // find the successor of the current node
                    MyBSTNode<K, V> successor = currentNode.successor();

                    // replace the key and value of the current node
                    // with the key and value of the successor
                    currentNode.setKey(successor.getKey());
                    currentNode.setValue(successor.getValue());

                    // remove the successor
                    // if the successor is a right child
                    if (successor.getParent().getRight() == successor) {
                        successor.getParent().setRight(null);
                        successor.setParent(null);

                    } else {
                        // if the successor is a left child
                        // if the successor has a right child
                        if (successor.getRight() != null) {
                            successor.getRight()
                                .setParent(successor.getParent());
                            successor.getParent()
                                .setLeft(successor.getRight());
                        } else {
                            // if the successor does not have a right child
                            successor.getParent().setLeft(null);
                            successor.setParent(null);
                        }
                    }
                    size--;
                    return oldValue;
                }

            } else if (compareResult < 0) {
                // if the key is less than the key of the current node,
                // go to the left subtree
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
            } else {
                // if the key is greater than the key of the current node,
                // go to the right subtree
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }

        return oldValue;
    }

    /**
     * return an ArrayList of MyBSTNode in order
     * 
     * @return an ArrayList of MyBSTNode in order
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        // create an ArrayList to store the nodes
        ArrayList<MyBSTNode<K, V>> list = new ArrayList<MyBSTNode<K, V>>();
        // check if the tree is empty
        if (root == null) {
            return list;
        } else {

            // get the root node
            MyBSTNode<K, V> currentNode = root;
            // find the leftmost node
            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }

            // add the leftmost node to the ArrayList
            list.add(currentNode);

            while (currentNode.successor() != null) {
                // add the successor of the current node to the ArrayList
                currentNode = currentNode.successor();
                list.add(currentNode);
            }

            return list;
        }

    }

    /**
     * this is the inner class of MyBSTNode
     * and it is used to store the data of the tree
     */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * This method returns the successor of the current node.
         * 
         * @return the successor of the current node
         */
        public MyBSTNode<K, V> successor() {
            // check if the node has right subtree
            if (this.getRight() != null) {
                // find the leftmost node in the right subtree
                MyBSTNode<K, V> node = this.getRight();
                while (node.getLeft() != null) {
                    node = node.getLeft();
                }
                return node;
            } else {
                // find the first ancestor that is a left child
                MyBSTNode<K, V> node = this;
                MyBSTNode<K, V> ancester = this.getParent();
                while (ancester != null && ancester.getLeft() != node) {
                    node = ancester;
                    ancester = ancester.getParent();
                }
                return ancester;
            }
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? 
                    comp.getKey() == null : this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? 
                    comp.getValue() == null : this.getValue()
                    .equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
