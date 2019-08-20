package com.rafbel.gateway.security;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Retrieves security jwt values from the local properties to be used
 * in application runtime
 *
 */
public class JwtConfiguration {

	@Value("${security.jwt.uri:/api/authenticator/login/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}
}
