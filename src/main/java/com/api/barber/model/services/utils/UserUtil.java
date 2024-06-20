package com.api.barber.model.services.utils;

import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.UserEntity;

public class UserUtil {

    public static UserDto convertToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthdate(entity.getBirthdate());
        dto.setBirthdateFormat(DateUtil.toStringFormat(entity.getBirthdate()));
        dto.setEmail(entity.getEmail());
        dto.setIsAdm(entity.getIsAdm());
        dto.setEnable(entity.getEnable());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
