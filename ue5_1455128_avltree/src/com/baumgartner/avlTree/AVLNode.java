package com.baumgartner.avlTree;

public class AVLNode {
    public AVLNode parent;
    public AVLNode left;
    public AVLNode right;
    public Integer key;
    public String data;

    public int balanceFactor; //

    public int height = 0; // To determine node height in O(1)

    public AVLNode(Integer key, String elem) {
        this.key = key;
        this.data = elem;
    }

    public AVLNode(Integer key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key + " " + data;
    }
}
