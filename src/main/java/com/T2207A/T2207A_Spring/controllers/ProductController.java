package com.T2207A.T2207A_Spring.controllers;

import com.T2207A.T2207A_Spring.entities.Product;
import com.T2207A.T2207A_Spring.models.ResponseObject;
import com.T2207A.T2207A_Spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Ok", product)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Can't not find product with id "+id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product model) {
        List<Product> foundProducts = productRepository.findByName(model.getName());
        if (foundProducts.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("false", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("true", "Ok", productRepository.save(model))
        );
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product model, @PathVariable Long id) {
        Product updateProduct = productRepository.findById(id)
                .map(product -> {
                    product.setName(model.getName());
                    product.setPrice(model.getPrice());
                    product.setDescription(model.getDescription());
                    product.setThumbnail(model.getThumbnail());
                    product.setQty(model.getQty());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    model.setId(id);
                    return productRepository.save(model);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("true", "Update Product successfully", updateProduct)
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Delete Product successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("False", "Not Found", "")
            );
        }
    }
}
