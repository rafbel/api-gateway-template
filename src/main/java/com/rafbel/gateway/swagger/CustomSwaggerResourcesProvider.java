package com.rafbel.gateway.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.rafbel.gateway.enumerator.SwaggerRouteEnum;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 
 * @author Rafael Bellotti
 * @since 2019-08-20
 * 
 * Sets up custom swagger provider to show all microservices documentation
 *
 */
@Component
@Primary
@EnableAutoConfiguration
public class CustomSwaggerResourcesProvider implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		return Arrays.asList(
			swaggerResource("catalog", SwaggerRouteEnum.CATALOG.getValue(), "2.0")
		);
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource resource = new SwaggerResource();
		resource.setName(name);
		resource.setLocation(location);
		resource.setSwaggerVersion(version);
		return resource;
	}
}
