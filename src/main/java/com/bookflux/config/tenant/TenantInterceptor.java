package com.bookflux.config.tenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class TenantInterceptor implements HandlerInterceptor {

  private static final String ANONYMOUS_USER = "anonymousUser";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    var path = request.getRequestURI();
    if (this.isTenantFree(path)) {
      TenantContext.clear();
      return true;
    }

    var auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated() && ANONYMOUS_USER != auth.getPrincipal()) {
      var tenantId = auth.getName();
      System.out.printf("TENANT ID: %s%n", tenantId);
      TenantContext.setTenantId(tenantId);
      return true;
    }

    return false;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    TenantContext.clear();
  }

  private boolean isTenantFree(String path) {
    return path.startsWith("/auth")
        || path.startsWith("/api/books");
  }

}
