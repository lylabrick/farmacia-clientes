package com.lylabrick.farmacia.controller;

import com.lylabrick.farmacia.entity.Customer;
import com.lylabrick.farmacia.service.CustomerServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Customers", description = "Customer management API endpoints")
public class CustomerController {

    private final CustomerServiceInterface customerService;

    @GetMapping
    @Operation(summary = "Get all customers", description = "Returns a list of all customers in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of customers",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)))
    })
    public Flux<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Returns a single customer by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public Mono<ResponseEntity<Customer>> getCustomerById(
            @Parameter(description = "Unique identifier of the customer", example = "507f1f77bcf86cd799439011")
            @PathVariable String id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Mono<ResponseEntity<Customer>> createCustomer(
            @Parameter(description = "Customer details to create")
            @Valid @RequestBody Customer customer) {
        return customerService.create(customer)
                .map(savedCustomer -> ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing customer", description = "Updates all fields of an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public Mono<ResponseEntity<Customer>> updateCustomer(
            @Parameter(description = "Unique identifier of the customer to update", example = "507f1f77bcf86cd799439011")
            @PathVariable String id,
            @Parameter(description = "Updated customer details")
            @Valid @RequestBody Customer customer) {
        return customerService.update(id, customer)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public Mono<ResponseEntity<Void>> deleteCustomer(
            @Parameter(description = "Unique identifier of the customer to delete", example = "507f1f77bcf86cd799439011")
            @PathVariable String id) {
        return customerService.delete(id)
                .thenReturn(ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
