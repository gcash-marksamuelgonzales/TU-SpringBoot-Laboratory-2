package com.example.demo.myfirstapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @DecimalMin(value = "0", inclusive = false, message = "Id should not be less than zero")
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;

    @NotNull(message = "Price should not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price should be greater than zero")
    private Double price;
}
