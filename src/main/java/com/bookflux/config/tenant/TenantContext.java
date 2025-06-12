package com.bookflux.config.tenant;

public class TenantContext {

  private TenantContext() {
  }

  private static final ThreadLocal<String> tenantId = new ThreadLocal<>();

  public static void setTenantId(String id) {
    tenantId.set(id);
  }

  public static String getTenantId() {
    return tenantId.get();
  }

  public static void clear() {
    tenantId.remove();
  }

}
