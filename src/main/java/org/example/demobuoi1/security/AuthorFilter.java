package org.example.demobuoi1.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession(false); // false means don't create a session if it doesn't exist
        String quyen = (session!=null)?(String) session.getAttribute("quyen"):null;
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.startsWith("/error")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/login")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/index")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/trang-chu")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/logout")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/ban-hang")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("/ban-hang/add-to-cart")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(requestURI.startsWith("hoa-don/edit/**")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(quyen == null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error");
            return;
        }
        if(requestURI.startsWith("/hoa-don/index") && (quyen == null || !quyen.equals("admin"))){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error");
            return;
        }
        if(requestURI.startsWith("/hoa-don-chi-tiet/index") && (quyen == null || !quyen.equals("admin"))){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error");
            return;
        }
        if (requestURI.endsWith(".jpg") || requestURI.endsWith(".jpeg") || requestURI.endsWith(".png") || requestURI.endsWith(".gif")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
