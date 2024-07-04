package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {

    private List<CartItem> sampleCartItems;

    @BeforeEach
    void setUp() {
        sampleCartItems = new ArrayList<>();

    }

    @Test
    void testCreateCartWithValidInputs() {
        long userId = 12345;
        float totalPrice = 150.0f;

        Cart cart = CartFactory.createCart1(userId, sampleCartItems, totalPrice);

        assertNotNull(cart, "Cart should not be null with valid inputs");
        assertEquals(userId, cart.getUserId(), "User ID should match the input");
        assertEquals(totalPrice, cart.getTotalPrice(), "Total price should match the input");
        assertEquals(sampleCartItems, cart.getItems(), "Cart items should match the input");
    }

    @Test
    void testCreateCartWithNullUserId() {
        long userId = 0;
        float totalPrice = 150.99f;

        Cart cart = CartFactory.createCart1(userId, sampleCartItems, totalPrice);

        assertNull(cart, "Cart should be null when user ID is null or zero");
    }

    @Test
    void testCreateCartWithNullTotalPrice() {
        long userId = 12345;
        float totalPrice = 0.0f;

        Cart cart = CartFactory.createCart1(userId, sampleCartItems, totalPrice);

        assertNull(cart, "Cart should be null when total price is zero or null");
    }

    @Test
    void testCreateCartWithNullOrEmptyCartItems() {
        long userId = 12345;
        float totalPrice = 150.99f;

        Cart cart = CartFactory.createCart1(userId, null, totalPrice);
        assertNotNull(cart, "Cart should not be null even if cart items are null");
        assertNull(cart.getItems(), "Cart items should be null if input is null");

        cart = CartFactory.createCart1(userId, new ArrayList<>(), totalPrice);
        assertNotNull(cart, "Cart should not be null even if cart items list is empty");
        assertTrue(cart.getItems().isEmpty(), "Cart items should be empty if input list is empty");
    }
}