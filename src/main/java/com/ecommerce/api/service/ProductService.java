package com.ecommerce.api.service;

import com.ecommerce.api.model.dto.ProductDTO;
import com.ecommerce.api.model.entity.Product;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (products != null) {
            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setDescription(product.getDescription());
                productDTO.setUpdatedAt(product.getUpdatedAt());
                productDTO.setCreatedAt(product.getCreatedAt());
                productDTO.setProductId(product.getProductId());
                productDTO.setName(product.getName());
                productDTO.setPrice(product.getPrice());
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
    }

    public ProductDTO findById(Integer id) {
        ProductDTO productDTO = new ProductDTO();
        Product product = repository.findById(id).get();
        if(product != null){
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setStock(product.getStock());
            productDTO.setUpdatedAt(product.getUpdatedAt());
            productDTO.setCreatedAt(product.getCreatedAt());
        }else{
            return null;
        }
        return productDTO;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setId(getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        repository.save(product);
        return productDTO;
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = repository.findById(productDTO.getId()).orElse(null);
        if(product != null){
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setStock(productDTO.getStock());
            product.setUpdatedAt(productDTO.getUpdatedAt());
            repository.save(product);
            return productDTO;
        }else{
            return null;
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public int getId() {
        List<Product> products = repository.findAll();
        if(products.size() > 0){
            return products.stream().max(Comparator.comparing(Product::getId)).get().getId() +1;
        }else{
            return 1;
        }
    }

}
