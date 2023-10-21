package br.com.skillup.payments.carts;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.skillup.payments.carts.dtos.CartView;
import br.com.skillup.payments.carts.requests.CartItemRequest;
import br.com.skillup.payments.carts.requests.CreateCartRequest;
import br.com.skillup.payments.products.Product;
import br.com.skillup.payments.products.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class CartService {
    
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartView> fetchAllCarts(){
        return cartRepository.findAll().stream().map(CartView::of).toList(); 
    }
    
    public Cart createCartWithItems(CreateCartRequest request){
        final var products = fetchCartProducts(request);
        final var cart = request.toEntity();
        final var items = request.mapCartItemsToEntity(cart, products);
        
        cart.addItemsAndCalculateAmount(items);

        return saveCart(cart);
    }

    public Cart addItemToCart(UUID cartId, CartItemRequest request){
        final var cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalStateException("Cart doesn't exists"));
        final var product = productRepository.findById(request.productId()).orElseThrow(() -> new IllegalStateException("Product doesn't exists"));
        
        final var item = request.toEntity(cart, product);

        cart.addItem(item);

        final var created = saveCart(cart);

        return created;
    }

    @Transactional
    private Cart saveCart(Cart cart){
        return cartRepository.saveAndFlush(cart);
    }

    private Map<Long, Product> fetchCartProducts(CreateCartRequest request){
        final var products = request.extractProductIds().collect(collectingAndThen(toSet(), productRepository::findAllById));
        
        return products.stream().collect(Collectors.toMap(Product::getId, identity()));
    }
}
