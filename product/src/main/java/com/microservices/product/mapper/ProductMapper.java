package com.microservices.product.mapper;

import com.microservices.product.model.domain.Product;
import com.microservices.product.model.dto.ProductDTO;
import com.microservices.product.model.dto.ProductRequestDTO;
import com.microservices.product.type.CategoryEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setDescription(productRequestDTO.getDescription());
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(CategoryEnum.valueOf(productRequestDTO.getCategory()));
        return product;
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }
}
