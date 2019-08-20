package com.rafbel.gateway.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rafbel.gateway.enumerator.AuthenticatorRouteEnum;
import com.rafbel.gateway.enumerator.CatalogRouteEnum;
import com.rafbel.gateway.enumerator.SwaggerRouteEnum;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Configuration class for endpoint access
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityTokenConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfiguration jwtConfiguration;
	
	/**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Enables the use of swagger documentation for read-only purposes
	 *
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/v2/api-docs",
                     	 "/configuration/ui",
                         "/swagger-resources",
                         "/configuration/security",
                         "/swagger-ui.html",
                         "/webjars/**");
    }
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
    	   http
		.csrf().disable()
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and()
		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		   .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfiguration), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		   .antMatchers(HttpMethod.POST, jwtConfiguration.getUri(), AuthenticatorRouteEnum.USERS.getValue()).permitAll()
		   .antMatchers(HttpMethod.GET, CatalogRouteEnum.PRODUCTS.getValue(), CatalogRouteEnum.PRODUCTS_ID.getValue(), SwaggerRouteEnum.CATALOG.getValue()).permitAll()
		   .antMatchers(SwaggerRouteEnum.RESOURCES.getValue()).permitAll()
		   .anyRequest().authenticated(); 
	}
	
	/**
	 * 
	 * @author Rafael Bellotti
	 * @since 2019-08-20
	 * 
	 * Bean for obtaining security jwt values
	 *
	 */
	@Bean
  	public JwtConfiguration jwtConfig() {
	   return new JwtConfiguration();
  	}
}
