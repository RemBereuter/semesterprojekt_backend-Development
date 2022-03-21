package com.example.semesterprojektbackend.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class CustomUserDetailsTest {
    @Test
    void testConstructor() {
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
        CustomUserDetails actualCustomUserDetails = new CustomUserDetails(user1);
        assertTrue(actualCustomUserDetails.isAccountNonExpired());
        assertTrue(actualCustomUserDetails.isAccountNonLocked());
        assertTrue(actualCustomUserDetails.isCredentialsNonExpired());
    }

    @Test
    void testGetAuthorities() {
        User user = new User();
        user.setRole(Role.ROLE_USER);
        Collection<? extends GrantedAuthority> actualAuthorities = (new CustomUserDetails(user)).getAuthorities();
        assertEquals(1, actualAuthorities.size());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    @Test
    void testGetPassword() {
        assertNull((new CustomUserDetails(new User())).getPassword());
    }

    @Test
    void testGetUsername() {
        assertNull((new CustomUserDetails(new User())).getUsername());
    }

    @Test
    void testIsEnabled() {
        assertTrue((new CustomUserDetails(new User())).isEnabled());
        assertFalse((new CustomUserDetails(
                new User(123L, "Jane", "Doe", "janedoe", "iloveyou", Role.ROLE_USER, true, false, new Cart()))).isEnabled());
    }
}

