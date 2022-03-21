package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.semesterprojektbackend.model.enumuration.Role;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserTest {
    @Test
    void testConstructor() {
        User actualUser = new User();
        Cart expectedCart = actualUser.cart;
        assertSame(expectedCart, actualUser.getCart());
        assertNull(actualUser.getFirstName());
        assertNull(actualUser.getLastName());
        assertNull(actualUser.getPassword());
        assertNull(actualUser.getUsername());
        assertTrue(actualUser.isAccountNonExpired());
        assertTrue(actualUser.isCredentialsNonExpired());
    }

    @Test
    void testConstructor2() {
        User actualUser = new User("Jane", "Doe", "janedoe", "iloveyou", Role.ROLE_USER);

        Cart expectedCart = actualUser.cart;
        assertSame(expectedCart, actualUser.getCart());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals(Role.ROLE_USER, actualUser.getRole());
        assertEquals("janedoe", actualUser.getUsername());
        assertTrue(actualUser.getEnabled());
        assertFalse(actualUser.getLocked());
        assertEquals("Doe", actualUser.getLastName());
    }

    @Test
    void testGetAuthorities() {
        User user = new User();
        user.setRole(Role.ROLE_USER);
        Collection<? extends GrantedAuthority> actualAuthorities = user.getAuthorities();
        assertEquals(1, actualAuthorities.size());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue((new User()).isAccountNonLocked());
        assertFalse((new User(123L, "Jane", "Doe", "janedoe", "iloveyou", Role.ROLE_USER, true, true, new Cart()))
                .isAccountNonLocked());
    }

    @Test
    void testIsEnabled() {
        assertTrue((new User()).isEnabled());
        assertFalse(
                (new User(123L, "Jane", "Doe", "janedoe", "iloveyou", Role.ROLE_USER, true, false, new Cart())).isEnabled());
    }
}

