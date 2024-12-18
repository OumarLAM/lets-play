package com.example.oulam.lets_play.service;

import com.example.oulam.lets_play.exception.ValidationException;
import com.example.oulam.lets_play.model.Product;
import com.example.oulam.lets_play.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(@Valid Product product) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ValidationException("name", "Product name already exists");
        }

        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (productRepository.findByName(updatedProduct.getName())
                            .filter(p -> !p.getId().equals(id))
                            .isPresent()) {
                        throw new ValidationException("name", "Product name already exists");
                    }
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setUserId(updatedProduct.getUserId());

                    return productRepository.save(existingProduct);
                });
    }

    @Transactional
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
