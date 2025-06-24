package com.bookflux.exception;

public class ObjectNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Object not found with id: ";

  public ObjectNotFoundException(String id, String objectName) {
    super(DEFAULT_MESSAGE + objectName + ":" + id);
  }
}
