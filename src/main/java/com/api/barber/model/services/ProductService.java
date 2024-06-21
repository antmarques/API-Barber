package com.api.barber.model.services;

import com.api.barber.model.dto.ProductDto;
import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.repositories.ProductRepository;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import com.api.barber.model.services.utils.NumberUtil;
import com.api.barber.model.services.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> findAll() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductDto> dtoList = new ArrayList<>();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("List of products is empty");
        }
        list.forEach(x -> dtoList.add(ProductUtil.convertToDto(x)));
        return dtoList;
    }

    public ProductDto findById(Long id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return ProductUtil.convertToDto(entity.get());
    }

    public ProductEntity create(ProductDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getName() == null && dto.getPrice() == null) {
            throw new RuntimeException("Name or Price can't be empty");
        }
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPriceFormat(dto.getPriceFormat());
        entity.setPrice(NumberUtil.numberFormatBr(entity.getPriceFormat()));
        entity.setEnable(dto.getEnable());

        return productRepository.save(entity);
    }

    public ProductEntity update(ProductDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new ResourceNotFoundException(dto.getId());
        }

        ProductEntity entity = productRepository.getReferenceById(dto.getId());
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getPriceFormat() != null) {
            entity.setPriceFormat(dto.getPriceFormat());
            entity.setPrice(NumberUtil.numberFormatBr(entity.getPriceFormat()));
        }
        return productRepository.save(entity);
    }

    public ProductEntity enableOrDisable(ProductDto dto){
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new ResourceNotFoundException(dto.getId());
        }

        ProductEntity entity = productRepository.getReferenceById(dto.getId());
        if (entity.getEnable()) {
            entity.setEnable(false);
        } else {
            entity.setEnable(true);
        }
        return productRepository.save(entity);
    }
}
