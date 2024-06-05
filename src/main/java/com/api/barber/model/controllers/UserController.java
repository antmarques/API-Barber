package com.api.barber.model.controllers;

import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.UserEntity;
import com.api.barber.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserEntity> entityList = service.findAll();
        return ResponseEntity.ok().body(entityList.stream().map(UserDto::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserEntity entity = service.findById(id);
        return ResponseEntity.ok().body(new UserDto(entity));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        UserEntity entity = service.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(entity));
    }
}
