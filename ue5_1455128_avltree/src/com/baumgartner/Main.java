package com.baumgartner;

import com.baumgartner.Exceptions.NodeAlreadyExistsException;
import com.baumgartner.avlTree.AVLTree;

public class Main {

    public static void main(String[] args) throws NodeAlreadyExistsException {
	// write your code here
        AVLTree avlTree = new AVLTree();

        avlTree.insert(1,"1");
        avlTree.insert(2,"2");
        avlTree.insert(3,"3");
        avlTree.insert(4,"4");
        avlTree.insert(5,"5");


        for(Object o : avlTree.toArray()){
            System.out.printf(o + " ");
        }
        System.out.println("Remove 5");
        avlTree.remove(5);

        for(Object o : avlTree.toArray()){
            System.out.printf(o + " ");
        }

        System.out.println("Remove 3");
        avlTree.remove(3);

        for(Object o : avlTree.toArray()){
            System.out.printf(o + " ");
        }


    }
}
