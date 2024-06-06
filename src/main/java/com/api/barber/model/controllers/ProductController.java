package com.api.barber.model.controllers;

import com.api.barber.model.dto.ProductDto;
import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
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

    @PostMapping(value = "/create")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto product) {
        ProductEntity entity = service.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(entity));
    }
}
