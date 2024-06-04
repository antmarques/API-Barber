package com.api.barber.model.services;

import com.api.barber.model.entities.SchedulingEntity;
import com.api.barber.model.repositories.SchedulingRepository;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    public List<SchedulingEntity> findAll() {
        return schedulingRepository.findAll();
    }

    public SchedulingEntity findById(Long id) {
        Optional<SchedulingEntity> entity = schedulingRepository.findById(id);
        return entity.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
