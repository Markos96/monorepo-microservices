package com.microservices.product.controller;

import com.microservices.product.model.dto.ProductDTO;
import com.microservices.product.model.dto.ProductRequestDTO;
import com.microservices.product.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<ProductDTO> getProducts() {
        return this.productService.getProducts();
    }

    @QueryMapping
    public ProductDTO getProductById(@Argument Integer id) {
        return this.productService.getProductById(id);
    }

    @QueryMapping
    public List<ProductDTO> getProductByCategory(@Argument String category) {
        return this.productService.getProductsByCategory(category);
    }

    @MutationMapping
    public ProductDTO addProduct(@Argument ProductRequestDTO newProduct) {
        return this.productService.saveProduct(newProduct);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Integer id) {
        return this.productService.deleteProductById(id);
    }
}
