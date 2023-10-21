package br.com.skillup.payments.carts.requests;

import java.util.List;

public record UpdateCartRequest(CustomerRequest customer, List<CartItemRequest> items) {}