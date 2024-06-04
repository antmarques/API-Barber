package com.api.barber.model.controllers;

import com.api.barber.model.dto.SchedulingDto;
import com.api.barber.model.entities.SchedulingEntity;
import com.api.barber.model.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SchedulingDto>> findAll() {
        List<SchedulingEntity> listEntity = service.findAll();
        return ResponseEntity.ok().body(listEntity.stream().map(SchedulingDto::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SchedulingDto> findById(@PathVariable Long id) {
        SchedulingEntity entity = service.findById(id);
        return ResponseEntity.ok().body(new SchedulingDto(entity));
    }
}
