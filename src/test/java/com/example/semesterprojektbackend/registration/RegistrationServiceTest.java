package com.example.semesterprojektbackend.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.registration.token.ConfirmationToken;
import com.example.semesterprojektbackend.registration.token.ConfirmationTokenService;
import com.example.semesterprojektbackend.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {RegistrationService.class})
@ExtendWith(SpringExtension.class)
class RegistrationServiceTest {
    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UserService userService;

    @Test
    void testRegister() {
        when(this.userService.signUpUser((com.example.semesterprojektbackend.model.User) any())).thenReturn("Sign Up User");
        assertEquals("Sign Up User",
                this.registrationService.register(new RegistrationRequest("Jane", "Doe", "janedoe", "iloveyou")));
        verify(this.userService).signUpUser((com.example.semesterprojektbackend.model.User) any());
    }

    @Test
    void testRegister2() {
        when(this.userService.signUpUser((com.example.semesterprojektbackend.model.User) any())).thenReturn("Sign Up User");
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getPassword()).thenReturn("iloveyou");
        when(registrationRequest.getUsername()).thenReturn("janedoe");
        when(registrationRequest.getLastName()).thenReturn("Doe");
        when(registrationRequest.getFirstName()).thenReturn("Jane");
        assertEquals("Sign Up User", this.registrationService.register(registrationRequest));
        verify(this.userService).signUpUser((com.example.semesterprojektbackend.model.User) any());
        verify(registrationRequest).getFirstName();
        verify(registrationRequest).getLastName();
        verify(registrationRequest).getPassword();
        verify(registrationRequest).getUsername();
    }

    @Test
    void testConfirmToken() {
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
        when(this.confirmationTokenService.getToken((String) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken((String) any());
    }

    @Test
    void testConfirmToken2() {
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
        confirmationToken.setConfirmedAt(null);
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setUser(user1);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        Optional<ConfirmationToken> ofResult = Optional.of(confirmationToken);
        when(this.confirmationTokenService.getToken((String) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken((String) any());
    }

    @Test
    void testConfirmToken3() {
        when(this.confirmationTokenService.getToken((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken((String) any());
    }
}

