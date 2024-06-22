package com.api.barber.model.services;

import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.UserEntity;
import com.api.barber.model.repositories.UserRepository;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import com.api.barber.model.services.utils.DateUtil;
import com.api.barber.model.services.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        List<UserEntity> list = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("List of users is empty");
        }
        list.forEach(x -> usersDto.add(UserUtil.convertToDto(x)));
        return usersDto;
    }

    public UserDto findById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return UserUtil.convertToDto(entity.get());
    }

    public UserEntity create(UserDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }

        List<UserEntity> list = userRepository.findAll();
        UserEntity entity = new UserEntity();
        if (!list.isEmpty()) {
            for (UserEntity u : list) {
                if (!u.getEmail().equals(dto.getEmail())) {
                    entity.setEmail(dto.getEmail());
                } else {
                    throw new RuntimeException("Email already registered");
                }
            }
        } else {
            entity.setEmail(dto.getEmail());
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            entity.setBirthdate(sdf.parse(dto.getBirthdateFormat()));
        } catch (ParseException e) {
            throw new RuntimeException("Date is not formating");
        }
        entity.setId(dto.getId());
        entity.setBirthdateFormat(dto.getBirthdateFormat());
        entity.setName(dto.getName());
        entity.setIsAdm(dto.getIsAdm());
        entity.setEnable(dto.getEnable());
        entity.setPassword(dto.getPassword());

        return userRepository.save(entity);
    }

    public UserEntity update(UserDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null) {
            throw new ResourceNotFoundException(null);
        }

        List<UserEntity> listUsers = userRepository.findAll();
        UserEntity entity = userRepository.getReferenceById(dto.getId());

        if (dto.getEmail() != null && !listUsers.isEmpty()){
            for (UserEntity ue: listUsers){
                if (ue.getId().equals(dto.getId())) {
                    if (ue.getEmail().equals(dto.getEmail())) {
                        throw new RuntimeException("Email already registered");
                    }
                    entity.setEmail(dto.getEmail());
                }
            }
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getBirthdateFormat() != null) {
            entity.setBirthdateFormat(dto.getBirthdateFormat());
            entity.setBirthdate(DateUtil.toDateFormat(entity.getBirthdateFormat()));
        }
        if (dto.getPassword() != null) {
            entity.setPassword(dto.getPassword());
        }

        return userRepository.save(entity);
    }

    public UserEntity enableOrDisable(UserDto dto) {
        if (dto == null) {
            throw new NullPointerException("Object is null");
        }
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new ResourceNotFoundException(null);
        }

        UserEntity entity = userRepository.getReferenceById(dto.getId());
        if (entity.getEnable()) {
            entity.setEnable(false);
        } else {
            entity.setEnable(true);
        }

        return userRepository.save(entity);
    }
}
