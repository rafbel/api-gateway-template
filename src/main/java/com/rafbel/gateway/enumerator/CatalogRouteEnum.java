package com.rafbel.gateway.enumerator;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Enumerator for all catalog microservice routes
 *
 */
public enum CatalogRouteEnum {
	PRODUCTS("/api/catalog/products"),
	PRODUCTS_ID("/api/catalog/products/{product_id}");

	private String value;
	
	private CatalogRouteEnum(String value) { 
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
