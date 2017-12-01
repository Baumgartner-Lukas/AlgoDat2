package com.baumgartner;

import com.baumgartner.Exceptions.NodeAlreadyExistsException;
import com.baumgartner.avlTree.AVLTree;

public class Main {

    public static void main(String[] args) throws NodeAlreadyExistsException {
	// write your code here
        AVLTree avlTree = new AVLTree();

        avlTree.insert(1,"1");
        avlTree.insert(2,"1");
    }
}
