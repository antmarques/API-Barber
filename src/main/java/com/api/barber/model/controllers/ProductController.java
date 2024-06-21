package com.api.barber.model.controllers;

import com.api.barber.model.dto.ProductDto;
import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.services.ProductService;
import org.modelmapper.ModelMapper;
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
        List<ProductDto> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto dto = service.findById(id);
        return ResponseEntity.ok().body(new ModelMapper().map(dto, ProductDto.class));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto product) {
        ProductEntity entity = service.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ModelMapper().map(new ProductDto(entity), ProductDto.class));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto product) {
        ProductEntity entity = service.update(product);
        return ResponseEntity.ok().body(new ModelMapper().map(new ProductDto(entity), ProductDto.class));
    }

    @DeleteMapping(value = "/enableOrDisable")
    public ResponseEntity<ProductDto> enableOrDisable(@RequestBody ProductDto product) {
        ProductEntity entity = service.enableOrDisable(product);
        return ResponseEntity.ok().body(new ModelMapper().map(new ProductDto(entity), ProductDto.class));
    }
}
