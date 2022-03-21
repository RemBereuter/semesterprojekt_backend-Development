package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.semesterprojektbackend.model.enumuration.Role;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CartTest {
    @Test
    void testConstructor() {
        Cart actualCart = new Cart();
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        actualCart.setCartItems(cartItemList);
        actualCart.setId(123L);
        Cart cart = new Cart();
        cart.setId(123L);
        User user = new User();
        cart.setUser(user);
        ArrayList<CartItem> cartItemList1 = new ArrayList<>();
        cart.setCartItems(cartItemList1);
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
        ArrayList<CartItem> cartItemList2 = new ArrayList<>();
        cart1.setCartItems(cartItemList2);
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
        actualCart.setUser(user2);
        List<CartItem> cartItems = actualCart.getCartItems();
        assertSame(cartItemList, cartItems);
        assertEquals(cartItemList1, cartItems);
        assertEquals(cartItemList2, cartItems);
        User user3 = actualCart.getUser();
        Cart cart2 = user3.getCart();
        List<CartItem> cartItems1 = cart2.getCartItems();
        assertSame(cartItemList2, cartItems1);
        assertEquals(cartItemList1, cartItems1);
        assertEquals(cartItems, cartItems1);
        User user4 = cart2.getUser();
        Cart cart3 = user4.getCart();
        List<CartItem> cartItems2 = cart3.getCartItems();
        assertSame(cartItemList1, cartItems2);
        assertEquals(cartItems, cartItems2);
        assertEquals(cartItems1, cartItems2);
        assertEquals(123L, actualCart.getId().longValue());
        assertEquals(123L, cart2.getId().longValue());
        assertEquals(123L, cart3.getId().longValue());
        assertSame(user2, user3);
        assertSame(user1, user4);
        assertSame(user, cart3.getUser());
    }

    @Test
    void testConstructor2() {
        ArrayList<CartItem> cartItemList = new ArrayList<>();

        Cart cart = new Cart();
        cart.setId(123L);
        cart.setUser(new User());
        ArrayList<CartItem> cartItemList1 = new ArrayList<>();
        cart.setCartItems(cartItemList1);

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
        ArrayList<CartItem> cartItemList2 = new ArrayList<>();
        cart1.setCartItems(cartItemList2);

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
        Cart actualCart = new Cart(123L, cartItemList, user1);
        ArrayList<CartItem> cartItemList3 = new ArrayList<>();
        actualCart.setCartItems(cartItemList3);
        actualCart.setId(123L);
        Cart cart2 = new Cart();
        cart2.setId(123L);
        User user2 = new User();
        cart2.setUser(user2);
        ArrayList<CartItem> cartItemList4 = new ArrayList<>();
        cart2.setCartItems(cartItemList4);
        User user3 = new User();
        user3.setLastName("Doe");
        user3.setCart(cart2);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setRole(Role.ROLE_USER);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setFirstName("Jane");
        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user3);
        ArrayList<CartItem> cartItemList5 = new ArrayList<>();
        cart3.setCartItems(cartItemList5);
        User user4 = new User();
        user4.setLastName("Doe");
        user4.setCart(cart3);
        user4.setPassword("iloveyou");
        user4.setUsername("janedoe");
        user4.setId(123L);
        user4.setRole(Role.ROLE_USER);
        user4.setEnabled(true);
        user4.setLocked(true);
        user4.setFirstName("Jane");
        actualCart.setUser(user4);
        List<CartItem> cartItems = actualCart.getCartItems();
        assertSame(cartItemList3, cartItems);
        assertEquals(cartItemList, cartItems);
        assertEquals(cartItemList1, cartItems);
        assertEquals(cartItemList2, cartItems);
        assertEquals(cartItemList4, cartItems);
        assertEquals(cartItemList5, cartItems);
        User user5 = actualCart.getUser();
        Cart cart4 = user5.getCart();
        List<CartItem> cartItems1 = cart4.getCartItems();
        assertSame(cartItemList5, cartItems1);
        assertEquals(cartItemList, cartItems1);
        assertEquals(cartItemList1, cartItems1);
        assertEquals(cartItemList2, cartItems1);
        assertEquals(cartItemList4, cartItems1);
        assertEquals(cartItems, cartItems1);
        User user6 = cart4.getUser();
        Cart cart5 = user6.getCart();
        List<CartItem> cartItems2 = cart5.getCartItems();
        assertSame(cartItemList4, cartItems2);
        assertEquals(cartItemList, cartItems2);
        assertEquals(cartItemList1, cartItems2);
        assertEquals(cartItemList2, cartItems2);
        assertEquals(cartItems, cartItems2);
        assertEquals(cartItems1, cartItems2);
        assertEquals(123L, actualCart.getId().longValue());
        assertEquals(123L, cart4.getId().longValue());
        assertEquals(123L, cart5.getId().longValue());
        assertSame(user4, user5);
        assertSame(user3, user6);
        assertSame(user2, cart5.getUser());
    }
}

