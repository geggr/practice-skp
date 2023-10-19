package br.com.skillup.payments.carts.requests;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import br.com.skillup.payments.carts.Cart;
import br.com.skillup.payments.carts.CartItem;
import br.com.skillup.payments.products.Product;
import jakarta.validation.constraints.NotNull;

public record CreateCartRequest(@NotNull(message = "customer should not be null") CustomerRequest customer, List<CartItemsRequest> items) {

    public Cart toEntity(){
        final var cart = new Cart(
            customer().name(),
            customer().email(),
            customer().document(),
            customer().phone()
        );

        return cart;
    }

    public List<CartItem> mapCartItemsToEntity(Cart cart, Map<Long, Product> products){
        if (items == null) return null;

        return items
            .stream()
            .filter(item -> products.containsKey(item.productId()))
            .map(item -> item.toEntity(cart, products.get(item.productId())))
            .toList();
    }

    public Stream<Long> extractProductIds(){
        return items.stream().map(CartItemsRequest::productId);
    }

}
