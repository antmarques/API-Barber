package com.api.barber.model.services;

import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity findById(Long id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        return entity.get();
    }

}
