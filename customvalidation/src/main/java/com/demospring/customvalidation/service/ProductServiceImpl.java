package com.demospring.customvalidation.service;

import com.demospring.customvalidation.model.Product;
import com.demospring.customvalidation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl (ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Product product) {
        productRepository.delete(product);
    }
}
