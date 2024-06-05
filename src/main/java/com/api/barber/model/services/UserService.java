package com.api.barber.model.services;

import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.UserEntity;
import com.api.barber.model.repositories.UserRepository;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import com.api.barber.model.services.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {
        List<UserEntity> list = userRepository.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("List of users is empty");
        }
        list.forEach(x -> {
            if (x != null) {
                x.setBirthdateFormat(DateUtil.toStringFormat(x.getBirthdate()));
            }
        });
        return list;
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        entity.ifPresent(userEntity -> userEntity.setBirthdateFormat(DateUtil.toStringFormat(userEntity.getBirthdate())));
        return entity.orElseThrow(() -> new ResourceNotFoundException(id));
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
}
