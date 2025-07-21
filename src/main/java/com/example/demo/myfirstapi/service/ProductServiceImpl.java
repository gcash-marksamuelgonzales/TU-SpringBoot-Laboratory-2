package com.example.demo.myfirstapi.service;

import com.example.demo.myfirstapi.dto.ProductRequest;
import com.example.demo.myfirstapi.dto.ProductResponse;
import com.example.demo.myfirstapi.model.Product;
import com.example.demo.myfirstapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductResponse mapResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice());
    }

    private Product mapEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        return product;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> findProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapResponse);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapEntity(productRequest);
        Product save = productRepository.save(product);
        return mapResponse(save);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> existing = productRepository.findById(id);

        if(existing.isEmpty()){
            throw new EntityNotFoundException(String.format("Product not found with Id: %s",id));
        }

        Product product = existing.get();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        Product save = productRepository.save(product);
        return mapResponse(save);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
