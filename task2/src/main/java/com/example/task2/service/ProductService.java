package com.example.task2.service;

import com.example.task2.entity.Product;
import com.example.task2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    // Define allowed categories
    private static final List<String> VALID_CATEGORIES = Arrays.asList("Electronics", "Clothing", "Accessories");

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        // Ensure name is not null or empty
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        // Ensure price is not null or negative
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price is required and must be positive");
        }
        // Validate category
        if (!VALID_CATEGORIES.contains(product.getCategory())) {
            throw new IllegalArgumentException("Invalid category");
        }
        // Check for duplicate name
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with this name already exists");
        }

        return productRepository.save(product);
    }
    public List<Product> createMultipleProducts(List<Product> products) {
        for (Product product : products) {
            createProduct(product);
        }
        return productRepository.saveAll(products);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false; // This should trigger "Product not found" in the controller
    }


}