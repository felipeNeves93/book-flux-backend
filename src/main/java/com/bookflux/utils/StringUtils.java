package com.bookflux.utils;

import java.util.UUID;

public final class StringUtils {

  private StringUtils() {
  }

  public static String generateUUID() {
    return UUID.randomUUID().toString();
  }

}
