package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    private static CartItem cartItem1;

    @BeforeEach
    void setUp() {
        Cart sampleCart = new Cart.Builder()
                .setUserId(12345)
                .setItems(new ArrayList<>())
                .setTotalPrice(150.0f)
                .build();
        cartItem1 = CartItemFactory.createCartItem(sampleCart, 101, 2, 29.0f);
    }

    @Test
    void testCreate() {
        CartItem created = cartItemService.create(cartItem1);
        assertNotNull(created, "Created CartItem should not be null");
        assertNotNull(created.getCartItemID(), "CartItem ID should not be null");
        System.out.println("Created: " + created);
    }

    @Test
    void testRead() {
        CartItem created = cartItemService.create(cartItem1);
        CartItem read = cartItemService.read(created.getCartItemID());
        assertNotNull(read, "Read CartItem should not be null");
        assertEquals(created, read, "Read CartItem should match the created one");
        System.out.println("Read: " + read);
    }

    @Test
    void testUpdate() {
        CartItem created = cartItemService.create(cartItem1);


        CartItem updated = cartItemService.update(created);
        assertNotNull(updated, "Updated CartItem should not be null");
        assertEquals(5, updated.getQuantity(), "Updated CartItem's quantity should be 5");
        System.out.println("Updated: " + updated);
    }

    @Test
    @Disabled
    void testDelete() {
        CartItem created = cartItemService.create(cartItem1);
        cartItemService.delete(created.getCartItemID());
        CartItem deleted = cartItemService.read(created.getCartItemID());
        assertNull(deleted, "Deleted CartItem should be null");
        System.out.println("CartItem deleted where CartItem ID: " + created.getCartItemID());
    }

    @Test
    void testGetAll() {
        cartItemService.create(cartItem1);
        Set<CartItem> cartItems = cartItemService.getAll();
        assertNotNull(cartItems, "List of CartItems should not be null");
        assertFalse(cartItems.isEmpty(), "List of CartItems should not be empty");
        System.out.println("All CartItems: " + cartItems);
    }
}