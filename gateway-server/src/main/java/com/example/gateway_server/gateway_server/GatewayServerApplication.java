package com.example.gateway_server.gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.RouteMatcher;

@SpringBootApplication
public class GatewayServerApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes().
				route(p -> p
						.path("/payroll/leave/**")
						.filters(f -> f.rewritePath("/payroll/leave/(?<segment>.*)", "/${segment}"))
						.uri("lb://LEAVE")
				).
				build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
