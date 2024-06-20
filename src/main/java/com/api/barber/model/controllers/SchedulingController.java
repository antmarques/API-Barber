package com.api.barber.model.controllers;

import com.api.barber.model.dto.SchedulingDto;
import com.api.barber.model.entities.SchedulingEntity;
import com.api.barber.model.services.SchedulingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<SchedulingDto>> findAll() {
        List<SchedulingDto> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SchedulingDto> findById(@PathVariable Long id) {
        SchedulingDto dto = service.findById(id);
        return ResponseEntity.ok().body(new ModelMapper().map(dto, SchedulingDto.class));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<SchedulingDto> create(@RequestBody SchedulingDto scheduling) {
        SchedulingEntity entity = service.create(scheduling);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(scheduling.getId()).toUri();
        return ResponseEntity.created(uri).body(new SchedulingDto(entity));
    }
}
