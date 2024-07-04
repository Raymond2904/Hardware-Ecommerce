package za.ac.cput.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemID;
    private long productID;
    @ManyToOne
    @JoinColumn(name = "CartId", nullable = false)
    private Cart cart;
    private int quantity;
    private float price;

    protected CartItem() {
    }

    public CartItem(Builder builder) {
        this.cartItemID = builder.cartItemID;
        this.productID = builder.productID;
        this.cart = builder.cart;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public long getCartItemID() {
        return cartItemID;
    }

    public long getProductID() {
        return productID;
    }

    public Cart getCart() {
        return cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity &&
                Float.compare(cartItem.price, price) == 0 &&
                Objects.equals(cartItemID, cartItem.cartItemID) &&
                Objects.equals(productID, cartItem.productID) &&
                Objects.equals(cart, cartItem.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemID, productID, cart, quantity, price);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemID +
                ", productID=" + productID +
                ", cart=" + cart +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private long cartItemID;
        private long productID;
        private Cart cart;
        private int quantity;
        private float price;

        public Builder setCartItemID(long cartItemID) {
            this.cartItemID = cartItemID;
            return this;
        }

        public Builder setProductID(long productID) {
            this.productID = productID;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemID = cartItem.getCartItemID();
            this.productID = cartItem.getProductID();
            this.cart = cartItem.getCart();
            this.quantity = cartItem.getQuantity();
            this.price = cartItem.getPrice();
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}

