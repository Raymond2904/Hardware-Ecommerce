package za.ac.cput.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private Long userId;
    private Double totalPrice;

    protected Cart() {}

    private Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.userId = builder.userId;
        this.totalPrice = builder.totalPrice;
        this.items = builder.items;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public List<CartItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) &&
                Objects.equals(userId, cart.userId) &&
                Objects.equals(totalPrice, cart.totalPrice) &&
                Objects.equals(items, cart.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userId, totalPrice, items);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private Long cartId;
        private Long userId;
        private Double totalPrice;
        private List<CartItem> items = new ArrayList<>();

        public Builder setCartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setItems(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.userId = cart.userId;
            this.totalPrice = cart.totalPrice;
            this.items = cart.items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}


