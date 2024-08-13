package com.api.barber.model.services;

import com.api.barber.model.dto.ItemSchedulingDto;
import com.api.barber.model.dto.SchedulingDto;
import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.ItemSchedulingEntity;
import com.api.barber.model.entities.ProductEntity;
import com.api.barber.model.entities.SchedulingEntity;
import com.api.barber.model.entities.UserEntity;
import com.api.barber.model.repositories.ItemSchedulingRepository;
import com.api.barber.model.repositories.ProductRepository;
import com.api.barber.model.repositories.SchedulingRepository;
import com.api.barber.model.repositories.UserRepository;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import com.api.barber.model.services.utils.DateUtil;
import com.api.barber.model.services.utils.SchedulingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemSchedulingRepository itemSchedulingRepository;

    public List<SchedulingDto> findAll() {
        List<SchedulingDto> schedulingDto = new ArrayList<>();
        List<SchedulingEntity> schedulingEntity = schedulingRepository.findAll();
        if (schedulingEntity.isEmpty()) {
            throw new ResourceNotFoundException(null);
        }

        for (SchedulingEntity se: schedulingEntity) {
            schedulingDto.add(SchedulingUtil.convertToDto(se));
        }
        return schedulingDto;
    }

    public SchedulingDto findById(Long id) {
        Optional<SchedulingEntity> entity = schedulingRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return SchedulingUtil.convertToDto(entity.get());
    }

    public List<SchedulingDto> findByUser(UserDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null) {
            throw new ResourceNotFoundException(null);
        }

        List<SchedulingDto> listDto = new ArrayList<>();
        List<SchedulingEntity> listEntity = schedulingRepository.findAll();
        UserEntity userEntity = userRepository.getReferenceById(dto.getId());

        if (!listEntity.isEmpty()) {
            for (SchedulingEntity se: listEntity) {
                if (se.getUser().getId().equals(userEntity.getId())) {
                    listDto.add(SchedulingUtil.convertToDto(se));
                } else {
                    throw new ResourceNotFoundException(se.getUser().getId());
                }
            }
        }
        return listDto;
    }

    public SchedulingEntity create(SchedulingDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getDescription() == null && dto.getDateFormat() == null && dto.getItems() == null) {
            throw new RuntimeException("Required fields are empty");
        }

        UserEntity user = userRepository.getReferenceById(dto.getUser().getId());
        ProductEntity product = new ProductEntity();
        for (ItemSchedulingDto isDto: dto.getItems()) {
            if (isDto.getProduct().getId() != null) {
                product = productRepository.getReferenceById(isDto.getProduct().getId());
            }
        }
        SchedulingEntity entity = new SchedulingEntity();
        ItemSchedulingEntity itemEntity = new ItemSchedulingEntity();
        List<ItemSchedulingEntity> listItens = new ArrayList<>();
        List<SchedulingEntity> listScheduling = new ArrayList<>();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setDate(DateUtil.toDateTimeFormat(dto.getDateFormat()));
        entity.setEnable(dto.getEnable());
        entity.setUser(user);
        for (ItemSchedulingDto isDto: dto.getItems()) {
            itemEntity.setProduct(product);
            itemEntity.setQuantity(isDto.getQuantity());
            itemEntity.setId(isDto.getId());
            itemEntity.setPrice(itemEntity.getSubTotal());
            itemEntity.setEnable(isDto.getEnable());
            itemEntity.setScheduling(entity);
            listItens.add(itemEntity);
        }
        product.setItems(listItens);
        entity.setItems(listItens);
        listScheduling.add(entity);
        user.setShedulings(listScheduling);

        itemSchedulingRepository.save(itemEntity);
        productRepository.save(product);
        userRepository.save(user);
        schedulingRepository.save(entity);
        return entity;
    }

    public SchedulingEntity activeOrDisable(SchedulingDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null) {
            throw new ResourceNotFoundException(null);
        }

        SchedulingEntity entity = schedulingRepository.getReferenceById(dto.getId());
        if (entity.getEnable()) {
            entity.setEnable(false);
        } else {
            entity.setEnable(true);
        }

        return schedulingRepository.save(entity);
    }
}
