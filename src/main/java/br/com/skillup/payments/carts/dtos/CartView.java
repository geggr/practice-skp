package br.com.skillup.payments.carts.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.skillup.payments.carts.Cart;

public record CartView(UUID id, BigDecimal amount, CartCustomerView customer, List<CartItemsView> items) {
    public static CartView of(Cart cart){
        final var items = cart
            .getItems()
            .stream()
            .map(CartItemsView::new)
            .toList();

        return new CartView(
            cart.getId(),
            cart.calculateTotalAmount(),
            new CartCustomerView(cart),
            items
        );
    }
}
