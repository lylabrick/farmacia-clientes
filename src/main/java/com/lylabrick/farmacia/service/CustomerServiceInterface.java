package com.lylabrick.farmacia.service;

import com.lylabrick.farmacia.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerServiceInterface {

    Flux<Customer> findAll();

    Mono<Customer> findById(String id);

    Mono<Customer> create(Customer customer);

    Mono<Customer> update(String id, Customer customerDetails);

    Mono<Void> delete(String id);
}