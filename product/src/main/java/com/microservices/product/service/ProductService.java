package com.microservices.product.service;

import com.microservices.product.mapper.ProductMapper;
import com.microservices.product.model.dto.ProductDTO;
import com.microservices.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.toDTO(product))
                .toList();
    }

    public ProductDTO getProductById(int id) {
        return productMapper.toDTO(productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Product with id " + id + " not found")));
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(product -> productMapper.toDTO(product))
                .toList();
    }
}
