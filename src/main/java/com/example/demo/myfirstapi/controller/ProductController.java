package com.example.demo.myfirstapi.controller;

import com.example.demo.myfirstapi.dto.ProductRequest;
import com.example.demo.myfirstapi.dto.ProductResponse;
import com.example.demo.myfirstapi.model.Product;
import com.example.demo.myfirstapi.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Retrieve All
    @GetMapping
    public List<ProductResponse> getAll(){
        return productService.findAllProducts();
    }

    // Retrieve Product using ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable Long id){
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    // Add New Product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        return productService.createProduct(productRequest);
    }

    // Delete from Product List using ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    // Update Product using ID
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest){
        try{
            ProductResponse productResponse = productService.updateProduct(id, productRequest);
            return productResponse;
        } catch(EntityNotFoundException ex){
            return new ProductResponse();
        }

    }

}
