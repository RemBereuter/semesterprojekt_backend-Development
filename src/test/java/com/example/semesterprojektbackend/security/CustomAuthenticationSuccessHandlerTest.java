package com.example.semesterprojektbackend.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

class CustomAuthenticationSuccessHandlerTest {
    @Test
    void testOnAuthenticationSuccess() throws IOException, ServletException {
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof org.springframework.mock.web.MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("127.0.0.1", mockHttpServletRequest.getRemoteAddr());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertEquals("127.0.0.1", mockHttpServletRequest.getLocalAddr());
        assertTrue(
                mockHttpServletRequest.getInputStream() instanceof org.springframework.mock.web.DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    @Test
    void testOnAuthenticationSuccess2() throws IOException, ServletException {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setSession(new MockHttpSession());
        Response response = new Response();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    @Test
    void testAuthenticationSuccessHandler() throws IOException, ServletException {
        AuthenticationSuccessHandler actualAuthenticationSuccessHandlerResult = (new CustomAuthenticationSuccessHandler())
                .authenticationSuccessHandler();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        actualAuthenticationSuccessHandlerResult.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof org.springframework.mock.web.MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("127.0.0.1", mockHttpServletRequest.getRemoteAddr());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertEquals("127.0.0.1", mockHttpServletRequest.getLocalAddr());
        assertTrue(
                mockHttpServletRequest.getInputStream() instanceof org.springframework.mock.web.DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    @Test
    void testAuthenticationSuccessHandler2() throws IOException, ServletException {
        AuthenticationSuccessHandler actualAuthenticationSuccessHandlerResult = (new CustomAuthenticationSuccessHandler())
                .authenticationSuccessHandler();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setSession(new MockHttpSession());
        Response response = new Response();
        actualAuthenticationSuccessHandlerResult.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertTrue(actualAuthenticationSuccessHandlerResult instanceof CustomAuthenticationSuccessHandler);
    }
}

