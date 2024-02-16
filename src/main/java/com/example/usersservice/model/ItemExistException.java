package com.example.usersservice.model;

public class ItemExistException extends RuntimeException {

  public ItemExistException(final String msg) {
    super(msg);
  }
}
