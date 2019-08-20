package com.rafbel.gateway.enumerator;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Enumerator for all authenticator microservice routes
 *
 */
public enum AuthenticatorRouteEnum {
	USERS("/api/authenticator/users");
	
	private String value;
	
	private AuthenticatorRouteEnum(String value) { 
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
