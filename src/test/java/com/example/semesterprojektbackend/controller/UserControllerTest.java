package com.example.semesterprojektbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.UserDTO;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.registration.token.ConfirmationTokenRepo;
import com.example.semesterprojektbackend.registration.token.ConfirmationTokenService;
import com.example.semesterprojektbackend.repositories.CartItemRepo;
import com.example.semesterprojektbackend.repositories.CartRepo;
import com.example.semesterprojektbackend.repositories.ProductRepo;
import com.example.semesterprojektbackend.repositories.UserRepo;
import com.example.semesterprojektbackend.service.CartService;
import com.example.semesterprojektbackend.service.ProductService;
import com.example.semesterprojektbackend.service.UserDTOService;
import com.example.semesterprojektbackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserDTOService userDTOService;

    @MockBean
    private UserService userService;

    @Test
    void testAddNew() throws Exception {
        when(this.userService.findAll()).thenReturn(new ArrayList<>());

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
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
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
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save((User) any())).thenReturn(user6);
        when(userRepo.findById((Long) any())).thenReturn(ofResult1);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService(mock(ConfirmationTokenRepo.class),
                mock(UserRepo.class));

        CartRepo cartRepo = mock(CartRepo.class);
        UserRepo userRepo1 = mock(UserRepo.class);
        CartItemRepo cartItemRepo = mock(CartItemRepo.class);
        UserService userService = new UserService(userRepo, bCryptPasswordEncoder, confirmationTokenService,
                new CartService(cartRepo, userRepo1, cartItemRepo, new ProductService(mock(ProductRepo.class))));

        UserController userController = new UserController(userService,
                new UserDTOService(mock(UserRepo.class), mock(ConfirmationTokenRepo.class)));

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
        ResponseEntity<User> actualUpdateUserResult = userController.updateUser(123L, user8);
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
        assertTrue(actualUpdateUserResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateUserResult.getStatusCode());
        User body = actualUpdateUserResult.getBody();
        assertTrue(body.getLocked());
        assertEquals("Doe", body.getLastName());
        assertEquals("Jane", body.getFirstName());
        assertTrue(body.getEnabled());
        assertEquals(Role.ROLE_USER, body.getRole());
        verify(userRepo).findById((Long) any());
        verify(userRepo).findByUsername((String) any());
        verify(userRepo).save((User) any());
    }

    @Test
    void testUpdateUser2() {
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
        UserService userService = mock(UserService.class);
        when(userService.updateUser((Long) any(), (User) any())).thenReturn(user2);
        UserController userController = new UserController(userService,
                new UserDTOService(mock(UserRepo.class), mock(ConfirmationTokenRepo.class)));

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
        ResponseEntity<User> actualUpdateUserResult = userController.updateUser(123L, user4);
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
        assertTrue(actualUpdateUserResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateUserResult.getStatusCode());
        verify(userService).updateUser((Long) any(), (User) any());
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
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save((User) any())).thenReturn(user6);
        when(userRepo.findById((Long) any())).thenReturn(ofResult1);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService(mock(ConfirmationTokenRepo.class),
                mock(UserRepo.class));

        CartRepo cartRepo = mock(CartRepo.class);
        UserRepo userRepo1 = mock(UserRepo.class);
        CartItemRepo cartItemRepo = mock(CartItemRepo.class);
        UserService userService = new UserService(userRepo, bCryptPasswordEncoder, confirmationTokenService,
                new CartService(cartRepo, userRepo1, cartItemRepo, new ProductService(mock(ProductRepo.class))));

        UserController userController = new UserController(userService,
                new UserDTOService(mock(UserRepo.class), mock(ConfirmationTokenRepo.class)));

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
        ResponseEntity<User> actualUpdateUserPasswordResult = userController.updateUserPassword(123L, user8);
        assertTrue(actualUpdateUserPasswordResult.getHeaders().isEmpty());
        assertTrue(actualUpdateUserPasswordResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateUserPasswordResult.getStatusCode());
        verify(userRepo).findById((Long) any());
        verify(userRepo).findByUsername((String) any());
        verify(userRepo).save((User) any());
    }

    @Test
    void testUpdateUserPassword2() {
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
        UserService userService = mock(UserService.class);
        when(userService.updateUserPassword((Long) any(), (User) any())).thenReturn(user2);
        UserController userController = new UserController(userService,
                new UserDTOService(mock(UserRepo.class), mock(ConfirmationTokenRepo.class)));

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
        ResponseEntity<User> actualUpdateUserPasswordResult = userController.updateUserPassword(123L, user4);
        assertTrue(actualUpdateUserPasswordResult.getHeaders().isEmpty());
        assertTrue(actualUpdateUserPasswordResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateUserPasswordResult.getStatusCode());
        verify(userService).updateUserPassword((Long) any(), (User) any());
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.userService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{userId}", 123L);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"deleted\":true}"));
    }

    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.userService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/users/{userId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"deleted\":true}"));
    }

    @Test
    void testGetUserById() throws Exception {
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
        when(this.userService.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":\"ROLE"
                                        + "_USER\",\"locked\":true,\"enabled\":true,\"cart\":{\"id\":123,\"cartItems\":[],\"user\":{\"id\":123,\"firstName\":\"Jane"
                                        + "\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":\"ROLE_USER\",\"locked\":true,\"enabled"
                                        + "\":true,\"cart\":{\"id\":123,\"cartItems\":[],\"user\":{\"id\":null,\"firstName\":null,\"lastName\":null,\"username\""
                                        + ":null,\"password\":null,\"role\":null,\"locked\":false,\"enabled\":true,\"cart\":{\"id\":null,\"cartItems\":null,"
                                        + "\"user\":null}}}}}}"));
    }

    @Test
    void testGetUserDTOByToken() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Doe");
        userDTO.setUsername("janedoe");
        userDTO.setLocked(true);
        userDTO.setEnabled(true);
        userDTO.setFirstName("Jane");
        when(this.userDTOService.findByToken((String) any())).thenReturn(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/token/{token}", "ABC123");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"locked\":true,\"enabled\":true}"));
    }

    @Test
    void testGetUsers() throws Exception {
        when(this.userService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetUsers2() throws Exception {
        when(this.userService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

