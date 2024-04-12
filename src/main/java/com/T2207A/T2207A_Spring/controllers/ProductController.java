package com.T2207A.T2207A_Spring.controllers;

import com.T2207A.T2207A_Spring.entities.Product;
import com.T2207A.T2207A_Spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    List<Product> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping()
    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> search(String search){
        return productService.searchProduct(search);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer minPrice,
                                @RequestParam(required = false) Integer maxPrice){
        return productService.filterProducts(name,minPrice,maxPrice);
    }
}
