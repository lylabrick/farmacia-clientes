package com.lylabrick.farmacia.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Customer entity representing a pharmacy customer")
public class Customer {

    @Id
    @Schema(description = "Unique identifier of the customer", example = "507f1f77bcf86cd799439011", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    
    @Schema(description = "Full name of the customer", example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    
    @Schema(description = "Email address of the customer", example = "john.doe@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    
    @Schema(description = "Phone number of the customer", example = "+54 11 1234-5678")
    private String phone;
    
    @Schema(description = "Physical address of the customer", example = "Av. Corrientes 1234, Buenos Aires")
    private String address;
    
    @Schema(description = "Date and time when the customer was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
