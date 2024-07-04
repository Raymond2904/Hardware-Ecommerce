package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartServiceTest {

    @Autowired
    private CartService cartService;

    private static Cart cart1;

    @BeforeEach
    void setUp() {
        List<CartItem> cartItems = new ArrayList<>();
        cart1 = new Cart.Builder()
                .setUserId(12345)
                .setItems(cartItems)
                .setTotalPrice(150.0f)
                .build();
    }

    @Test
    void testCreate() {
        Cart created = cartService.create(cart1);
        assertNotNull(created, "Created Cart should not be null");
        assertNotNull(created.getCartId(), "Cart ID should not be null");
        System.out.println("Created: " + created);
    }

    @Test
    void testRead() {
        Cart created = cartService.create(cart1);
        Cart read = cartService.read(created.getCartId());
        assertNotNull(read, "Read Cart should not be null");
        assertEquals(created, read, "Read Cart should match the created one");
        System.out.println("Read: " + read);
    }

    @Test
    void testUpdate() {
        Cart created = cartService.create(cart1);


        Cart updated = cartService.update(created);
        assertNotNull(updated, "Updated Cart should not be null");
        assertEquals(200.0f, updated.getTotalPrice(), "Updated Cart's total price should be 200.0f");
        System.out.println("Updated: " + updated);
    }

    @Test
    void testDelete() {
        Cart created = cartService.create(cart1);
        cartService.delete(created.getCartId());
        Cart deletedCart = cartService.read(created.getCartId());
        assertNull(deletedCart, "Deleted Cart should be null");
        System.out.println("Cart deleted where Cart ID: " + created.getCartId());
    }

    @Test
    void testGetAll() {
        cartService.create(cart1);
        Set<Cart> carts = cartService.getAll();
        assertNotNull(carts, "List of Carts should not be null");
        assertFalse(carts.isEmpty(), "List of Carts should not be empty");
        System.out.println("All Carts: " + carts);
    }
}