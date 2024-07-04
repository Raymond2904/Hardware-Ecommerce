package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.util.Helper;

public class CartItemFactory {

    public static CartItem createCartItem(Cart cart , long productID, int quantity,  float price) {

        if ( Helper.isNullOrEmpty(String.valueOf(productID))
               || Helper.isNullOrEmpty(String.valueOf(quantity))
               || Helper.isNullOrEmpty(String.valueOf(cart.getUserId()))
                || Helper.isNullOrEmpty(String.valueOf(price)) ) {
           return null;
        }

        return new CartItem.Builder()
                .setProductID(productID)
                .setQuantity(quantity)
                .setCart(cart)
                .setPrice(price)
                .build();
    }
}


