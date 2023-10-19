package br.com.skillup.payments.products;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "products")
public class Product {
    
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name="code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;


    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "licence_months")
    private Integer licenceMonths;

    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Product(){}

    public Product(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLicenceMonths() {
        return licenceMonths;
    }

    public void setLicenceMonths(Integer licenceMonths) {
        this.licenceMonths = licenceMonths;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return """
            {
                "code": %s,
                "name": %s,
                "price": %s,
                "licence_months": %d
            }
        """.stripIndent().formatted(code, name, price, licenceMonths);
    }

    

}
