package br.com.skillup.payments.carts;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    
}
