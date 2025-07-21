package com.example.demo.myfirstapi.service;

import com.example.demo.myfirstapi.dto.ProductRequest;
import com.example.demo.myfirstapi.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<ProductResponse> findAllProducts();
    Optional<ProductResponse> findProductById(Long id);
    ProductResponse createProduct(ProductRequest product);
    ProductResponse updateProduct(Long id, ProductRequest product);
    void deleteProduct(Long id);
}
