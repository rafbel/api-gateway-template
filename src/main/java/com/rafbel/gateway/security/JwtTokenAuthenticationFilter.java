package com.rafbel.gateway.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Class responsible for receiving filtering the received token,
 * parsing it and obtaining list of user authorities before continuing
 * the filter chain
 *
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtConfiguration jwtConfiguration;
	
	/**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Constructor sets security values to be used
	 *
	 */
	public JwtTokenAuthenticationFilter(JwtConfiguration jwtConfiguration) {
		this.jwtConfiguration = jwtConfiguration;
	}
	
	/**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Checks user authentication if token is provided before continuing the chain
	 *
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String header = request.getHeader(jwtConfiguration.getHeader());

		if(header == null || !header.startsWith(jwtConfiguration.getPrefix())) {
			chain.doFilter(request, response);
			return;
		}
		String token = header.replace(jwtConfiguration.getPrefix(), "");
		
		try {

			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfiguration.getSecret().getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			String username = claims.getSubject();
			if(username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");

				 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								 username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				 
				 SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		chain.doFilter(request, response);
	}


}
