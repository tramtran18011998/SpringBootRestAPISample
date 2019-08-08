package com.demospring.customvalidation.controller;


import com.demospring.customvalidation.model.Product;
import com.demospring.customvalidation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    //list product
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> products= productService.findAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //get by id
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
        Optional<Product> product=productService.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<>(product.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }


    //insert product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder builder){
        productService.save(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    //edit product
    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product){
        Optional<Product> currentProduct=productService.findById(id);
        if(!currentProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProduct.get().setName(product.getName());
        currentProduct.get().setPrice(product.getPrice());
        currentProduct.get().setDescription(product.getDescription());

        productService.save(currentProduct.get());

        return new ResponseEntity<>(currentProduct.get(),HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id){
        Optional<Product> product=productService.findById(id);

        if(!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.remove(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
