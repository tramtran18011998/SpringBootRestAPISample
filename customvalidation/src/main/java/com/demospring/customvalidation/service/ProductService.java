package com.demospring.customvalidation.service;

import com.demospring.customvalidation.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();
    Optional<Product> findById(Integer id);
    void save(Product product);
    void remove(Product product);
}
