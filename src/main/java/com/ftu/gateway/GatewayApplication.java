package com.ftu.gateway;

import com.ftu.gateway.filter.AuthenticationAndAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public AuthenticationAndAuthorizationFilter authenticationAndAuthorizationFilter() {
        return new AuthenticationAndAuthorizationFilter();
    }


}
