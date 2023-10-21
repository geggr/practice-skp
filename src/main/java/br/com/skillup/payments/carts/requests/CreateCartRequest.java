package br.com.skillup.payments.carts.requests;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.skillup.payments.carts.Cart;
import br.com.skillup.payments.carts.CartItem;
import br.com.skillup.payments.products.Product;

public record CreateCartRequest(CustomerRequest customer, List<CartItemRequest> items) {

    public Cart toEntity() {
        return Optional.ofNullable(customer).map(customer -> new Cart(
                customer.name(),
                customer.email(),
                customer.document(),
                customer.phone()))
                .orElse(new Cart());
    }

    public List<CartItem> mapCartItemsToEntity(Cart cart, Map<Long, Product> products){
        return Optional.ofNullable(items)
            .map(it -> it
                    .stream()
                    .filter(item -> products.containsKey(item.productId()))
                    .map(item -> item.toEntity(cart, products.get(item.productId())))
                    .toList())
            .orElse(List.of());
    }

    public Stream<Long> extractProductIds() {
        return items.stream().map(CartItemRequest::productId);
    }

}
