package com.rafbel.gateway.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Sets cors filters that determines which methods, headers, and origins are allowed
 *
 */
@Component
@Order(-2000)
public class CorsFilterConfiguration implements Filter {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Overrides doFilter method, allowing any origin, all methods, and specific headers
	 *
	 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, remember-me, Authorization, Authentication");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Overrides init method so as to do nothing
	 *
	 */
    @Override
    public void init(FilterConfig filterConfig) {}

    /**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Overrides destroy method so as to do nothing
	 *
	 */
    @Override
    public void destroy() {}
    
}
