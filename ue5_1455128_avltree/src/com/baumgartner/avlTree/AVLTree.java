package com.baumgartner.avlTree;

import com.baumgartner.Exceptions.NodeAlreadyExistsException;

public class AVLTree {
    protected AVLNode root;
    protected int size;

    /**
     * Default constructor.
     * Initializes the AVL tree.
     */
    public AVLTree() {
        this.root = null;
        size = 0;
    }

    /**
     * Retrieves tree height.
     *
     * @return -1 in case of empty tree, current tree height otherwise.
     */
    public int height() {
        return -1;
    }

    /**
     * Inserts a new node into AVL tree.
     *
     * @param key  Key of the new node.
     * @param data Data of the new node. May be null.
     */
    public void insert(Integer key, String data) throws IllegalArgumentException, NodeAlreadyExistsException {
        AVLNode n = new AVLNode(key, data);
        if(key == null || data.isEmpty()) throw new IllegalArgumentException("New nodes must not be empty");
        if(!(find(n.key).equals("Not found"))) throw new NodeAlreadyExistsException("This node already exists!");
        if(root == null) {
            root = new AVLNode(key, data);
        }else{
            insert(root, new AVLNode(key, data));
        }
    }



    /**
     * Removes the first node with given key.
     *
     * @param key Key of node to remove.
     * @return true If element was found and deleted.
     */
    public boolean remove(Integer key) throws IllegalArgumentException {
        return false;
    }

    /**
     * Returns value of a first found node with given key.
     *
     * @param key Key to search.
     * @return Corresponding value if key was found, null otherwise.
     */
    public String find(Integer key) throws IllegalArgumentException {
            AVLNode current = root;
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.data;
                }
                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return "Not found";
        }

    /**
     * Yields number of key/value pairs in the tree.
     *
     * @return Number of key/value pairs.
     */
    public int size() {
        return size;
    }

    /**
     * Yields an array representation of the data elements (in-order).
     *
     * @return Array representation of the tree.
     */
    public Object[] toArray() {
        return null;
    }

    //---------- private methods ----------

    /**
     * Adds element to the tree
     * @param root parent element of new node
     * @param newNode  new node that gets insertet into the AVL tree
     */
    private void insert(AVLNode root, AVLNode newNode) {
    }

    /**
     * Implements cut & link restructure operation.
     * @param n Node to start restructuring with.
     */
    private void restructure (AVLNode n) {
    }

    /**
     * Calculates the height of a node.
     * @param node node to calculate height of
     */
    private void updateHeights(AVLNode node){
    }

    private int nodeHeight(AVLNode node) {
        return -1;
    }

    /**
     * Rebalances a node
     * @param node node to be balanced
     */
    private void rebalance(AVLNode node) {
    }

}
