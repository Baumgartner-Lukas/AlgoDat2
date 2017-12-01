package com.baumgartner.Exceptions;

public class NodeAlreadyExistsException extends Exception{

    public NodeAlreadyExistsException(){}

    public NodeAlreadyExistsException(String message) {
        super(message);
    }
}
