package com.baumgartner.avlTree;

import com.baumgartner.Exceptions.NodeAlreadyExistsException;

public class AVLTree {
    protected AVLNode root;
    protected int size; //number of nodes in the tree

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
        if (root == null) return -1;
        return root.height;
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
        return null;
    }

    /**
     * Inserts a new node into AVL tree.
     *
     * @param key  Key of the new node.
     * @param data Data of the new node. May be null.
     */
    public void insert(Integer key, String data) throws IllegalArgumentException, NodeAlreadyExistsException {
        AVLNode n = new AVLNode(key, data);
        if (key == null || data.isEmpty()) throw new IllegalArgumentException("New nodes must not be empty");
        if (!(find(n.key) == null)) throw new NodeAlreadyExistsException("This node already exists!");
        if (root == null) {
            root = n;
            size++;
        } else {
            root = insert(root, n);
            size++;
        }
    }


    /**
     * Removes the first node with given key.
     *
     * @param key Key of node to remove.
     * @return true If element was found and deleted.
     */
    public boolean remove(Integer key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key must not be empty");
        if (find(key) != null) {
            root = remove(root, key);
            size--;
            return true;
        }
        return false;
    }


    /**
     * Yields an array representation of the data elements (in-order).
     *
     * @return Array representation of the tree.
     */
    public Object[] toArray() {
        Object[] oArray = new Object[size];
        toArrayInOrder(oArray, 0, root);
        return oArray;
    }



    //---------- private methods ----------

    /**
     * Adds element to the tree
     *
     * @param node    parent element of new node
     * @param newNode new Node that gets inserted into the AVL tree
     */
    private AVLNode insert(AVLNode node, AVLNode newNode) {
        if (node == null) return newNode;
        if (newNode.key.compareTo(node.key) < 0) {
            node.left = insert(node.left, newNode);
        } else {
            node.right = insert(node.right, newNode);
        }

        update(node);
        return balance(node);
    }

    private AVLNode remove(AVLNode node, Integer key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            // if node has two leafs attached
            if (node.left == null) {
                //if node has a leaf only on the left
                return node.right;
            } else if (node.right == null) {
                //if node has a leaf only on the right
                return node.left;
            } else{
                if(node.left.height > node.right.height){
                    int parentVal = maxNode(node.left);
                    node.key = parentVal;
                    node.left = remove(node.left, parentVal);
                }else{
                    int parentVal = minNode(node.right);
                    node.key = parentVal;
                    node.right = remove(node.right, parentVal);
                }
            }
        }
        update(node);
        return balance(node);
    }

    /**
     * Update the height of a node. Update the balance of a node
     *
     * @param node node to calculate height and balance of
     */
    private void update(AVLNode node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        //update height
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        // update balanceFactor
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    /**
     * @param node Rebalances the node if the balance factor is greater 1 or smaller -1
     * @return returns the re-balanced node
     */
    private AVLNode balance(AVLNode node) {
        //more node on the left
        if (node.balanceFactor == -2) {
            if (node.left.balanceFactor <= 0) {
                //rotate left-left
                return rotateLeftLeft(node);
            } else {
                //rotate left-right
                return rotateLeftRight(node);
            }
        } else if (node.balanceFactor == +2) {
            if (node.right.balanceFactor >= 0) {
                //rotate right-right
                return rotateRightRight(node);
            } else {
                //rotate right-left
                return rotateRightLeft(node);
            }
        }
        //Node is balanced
        return node;
    }

    private AVLNode rotateLeftLeft(AVLNode node) {
        return rotateRight(node);
    }

    private AVLNode rotateLeftRight(AVLNode node) {
        node.left = rotateLeft(node);
        return rotateLeftLeft(node);
    }

    private AVLNode rotateRightRight(AVLNode node) {
        return rotateLeft(node);
    }

    private AVLNode rotateRightLeft(AVLNode node) {
        node.right = rotateRight(node);
        return rotateRightRight(node);
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Integer minNode(AVLNode node) {
        if (node.left == null) return node.key;
        else {
            return minNode(node.left);
        }
    }

    private Integer maxNode(AVLNode node){
        if(node.right == null) return node.key;
        else{
            return maxNode(node.right);
        }
    }

    private int toArrayInOrder(Object[] ret, int offset, AVLNode n) {
        if (n.left != null) {
            offset = toArrayInOrder(ret, offset, n.left);
        }
        ret[offset++] = n.data;
        if (n.right != null) {
            offset = toArrayInOrder(ret, offset, n.right);
        }
        return offset;
    }

}

