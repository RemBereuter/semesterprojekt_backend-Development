package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.UserDTO;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.registration.token.ConfirmationToken;
import com.example.semesterprojektbackend.registration.token.ConfirmationTokenRepo;
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

@ContextConfiguration(classes = {UserDTOService.class})
@ExtendWith(SpringExtension.class)
class UserDTOServiceTest {
    @MockBean
    private ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    private UserDTOService userDTOService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testFindByToken() {
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
        when(this.confirmationTokenRepo.findByToken((String) any())).thenReturn(ofResult1);
        UserDTO actualFindByTokenResult = this.userDTOService.findByToken("ABC123");
        assertTrue(actualFindByTokenResult.getEnabled());
        assertEquals("janedoe", actualFindByTokenResult.getUsername());
        assertTrue(actualFindByTokenResult.getLocked());
        assertEquals("Doe", actualFindByTokenResult.getLastName());
        assertEquals("Jane", actualFindByTokenResult.getFirstName());
        verify(this.userRepo).findById((Long) any());
        verify(this.confirmationTokenRepo).findByToken((String) any());
    }
}

