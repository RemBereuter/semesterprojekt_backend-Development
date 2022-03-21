package com.example.semesterprojektbackend.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.repositories.UserRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

class WebSecurityConfigTest {
    @Test
    void testAuthenticationSuccessHandler() throws IOException, ServletException {
        AuthenticationSuccessHandler actualAuthenticationSuccessHandlerResult = (new WebSecurityConfig(
                new CustomUserDetailService(mock(UserRepo.class)))).authenticationSuccessHandler();
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
        AuthenticationSuccessHandler actualAuthenticationSuccessHandlerResult = (new WebSecurityConfig(
                new CustomUserDetailService(mock(UserRepo.class)))).authenticationSuccessHandler();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setSession(new MockHttpSession());
        Response response = new Response();
        actualAuthenticationSuccessHandlerResult.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertTrue(actualAuthenticationSuccessHandlerResult instanceof CustomAuthenticationSuccessHandler);
    }

    @Test
    void testUserDetailsService() throws UsernameNotFoundException {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CustomUserDetails.user
        //     WebSecurityConfig.customUserDetailService
        //     WebSecurityConfigurerAdapter.authenticationBuilder
        //     WebSecurityConfigurerAdapter.authenticationConfiguration
        //     WebSecurityConfigurerAdapter.authenticationManager
        //     WebSecurityConfigurerAdapter.authenticationManagerInitialized
        //     WebSecurityConfigurerAdapter.contentNegotiationStrategy
        //     WebSecurityConfigurerAdapter.context
        //     WebSecurityConfigurerAdapter.disableDefaults
        //     WebSecurityConfigurerAdapter.disableLocalConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.http
        //     WebSecurityConfigurerAdapter.localConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.logger
        //     WebSecurityConfigurerAdapter.objectPostProcessor
        //     WebSecurityConfigurerAdapter.trustResolver

        Cart cart = new Cart();
        cart.setId(123L);
        cart.setUser(new User());
        cart.setCartItems(new ArrayList<>());

        User user = new User();
        user.setLastName("Doe");
        user.setCart(cart);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setRole(Role.ROLE_USER);
        user.setEnabled(true);
        user.setLocked(true);
        user.setFirstName("Jane");

        Cart cart1 = new Cart();
        cart1.setId(123L);
        cart1.setUser(user);
        cart1.setCartItems(new ArrayList<>());

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setCart(cart1);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setRole(Role.ROLE_USER);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setFirstName("Jane");
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername((String) any())).thenReturn(Optional.of(user1));
        (new WebSecurityConfig(new CustomUserDetailService(userRepo))).userDetailsService().loadUserByUsername("foo");
    }

    @Test
    void testPasswordEncoder() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     WebSecurityConfig.customUserDetailService
        //     WebSecurityConfigurerAdapter.authenticationBuilder
        //     WebSecurityConfigurerAdapter.authenticationConfiguration
        //     WebSecurityConfigurerAdapter.authenticationManager
        //     WebSecurityConfigurerAdapter.authenticationManagerInitialized
        //     WebSecurityConfigurerAdapter.contentNegotiationStrategy
        //     WebSecurityConfigurerAdapter.context
        //     WebSecurityConfigurerAdapter.disableDefaults
        //     WebSecurityConfigurerAdapter.disableLocalConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.http
        //     WebSecurityConfigurerAdapter.localConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.logger
        //     WebSecurityConfigurerAdapter.objectPostProcessor
        //     WebSecurityConfigurerAdapter.trustResolver
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.random
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version

        (new WebSecurityConfig(new CustomUserDetailService(mock(UserRepo.class)))).passwordEncoder();
    }

    @Test
    void testAuthenticationProvider() {
        DaoAuthenticationProvider actualAuthenticationProviderResult = (new WebSecurityConfig(
                new CustomUserDetailService(mock(UserRepo.class)))).authenticationProvider();
        assertTrue(actualAuthenticationProviderResult
                .getUserCache() instanceof org.springframework.security.core.userdetails.cache.NullUserCache);
        assertTrue(actualAuthenticationProviderResult.isHideUserNotFoundExceptions());
        assertFalse(actualAuthenticationProviderResult.isForcePrincipalAsString());
    }

    @Test
    void testCorsConfigurationSource() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     WebSecurityConfig.customUserDetailService
        //     WebSecurityConfigurerAdapter.authenticationBuilder
        //     WebSecurityConfigurerAdapter.authenticationConfiguration
        //     WebSecurityConfigurerAdapter.authenticationManager
        //     WebSecurityConfigurerAdapter.authenticationManagerInitialized
        //     WebSecurityConfigurerAdapter.contentNegotiationStrategy
        //     WebSecurityConfigurerAdapter.context
        //     WebSecurityConfigurerAdapter.disableDefaults
        //     WebSecurityConfigurerAdapter.disableLocalConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.http
        //     WebSecurityConfigurerAdapter.localConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.logger
        //     WebSecurityConfigurerAdapter.objectPostProcessor
        //     WebSecurityConfigurerAdapter.trustResolver
        //     CorsConfiguration.allowCredentials
        //     CorsConfiguration.allowedHeaders
        //     CorsConfiguration.allowedMethods
        //     CorsConfiguration.allowedOriginPatterns
        //     CorsConfiguration.allowedOrigins
        //     CorsConfiguration.exposedHeaders
        //     CorsConfiguration.maxAge
        //     CorsConfiguration.resolvedMethods

        CorsConfigurationSource actualCorsConfigurationSourceResult = (new WebSecurityConfig(
                new CustomUserDetailService(mock(UserRepo.class)))).corsConfigurationSource();
        actualCorsConfigurationSourceResult.getCorsConfiguration(new MockHttpServletRequest());
    }

    @Test
    void testCorsConfigurationSource2() {
        CorsConfigurationSource actualCorsConfigurationSourceResult = (new WebSecurityConfig(
                new CustomUserDetailService(mock(UserRepo.class)))).corsConfigurationSource();
        assertNull(actualCorsConfigurationSourceResult.getCorsConfiguration(
                new MockHttpServletRequest("https://example.org/example", "https://example.org/example")));
    }
}

