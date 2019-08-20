package com.rafbel.gateway.enumerator;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Enumerator for all swagger api document routes
 *
 */
public enum SwaggerRouteEnum {
	RESOURCES("/swagger-resources/**"),
	CATALOG("/api/catalog/v2/api-docs");

	private String value;
	
	private SwaggerRouteEnum(String value) { 
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
