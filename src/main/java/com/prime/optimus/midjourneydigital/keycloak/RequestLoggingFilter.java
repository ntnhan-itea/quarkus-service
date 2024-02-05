package com.prime.optimus.midjourneydigital.keycloak;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class RequestLoggingFilter implements ClientRequestFilter, ContainerRequestFilter {
    // TODO: this test for testing only - show log of call URL request

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
//         Log the full URL
//        String url = requestContext.getUri().toString();
//        System.out.println("Request URL: " + url);

        // Log specific headers, e.g., Authorization header (Token)
//        String token = requestContext.getHeaderString("Authorization");
//        System.out.println("Authorization Token: " + token);

        // Alternatively, log all headers
//        requestContext.getHeaders().forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Log the full URL
//        String url = requestContext.getUriInfo().getAbsolutePath().toString();
//        System.out.println("Request URL: " + url);

        // Log specific headers, e.g., Authorization header (Token)
//        String token = requestContext.getHeaderString("Authorization");
//        System.out.println("Authorization Token: " + token);

        // Alternatively, log all headers
//        requestContext.getHeaders().forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
