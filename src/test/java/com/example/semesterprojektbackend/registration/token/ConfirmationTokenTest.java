package com.example.semesterprojektbackend.registration.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.CartItem;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class ConfirmationTokenTest {
    @Test
    void testConstructor() {
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime ofResult1 = LocalDateTime.of(1, 1, 1, 1, 1);

        Cart cart = new Cart();
        cart.setId(123L);
        User user = new User();
        cart.setUser(user);
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cart.setCartItems(cartItemList);

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setCart(cart);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setRole(Role.ROLE_USER);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setFirstName("Jane");

        Cart cart1 = new Cart();
        cart1.setId(123L);
        cart1.setUser(user1);
        ArrayList<CartItem> cartItemList1 = new ArrayList<>();
        cart1.setCartItems(cartItemList1);

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart1);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");
        ConfirmationToken actualConfirmationToken = new ConfirmationToken("ABC123", ofResult, ofResult1, user2);

        assertNull(actualConfirmationToken.getConfirmedAt());
        User user3 = actualConfirmationToken.getUser();
        assertSame(user2, user3);
        assertEquals("ABC123", actualConfirmationToken.getToken());
        LocalDateTime createdAt = actualConfirmationToken.getCreatedAt();
        assertSame(ofResult, createdAt);
        assertNull(actualConfirmationToken.getId());
        assertEquals("01:01", createdAt.toLocalTime().toString());
        LocalDateTime expiresAt = actualConfirmationToken.getExpiresAt();
        assertSame(ofResult1, expiresAt);
        assertEquals("0001-01-01", createdAt.toLocalDate().toString());
        assertEquals("0001-01-01", expiresAt.toLocalDate().toString());
        assertEquals("01:01", expiresAt.toLocalTime().toString());
        assertEquals(Role.ROLE_USER, user3.getRole());
        assertEquals("iloveyou", user3.getPassword());
        assertTrue(user3.getLocked());
        assertEquals("Doe", user3.getLastName());
        assertEquals(123L, user3.getId().longValue());
        assertTrue(user3.isCredentialsNonExpired());
        assertEquals("janedoe", user3.getUsername());
        Cart cart2 = user3.getCart();
        assertSame(cart1, cart2);
        Collection<? extends GrantedAuthority> authorities = user3.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("Jane", user3.getFirstName());
        assertTrue(user3.isAccountNonExpired());
        assertTrue(user3.getEnabled());
        List<CartItem> cartItems = cart2.getCartItems();
        assertSame(cartItemList1, cartItems);
        assertEquals(cartItemList, cartItems);
        assertTrue(cartItems.isEmpty());
        User user4 = cart2.getUser();
        assertSame(user1, user4);
        assertEquals(123L, cart2.getId().longValue());
        GrantedAuthority getResult = ((List<? extends GrantedAuthority>) authorities).get(0);
        assertTrue(getResult instanceof SimpleGrantedAuthority);
        assertEquals("Jane", user4.getFirstName());
        assertEquals("ROLE_USER", getResult.toString());
        assertEquals("ROLE_USER", getResult.getAuthority());
        assertEquals("janedoe", user4.getUsername());
        assertEquals(Role.ROLE_USER, user4.getRole());
        assertEquals("iloveyou", user4.getPassword());
        assertTrue(user4.getLocked());
        assertEquals("Doe", user4.getLastName());
        assertEquals(123L, user4.getId().longValue());
        Collection<? extends GrantedAuthority> authorities1 = user4.getAuthorities();
        assertEquals(1, authorities1.size());
        assertTrue(user4.getEnabled());
        assertTrue(user4.isCredentialsNonExpired());
        Cart cart3 = user4.getCart();
        assertSame(cart, cart3);
        assertTrue(user4.isAccountNonExpired());
        User user5 = cart3.getUser();
        assertSame(user, user5);
        assertEquals(123L, cart3.getId().longValue());
        GrantedAuthority getResult1 = ((List<? extends GrantedAuthority>) authorities1).get(0);
        assertTrue(getResult1 instanceof SimpleGrantedAuthority);
        List<CartItem> cartItems1 = cart3.getCartItems();
        assertSame(cartItemList, cartItems1);
        assertEquals(cartItems, cartItems1);
        assertEquals(getResult, getResult1);
        assertNull(user5.getLastName());
        assertNull(user5.getId());
        assertNull(user5.getFirstName());
        assertTrue(user5.getEnabled());
        assertNull(user5.getPassword());
        assertFalse(user5.getLocked());
        assertSame(user3, user2);
    }
}

