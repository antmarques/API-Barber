package com.api.barber.model.controllers;

import com.api.barber.model.dto.ProductDto;
import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductEntity> listEntity = service.findAll();
        return ResponseEntity.ok().body(listEntity.stream().map(ProductDto::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductEntity entity = service.findById(id);
        return ResponseEntity.ok().body(new ProductDto(entity));
    }
}
