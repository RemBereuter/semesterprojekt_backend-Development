package com.example.semesterprojektbackend.registration.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConfirmationTokenService.class})
@ExtendWith(SpringExtension.class)
class ConfirmationTokenServiceTest {
    @MockBean
    private ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testSaveConfirmationToken() {
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

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setUser(user1);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        when(this.confirmationTokenRepo.save((ConfirmationToken) any())).thenReturn(confirmationToken);

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(new Cart());
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user2);
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

        ConfirmationToken confirmationToken1 = new ConfirmationToken();
        confirmationToken1.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setId(123L);
        confirmationToken1.setUser(user3);
        confirmationToken1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setToken("ABC123");
        this.confirmationTokenService.saveConfirmationToken(confirmationToken1);
        verify(this.confirmationTokenRepo).save((ConfirmationToken) any());
    }

    @Test
    void testGetToken() {
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

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setUser(user1);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        Optional<ConfirmationToken> ofResult = Optional.of(confirmationToken);
        when(this.confirmationTokenRepo.findByToken((String) any())).thenReturn(ofResult);
        Optional<ConfirmationToken> actualToken = this.confirmationTokenService.getToken("ABC123");
        assertSame(ofResult, actualToken);
        assertTrue(actualToken.isPresent());
        verify(this.confirmationTokenRepo).findByToken((String) any());
    }

    @Test
    void testGetTokenByUserId() {
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

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setUser(user1);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        Optional<ConfirmationToken> ofResult = Optional.of(confirmationToken);
        when(this.confirmationTokenRepo.findByUserId((Long) any())).thenReturn(ofResult);
        assertEquals("ABC123", this.confirmationTokenService.getTokenByUserId(123L).getToken());
        verify(this.confirmationTokenRepo).findByUserId((Long) any());
    }

    @Test
    void testGetTokenByUsername() {
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

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(new Cart());
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user2);
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

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setUser(user3);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        when(this.confirmationTokenRepo.findByUserId((Long) any())).thenReturn(ofResult1);
        TokenDTO actualTokenByUsername = this.confirmationTokenService.getTokenByUsername("janedoe");
        assertEquals("ROLE_USER", actualTokenByUsername.getRole());
        assertEquals("ABC123", actualTokenByUsername.getToken());
        verify(this.userRepo).findByUsername((String) any());
        verify(this.confirmationTokenRepo).findByUserId((Long) any());
    }

    @Test
    void testSetConfirmedAt() {
        when(this.confirmationTokenRepo.updateConfirmedAt((String) any(), (java.time.LocalDateTime) any())).thenReturn(1);
        assertEquals(1, this.confirmationTokenService.setConfirmedAt("ABC123"));
        verify(this.confirmationTokenRepo).updateConfirmedAt((String) any(), (java.time.LocalDateTime) any());
    }
}

