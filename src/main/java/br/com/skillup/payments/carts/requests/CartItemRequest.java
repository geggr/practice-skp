package br.com.skillup.payments.carts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.skillup.payments.carts.Cart;
import br.com.skillup.payments.carts.CartItem;
import br.com.skillup.payments.products.Product;

public record CartItemRequest(@JsonProperty("item_id") Long itemId, @JsonProperty("product_id") Long productId, Integer quantity){
    
    public CartItem toEntity(Cart cart, Product product){
        return new CartItem(itemId, cart, product, quantity);
    }
    
}
