package com.http.filter;

import com.http.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.http.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGES, LOCALE);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if ( isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse)servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        Object user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }
}
