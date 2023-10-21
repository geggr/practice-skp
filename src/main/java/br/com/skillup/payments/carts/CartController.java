package br.com.skillup.payments.carts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skillup.payments.carts.dtos.CartView;
import br.com.skillup.payments.carts.requests.CartItemRequest;
import br.com.skillup.payments.carts.requests.CreateCartRequest;
import br.com.skillup.payments.carts.requests.UpdateCartRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartView>> list(){
        final var carts = cartService.fetchAllCarts();

        return ResponseEntity.ok(carts);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateCartRequest request){
        final var cart = cartService.createCartWithItems(request);

        return ResponseEntity.ok(CartView.of(cart));
    }

    @PostMapping("/items/{id}/add")
    public ResponseEntity<?> add(@PathVariable("id") UUID cartId, @RequestBody @Valid CartItemRequest request){
        final var cart = cartService.addItemToCart(cartId, request);

        return ResponseEntity.ok(CartView.of(cart));
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody UpdateCartRequest request){
        
        return null;
    }
}
