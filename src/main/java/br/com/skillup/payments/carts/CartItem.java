package br.com.skillup.payments.carts;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

import br.com.skillup.payments.products.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "cart_items")
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public CartItem(){}

    public CartItem(Long id, Cart cart, Product product, Integer quantity){
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName(){
        return Optional.ofNullable(product).map(Product::getName).orElse("");
    }

    public BigDecimal amount(){
        return Optional.ofNullable(product)
            .map(product -> product.getPrice().multiply(BigDecimal.valueOf(quantity), MathContext.DECIMAL64))
            .orElse(BigDecimal.ZERO);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
