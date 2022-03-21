package com.example.semesterprojektbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.semesterprojektbackend.model.Brand;
import com.example.semesterprojektbackend.model.Cart;
import com.example.semesterprojektbackend.model.CartDTO;
import com.example.semesterprojektbackend.model.CartItem;
import com.example.semesterprojektbackend.model.Category;
import com.example.semesterprojektbackend.model.Product;
import com.example.semesterprojektbackend.model.User;
import com.example.semesterprojektbackend.model.enumuration.Role;
import com.example.semesterprojektbackend.model.enumuration.Size;
import com.example.semesterprojektbackend.model.enumuration.Status;
import com.example.semesterprojektbackend.repositories.CartItemRepo;
import com.example.semesterprojektbackend.repositories.CartRepo;
import com.example.semesterprojektbackend.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartService.class})
@ExtendWith(SpringExtension.class)
class CartServiceTest {
    @MockBean
    private CartItemRepo cartItemRepo;

    @MockBean
    private CartRepo cartRepo;

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testGetCart() {
        ArrayList<Cart> cartList = new ArrayList<>();
        when(this.cartRepo.findAll()).thenReturn(cartList);
        List<Cart> actualCart = this.cartService.getCart();
        assertSame(cartList, actualCart);
        assertTrue(actualCart.isEmpty());
        verify(this.cartRepo).findAll();
    }

    @Test
    void testSave() {
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

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user1);
        cart2.setCartItems(new ArrayList<>());
        when(this.cartRepo.save((Cart) any())).thenReturn(cart2);

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

        Cart cart4 = new Cart();
        cart4.setId(123L);
        cart4.setUser(user3);
        cart4.setCartItems(new ArrayList<>());
        this.cartService.save(cart4);
        verify(this.cartRepo).save((Cart) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testFindById() {
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
        Optional<Cart> ofResult = Optional.of(cart1);
        when(this.cartRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Cart> actualFindByIdResult = this.cartService.findById(1);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.cartRepo).findById((Integer) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testCreateCart() {
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

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user1);
        cart2.setCartItems(new ArrayList<>());
        when(this.cartRepo.save((Cart) any())).thenReturn(cart2);
        assertEquals(123L, this.cartService.createCart(123L).getId().longValue());
        verify(this.cartRepo).save((Cart) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.cartRepo).deleteById((Integer) any());
        this.cartService.delete(1);
        verify(this.cartRepo).deleteById((Integer) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testGetCartDTO() {
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
        CartDTO actualCartDTO = this.cartService.getCartDTO("janedoe");
        assertEquals(0, actualCartDTO.getCartItemsCount());
        assertEquals(0.0f, actualCartDTO.getCartTotal());
        verify(this.userRepo).findByUsername((String) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testGetCartDTO2() {
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
        cart1.setUser(new User());
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

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user1);
        cart2.setCartItems(new ArrayList<>());

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart2);
        cartItem.setId(1);
        cartItem.setQuantity(0);
        cartItem.setProduct(product);

        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user);
        cart3.setCartItems(cartItemList);

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart3);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");
        Optional<User> ofResult = Optional.of(user2);
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);
        CartDTO actualCartDTO = this.cartService.getCartDTO("janedoe");
        assertEquals(1, actualCartDTO.getCartItemsCount());
        assertEquals(0.0f, actualCartDTO.getCartTotal());
        verify(this.userRepo).findByUsername((String) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testChangeQuantity() {
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        List<CartItem> actualChangeQuantityResult = this.cartService.changeQuantity(cartItemList, 1, 42L);
        assertSame(cartItemList, actualChangeQuantityResult);
        assertTrue(actualChangeQuantityResult.isEmpty());
    }

    @Test
    void testChangeQuantity2() {
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

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart1);
        cartItem.setId(1);
        cartItem.setQuantity(0);
        cartItem.setProduct(product);

        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        List<CartItem> actualChangeQuantityResult = this.cartService.changeQuantity(cartItemList, 1, 42L);
        assertSame(cartItemList, actualChangeQuantityResult);
        assertEquals(1, actualChangeQuantityResult.size());
    }

    @Test
    void testChangeQuantity3() {
        doNothing().when(this.cartItemRepo).delete((CartItem) any());

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

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart1);
        cartItem.setId(1);
        cartItem.setQuantity(-1);
        cartItem.setProduct(product);

        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        List<CartItem> actualChangeQuantityResult = this.cartService.changeQuantity(cartItemList, 1, 42L);
        assertSame(cartItemList, actualChangeQuantityResult);
        assertTrue(actualChangeQuantityResult.isEmpty());
        verify(this.cartItemRepo).delete((CartItem) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testUpdateCart() {
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

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);
        when(this.productService.findByItemNumber((Long) any())).thenReturn(product);

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

        Cart cart4 = new Cart();
        cart4.setId(123L);
        cart4.setUser(user3);
        cart4.setCartItems(new ArrayList<>());
        when(this.cartRepo.save((Cart) any())).thenReturn(cart4);
        doNothing().when(this.cartRepo).flush();

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

        Cart cart5 = new Cart();
        cart5.setId(123L);
        cart5.setUser(user4);
        cart5.setCartItems(new ArrayList<>());

        User user5 = new User();
        user5.setLastName("Doe");
        user5.setCart(cart5);
        user5.setPassword("iloveyou");
        user5.setUsername("janedoe");
        user5.setId(123L);
        user5.setRole(Role.ROLE_USER);
        user5.setEnabled(true);
        user5.setLocked(true);
        user5.setFirstName("Jane");

        Cart cart6 = new Cart();
        cart6.setId(123L);
        cart6.setUser(user5);
        cart6.setCartItems(new ArrayList<>());

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Product product1 = new Product();
        product1.setStatus(Status.ACTIVE);
        product1.setBrand(brand1);
        product1.setPrice(10.0);
        product1.setImageUrl("https://example.org/example");
        product1.setName("Name");
        product1.setSize(Size.S);
        product1.setCategory(category1);
        product1.setDescription("The characteristics of someone or something");
        product1.setItemNumber(42L);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart6);
        cartItem.setId(1);
        cartItem.setQuantity(1);
        cartItem.setProduct(product1);
        when(this.cartItemRepo.save((CartItem) any())).thenReturn(cartItem);
        this.cartService.updateCart(42L, 1, "janedoe");
        verify(this.userRepo).findByUsername((String) any());
        verify(this.productService).findByItemNumber((Long) any());
        verify(this.cartRepo).flush();
        verify(this.cartRepo).save((Cart) any());
        verify(this.cartItemRepo).save((CartItem) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }

    @Test
    void testUpdateCart2() {
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
        cart1.setUser(new User());
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

        Cart cart2 = new Cart();
        cart2.setId(123L);
        cart2.setUser(user1);
        cart2.setCartItems(new ArrayList<>());

        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("Brand Name");

        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Product product = new Product();
        product.setStatus(Status.ACTIVE);
        product.setBrand(brand);
        product.setPrice(10.0);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setSize(Size.S);
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setItemNumber(42L);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart2);
        cartItem.setId(1);
        cartItem.setQuantity(0);
        cartItem.setProduct(product);

        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        Cart cart3 = new Cart();
        cart3.setId(123L);
        cart3.setUser(user);
        cart3.setCartItems(cartItemList);

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setCart(cart3);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setRole(Role.ROLE_USER);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setFirstName("Jane");
        Optional<User> ofResult = Optional.of(user2);

        User user3 = new User();
        user3.setLastName("Doe");
        user3.setCart(new Cart());
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setRole(Role.ROLE_USER);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setFirstName("Jane");

        Cart cart4 = new Cart();
        cart4.setId(123L);
        cart4.setUser(user3);
        cart4.setCartItems(new ArrayList<>());

        User user4 = new User();
        user4.setLastName("Doe");
        user4.setCart(cart4);
        user4.setPassword("iloveyou");
        user4.setUsername("janedoe");
        user4.setId(123L);
        user4.setRole(Role.ROLE_USER);
        user4.setEnabled(true);
        user4.setLocked(true);
        user4.setFirstName("Jane");

        Cart cart5 = new Cart();
        cart5.setId(123L);
        cart5.setUser(user4);
        cart5.setCartItems(new ArrayList<>());

        User user5 = new User();
        user5.setLastName("Doe");
        user5.setCart(cart5);
        user5.setPassword("iloveyou");
        user5.setUsername("janedoe");
        user5.setId(123L);
        user5.setRole(Role.ROLE_USER);
        user5.setEnabled(true);
        user5.setLocked(true);
        user5.setFirstName("Jane");
        when(this.userRepo.save((User) any())).thenReturn(user5);
        doNothing().when(this.userRepo).flush();
        when(this.userRepo.findByUsername((String) any())).thenReturn(ofResult);

        Brand brand1 = new Brand();
        brand1.setId(1);
        brand1.setBrandName("Brand Name");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Product product1 = new Product();
        product1.setStatus(Status.ACTIVE);
        product1.setBrand(brand1);
        product1.setPrice(10.0);
        product1.setImageUrl("https://example.org/example");
        product1.setName("Name");
        product1.setSize(Size.S);
        product1.setCategory(category1);
        product1.setDescription("The characteristics of someone or something");
        product1.setItemNumber(42L);
        when(this.productService.findByItemNumber((Long) any())).thenReturn(product1);

        Cart cart6 = new Cart();
        cart6.setId(123L);
        cart6.setUser(new User());
        cart6.setCartItems(new ArrayList<>());

        User user6 = new User();
        user6.setLastName("Doe");
        user6.setCart(cart6);
        user6.setPassword("iloveyou");
        user6.setUsername("janedoe");
        user6.setId(123L);
        user6.setRole(Role.ROLE_USER);
        user6.setEnabled(true);
        user6.setLocked(true);
        user6.setFirstName("Jane");

        Cart cart7 = new Cart();
        cart7.setId(123L);
        cart7.setUser(user6);
        cart7.setCartItems(new ArrayList<>());

        User user7 = new User();
        user7.setLastName("Doe");
        user7.setCart(cart7);
        user7.setPassword("iloveyou");
        user7.setUsername("janedoe");
        user7.setId(123L);
        user7.setRole(Role.ROLE_USER);
        user7.setEnabled(true);
        user7.setLocked(true);
        user7.setFirstName("Jane");

        Cart cart8 = new Cart();
        cart8.setId(123L);
        cart8.setUser(user7);
        cart8.setCartItems(new ArrayList<>());
        when(this.cartRepo.save((Cart) any())).thenReturn(cart8);
        doNothing().when(this.cartRepo).flush();

        User user8 = new User();
        user8.setLastName("Doe");
        user8.setCart(new Cart());
        user8.setPassword("iloveyou");
        user8.setUsername("janedoe");
        user8.setId(123L);
        user8.setRole(Role.ROLE_USER);
        user8.setEnabled(true);
        user8.setLocked(true);
        user8.setFirstName("Jane");

        Cart cart9 = new Cart();
        cart9.setId(123L);
        cart9.setUser(user8);
        cart9.setCartItems(new ArrayList<>());

        User user9 = new User();
        user9.setLastName("Doe");
        user9.setCart(cart9);
        user9.setPassword("iloveyou");
        user9.setUsername("janedoe");
        user9.setId(123L);
        user9.setRole(Role.ROLE_USER);
        user9.setEnabled(true);
        user9.setLocked(true);
        user9.setFirstName("Jane");

        Cart cart10 = new Cart();
        cart10.setId(123L);
        cart10.setUser(user9);
        cart10.setCartItems(new ArrayList<>());

        Brand brand2 = new Brand();
        brand2.setId(1);
        brand2.setBrandName("Brand Name");

        Category category2 = new Category();
        category2.setCategoryName("Category Name");
        category2.setId(1);

        Product product2 = new Product();
        product2.setStatus(Status.ACTIVE);
        product2.setBrand(brand2);
        product2.setPrice(10.0);
        product2.setImageUrl("https://example.org/example");
        product2.setName("Name");
        product2.setSize(Size.S);
        product2.setCategory(category2);
        product2.setDescription("The characteristics of someone or something");
        product2.setItemNumber(42L);

        CartItem cartItem1 = new CartItem();
        cartItem1.setCart(cart10);
        cartItem1.setId(1);
        cartItem1.setQuantity(1);
        cartItem1.setProduct(product2);
        when(this.cartItemRepo.save((CartItem) any())).thenReturn(cartItem1);
        this.cartService.updateCart(42L, 1, "janedoe");
        verify(this.userRepo).findByUsername((String) any());
        verify(this.userRepo).flush();
        verify(this.userRepo).save((User) any());
        assertTrue(this.cartService.getCart().isEmpty());
    }
}

