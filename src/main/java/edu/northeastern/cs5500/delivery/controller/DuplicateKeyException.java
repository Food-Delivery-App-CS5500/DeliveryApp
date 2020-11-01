package edu.northeastern.cs5500.delivery.controller;

public class DuplicateKeyException extends Exception {
    public DuplicateKeyException(String message) {
        super(message);
      }
}
