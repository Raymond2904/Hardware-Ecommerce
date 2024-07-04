package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;
import static org.junit.jupiter.api.Assertions.*;

class CartItemFactoryTest {

    private Cart sampleCart;

    @BeforeEach
    void setUp() {
        List<CartItem> cartItems = new ArrayList<>();
        sampleCart = new Cart.Builder()
                .setUserId(12345)
                .setItems(cartItems)
                .setTotalPrice(150.0f)
                .build();
    }

    @Test
    void testCreateCartItemWithValidInputs() {
        long productID = 101;
        int quantity = 2;
        float price = 29f;

        CartItem cartItem = CartItemFactory.createCartItem(sampleCart, productID, quantity, price);

        assertNotNull(cartItem, "CartItem should not be null with valid inputs");
        assertEquals(productID, cartItem.getProductID(), "Product ID should match the input");
        assertEquals(quantity, cartItem.getQuantity(), "Quantity should match the input");
        assertEquals(sampleCart, cartItem.getCart(), "Cart should match the input");
        assertEquals(price, cartItem.getPrice(), "Price should match the input");
    }

    @Test
    void testCreateCartItemWithInvalidProductID() {
        long productID = 0;
        int quantity = 2;
        float price = 29f;

        CartItem cartItem = CartItemFactory.createCartItem(sampleCart, productID, quantity, price);

        assertNull(cartItem, "CartItem should be null when Product ID is zero");
    }

    @Test
    void testCreateCartItemWithInvalidQuantity() {
        long productID = 101;
        int quantity = 0;
        float price = 29f;

        CartItem cartItem = CartItemFactory.createCartItem(sampleCart, productID, quantity, price);

        assertNull(cartItem, "CartItem should be null when Quantity is zero");
    }

    @Test
    void testCreateCartItemWithNullCart() {
        long productID = 101;
        int quantity = 2;
        float price = 29f;

        CartItem cartItem = CartItemFactory.createCartItem(null, productID, quantity, price);

        assertNull(cartItem, "CartItem should be null when Cart is null");
    }

    @Test
    void testCreateCartItemWithInvalidCartUserId() {
        long productID = 101;
        int quantity = 2;
        float price = 29f;
        Cart invalidCart = new Cart.Builder()
                .setUserId(0)
                .setItems(new ArrayList<>())
                .setTotalPrice(100.0f)
                .build();

        CartItem cartItem = CartItemFactory.createCartItem(invalidCart, productID, quantity, price);

        assertNull(cartItem, "CartItem should be null when Cart's user ID is zero");
    }
}