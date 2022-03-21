package com.example.semesterprojektbackend.security;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;

class JsonUsernamePasswordAuthenticationFilterTest {
    @Test
    void testAttemptAuthentication() throws AuthenticationException {
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertThrows(InternalAuthenticationServiceException.class,
                () -> jsonUsernamePasswordAuthenticationFilter.attemptAuthentication(request, new Response()));
    }

    @Test
    void testAttemptAuthentication2() throws AuthenticationException {
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter();
        MockHttpServletRequest request = new MockHttpServletRequest("https://example.org/example",
                "https://example.org/example");

        assertThrows(InternalAuthenticationServiceException.class,
                () -> jsonUsernamePasswordAuthenticationFilter.attemptAuthentication(request, new Response()));
    }
}

