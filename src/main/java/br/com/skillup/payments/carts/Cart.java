package br.com.skillup.payments.carts;

import static jakarta.persistence.GenerationType.UUID;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "carts")
public class Cart {

    @Id @GeneratedValue(strategy = UUID)
    private UUID id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_document")
    private String customerDocument;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    public Cart(){}

    public Cart(String customerName, String customerEmail, String customerDocument, String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerDocument = customerDocument;
        this.customerPhone = customerPhone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerDocument() {
        return customerDocument;
    }

    public void setCustomerDocument(String customerDocument) {
        this.customerDocument = customerDocument;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addItemsAndCalculateAmount(List<CartItem> items){
        this.items = items;
        this.amount = calculateTotalAmount();
    }

    public BigDecimal calculateTotalAmount(){
        return items
            .stream()
            .map(CartItem::amount)
            .reduce(BigDecimal.ZERO, (accumulator, current) -> accumulator.add(current));
    }

    public void addItem(CartItem item) {
        items.add(item);
        this.amount = this.amount.add(item.amount(), MathContext.DECIMAL64);
    }
}
