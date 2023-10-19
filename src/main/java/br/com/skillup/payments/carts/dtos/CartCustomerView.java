package br.com.skillup.payments.carts.dtos;

import br.com.skillup.payments.carts.Cart;

record CartCustomerView(String name, String email){
    public CartCustomerView(Cart cart){
        this(cart.getCustomerName(), cart.getCustomerEmail());
    }
}