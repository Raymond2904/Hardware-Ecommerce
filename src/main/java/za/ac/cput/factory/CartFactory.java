package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.util.List;

public class CartFactory {

    public static Cart createCart1(long userId, List<CartItem> cartItems, float totalPrice) {

        if(Helper.isNullOrEmpty(String.valueOf(userId)) || Helper.isNullOrEmpty(String.valueOf(totalPrice))){
            return null;
        }

        return new Cart.Builder()
                .setUserId(userId)
                .setItems(cartItems)
                .setTotalPrice((totalPrice))
                .build();
    }
}