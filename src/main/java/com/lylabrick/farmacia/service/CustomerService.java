package com.lylabrick.farmacia.service;

import com.lylabrick.farmacia.entity.Customer;
import com.lylabrick.farmacia.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    public Mono<Customer> create(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    public Mono<Customer> update(String id, Customer customerDetails) {
        return customerRepository.findById(id)
                .flatMap(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    customer.setPhone(customerDetails.getPhone());
                    customer.setAddress(customerDetails.getAddress());
                    return customerRepository.save(customer);
                });
    }

    public Mono<Void> delete(String id) {
        return customerRepository.deleteById(id);
    }
}
