package com.T2207A.T2207A_Spring.services;

import com.T2207A.T2207A_Spring.entities.Product;
import com.T2207A.T2207A_Spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        return productRepository.findById(id)
                .map(p-> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setQty(product.getQty());
                    p.setThumbnail(product.getThumbnail());
                    p.setDescription(product.getDescription());
                    return productRepository.save(p);
                })
                .orElseGet(()->{
                    product.setId(id);
                    return productRepository.save(product);
                });
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String search){
        return productRepository.findAllByNameContainingOrDescriptionContaining(search, search);
    }

    public List<Product> filterProducts(String name,Integer minPrice,Integer maxPrice){
        return productRepository.filterProducts(name,minPrice,maxPrice);
    }
}