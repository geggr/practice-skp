package br.com.skillup.payments.carts.requests;

public record CustomerRequest(String name, String email, String phone, String document){}