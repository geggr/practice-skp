package br.com.skillup.payments.carts.dtos;

import br.com.skillup.payments.carts.CartItem;

record CartItemsView(String product, Integer quantity){
    public CartItemsView(CartItem item){
        this(item.getProductName(), item.getQuantity());
    }
}
