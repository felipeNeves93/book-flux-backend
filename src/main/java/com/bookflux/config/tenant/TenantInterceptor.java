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
    if (path.equals("/register") || path.equals("/login")) {
      TenantContext.clear();
      return true;
    }

    var auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated() && ANONYMOUS_USER != auth.getPrincipal()) {
      var tenantId = auth.getName();
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

}
