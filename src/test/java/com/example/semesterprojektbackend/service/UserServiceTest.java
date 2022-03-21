package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.registration.token.ConfirmationTokenService;
import com.example.semesterprojektbackend.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private CartService cartService;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        Optional<User> ofResult = Optional.of(user1);
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(user1, this.userService.loadUserByUsername("janedoe"));
        verify(this.userRepo).findByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userService.loadUserByUsername("janedoe"));
        verify(this.userRepo).findByUsername((String) any());
    }

    @Test
    void testSignUpUser() {
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
        Optional<User> ofResult = Optional.of(user1);
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(new User());
        cart2.setCartItems(new ArrayList<>());

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart2);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");

        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user2);
        cart3.setCartItems(new ArrayList<>());

        User user3 = new User();
        user3.setLastName("Doe");
        user3.setCart(cart3);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setRole(Role.ROLE_USER);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setFirstName("Jane");
        assertThrows(IllegalStateException.class, () -> this.userService.signUpUser(user3));
        verify(this.userRepo).findByUsername((String) any());
    }

    @Test
    void testSignUpUser2() {
        User user = new User();
        user.setLastName("Doe");
        user.setCart(new Cart());
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setRole(Role.ROLE_USER);
        user.setEnabled(true);
        user.setLocked(true);
        user.setFirstName("Jane");

        Cart cart = new Cart();
        cart.setId(123L);
        cart.setUser(user);
        cart.setCartItems(new ArrayList<>());

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
        cart1.setCartItems(new ArrayList<>());

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
        when(this.userRepo.save((User) any())).thenReturn(user2);
        when(this.userRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        doNothing().when(this.confirmationTokenService)
                .saveConfirmationToken((com.example.semesterprojektbackend.registration.token.ConfirmationToken) any());

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(new User());
        cart2.setCartItems(new ArrayList<>());

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
        cart3.setCartItems(new ArrayList<>());

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
        this.userService.signUpUser(user4);
        verify(this.userRepo).findByUsername((String) any());
        verify(this.userRepo).save((User) any());
        verify(this.confirmationTokenService)
                .saveConfirmationToken((com.example.semesterprojektbackend.registration.token.ConfirmationToken) any());
        assertEquals(Role.ROLE_USER, user4.getRole());
    }

    @Test
    void testEnableUser() {
        when(this.userRepo.enableUser((String) any())).thenReturn(1);
        assertEquals(1, this.userService.enableUser("janedoe"));
        verify(this.userRepo).enableUser((String) any());
        assertTrue(this.userService.findAll().isEmpty());
    }

    @Test
    void testFindAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepo.findAll()).thenReturn(userList);
        List<User> actualFindAllResult = this.userService.findAll();
        assertSame(userList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.userRepo).findAll();
    }

    @Test
    void testFindById() {
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
        Optional<User> ofResult = Optional.of(user1);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);
        Optional<User> actualFindByIdResult = this.userService.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.userRepo).findById((Long) any());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setLastName("Doe");
        user.setCart(new Cart());
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setRole(Role.ROLE_USER);
        user.setEnabled(true);
        user.setLocked(true);
        user.setFirstName("Jane");

        Cart cart = new Cart();
        cart.setId(123L);
        cart.setUser(user);
        cart.setCartItems(new ArrayList<>());

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
        cart1.setCartItems(new ArrayList<>());

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
        when(this.userRepo.save((User) any())).thenReturn(user2);

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(new User());
        cart2.setCartItems(new ArrayList<>());

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
        cart3.setCartItems(new ArrayList<>());

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
        assertSame(user4, this.userService.save(user4));
        verify(this.userRepo).save((User) any());
    }

    @Test
    void testDelete() {
        doNothing().when(this.userRepo).deleteById((Long) any());
        this.userService.delete(123L);
        verify(this.userRepo).deleteById((Long) any());
        assertTrue(this.userService.findAll().isEmpty());
    }

    @Test
    void testUpdateUser() {
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
        Optional<User> ofResult = Optional.of(user1);

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(new User());
        cart2.setCartItems(new ArrayList<>());

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart2);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");

        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user2);
        cart3.setCartItems(new ArrayList<>());

        User user3 = new User();
        user3.setLastName("Doe");
        user3.setCart(cart3);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setRole(Role.ROLE_USER);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setFirstName("Jane");
        Optional<User> ofResult1 = Optional.of(user3);

        User user4 = new User();
        user4.setLastName("Doe");
        user4.setCart(new Cart());
        user4.setPassword("iloveyou");
        user4.setUsername("janedoe");
        user4.setId(123L);
        user4.setRole(Role.ROLE_USER);
        user4.setEnabled(true);
        user4.setLocked(true);
        user4.setFirstName("Jane");

        Cart cart4 = new Cart();
        cart4.setId(123L);
        cart4.setUser(user4);
        cart4.setCartItems(new ArrayList<>());

        User user5 = new User();
        user5.setLastName("Doe");
        user5.setCart(cart4);
        user5.setPassword("iloveyou");
        user5.setUsername("janedoe");
        user5.setId(123L);
        user5.setRole(Role.ROLE_USER);
        user5.setEnabled(true);
        user5.setLocked(true);
        user5.setFirstName("Jane");

        Cart cart5 = new Cart();
        cart5.setId(123L);
        cart5.setUser(user5);
        cart5.setCartItems(new ArrayList<>());

        User user6 = new User();
        user6.setLastName("Doe");
        user6.setCart(cart5);
        user6.setPassword("iloveyou");
        user6.setUsername("janedoe");
        user6.setId(123L);
        user6.setRole(Role.ROLE_USER);
        user6.setEnabled(true);
        user6.setLocked(true);
        user6.setFirstName("Jane");
        when(this.userRepo.save((User) any())).thenReturn(user6);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult1);
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);

        Cart cart6 = new Cart();
        cart6.setId(123L);
        cart6.setUser(new User());
        cart6.setCartItems(new ArrayList<>());

        User user7 = new User();
        user7.setLastName("Doe");
        user7.setCart(cart6);
        user7.setPassword("iloveyou");
        user7.setUsername("janedoe");
        user7.setId(123L);
        user7.setRole(Role.ROLE_USER);
        user7.setEnabled(true);
        user7.setLocked(true);
        user7.setFirstName("Jane");

        Cart cart7 = new Cart();
        cart7.setId(123L);
        cart7.setUser(user7);
        cart7.setCartItems(new ArrayList<>());

        User user8 = new User();
        user8.setLastName("Doe");
        user8.setCart(cart7);
        user8.setPassword("iloveyou");
        user8.setUsername("janedoe");
        user8.setId(123L);
        user8.setRole(Role.ROLE_USER);
        user8.setEnabled(true);
        user8.setLocked(true);
        user8.setFirstName("Jane");
        User actualUpdateUserResult = this.userService.updateUser(123L, user8);
        assertSame(user3, actualUpdateUserResult);
        assertEquals("Jane", actualUpdateUserResult.getFirstName());
        assertEquals(Role.ROLE_USER, actualUpdateUserResult.getRole());
        assertTrue(actualUpdateUserResult.getEnabled());
        assertTrue(actualUpdateUserResult.getLocked());
        assertEquals("Doe", actualUpdateUserResult.getLastName());
        verify(this.userRepo).findById((Long) any());
        verify(this.userRepo).findByUsername((String) any());
        verify(this.userRepo).save((User) any());
    }

    @Test
    void testUpdateUserPassword() {
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
        Optional<User> ofResult = Optional.of(user1);

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(new User());
        cart2.setCartItems(new ArrayList<>());

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart2);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");

        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user2);
        cart3.setCartItems(new ArrayList<>());

        User user3 = new User();
        user3.setLastName("Doe");
        user3.setCart(cart3);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setRole(Role.ROLE_USER);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setFirstName("Jane");
        Optional<User> ofResult1 = Optional.of(user3);

        User user4 = new User();
        user4.setLastName("Doe");
        user4.setCart(new Cart());
        user4.setPassword("iloveyou");
        user4.setUsername("janedoe");
        user4.setId(123L);
        user4.setRole(Role.ROLE_USER);
        user4.setEnabled(true);
        user4.setLocked(true);
        user4.setFirstName("Jane");

        Cart cart4 = new Cart();
        cart4.setId(123L);
        cart4.setUser(user4);
        cart4.setCartItems(new ArrayList<>());

        User user5 = new User();
        user5.setLastName("Doe");
        user5.setCart(cart4);
        user5.setPassword("iloveyou");
        user5.setUsername("janedoe");
        user5.setId(123L);
        user5.setRole(Role.ROLE_USER);
        user5.setEnabled(true);
        user5.setLocked(true);
        user5.setFirstName("Jane");

        Cart cart5 = new Cart();
        cart5.setId(123L);
        cart5.setUser(user5);
        cart5.setCartItems(new ArrayList<>());

        User user6 = new User();
        user6.setLastName("Doe");
        user6.setCart(cart5);
        user6.setPassword("iloveyou");
        user6.setUsername("janedoe");
        user6.setId(123L);
        user6.setRole(Role.ROLE_USER);
        user6.setEnabled(true);
        user6.setLocked(true);
        user6.setFirstName("Jane");
        when(this.userRepo.save((User) any())).thenReturn(user6);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult1);
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);

        Cart cart6 = new Cart();
        cart6.setId(123L);
        cart6.setUser(new User());
        cart6.setCartItems(new ArrayList<>());

        User user7 = new User();
        user7.setLastName("Doe");
        user7.setCart(cart6);
        user7.setPassword("iloveyou");
        user7.setUsername("janedoe");
        user7.setId(123L);
        user7.setRole(Role.ROLE_USER);
        user7.setEnabled(true);
        user7.setLocked(true);
        user7.setFirstName("Jane");

        Cart cart7 = new Cart();
        cart7.setId(123L);
        cart7.setUser(user7);
        cart7.setCartItems(new ArrayList<>());

        User user8 = new User();
        user8.setLastName("Doe");
        user8.setCart(cart7);
        user8.setPassword("iloveyou");
        user8.setUsername("janedoe");
        user8.setId(123L);
        user8.setRole(Role.ROLE_USER);
        user8.setEnabled(true);
        user8.setLocked(true);
        user8.setFirstName("Jane");
        assertSame(user3, this.userService.updateUserPassword(123L, user8));
        verify(this.userRepo).findById((Long) any());
        verify(this.userRepo).findByUsername((String) any());
        verify(this.userRepo).save((User) any());
    }
}

