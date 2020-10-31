package edu.northeastern.cs5500.delivery.controller;

/*
 * Generic Exception message class for all exceptions in Controller
*/
public class ExceptionClass extends Exception {

    private static final long serialVersionUID = -5155772275370417762L;
    private static String exceptionMessage;

    public ExceptionClass(String message) {
        super(message + "\n" + exceptionMessage);
    }
}
