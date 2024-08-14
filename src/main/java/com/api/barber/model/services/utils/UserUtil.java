package com.api.barber.model.services.utils;

import com.api.barber.model.dto.UserDto;
import com.api.barber.model.entities.UserEntity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String encrypterPassword(String password) {
        String encripted;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigestSenhaAdmin[] = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexStringSenhaAdmin = new StringBuilder();
            for (byte b : messageDigestSenhaAdmin) {
                hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
            }
            encripted = hexStringSenhaAdmin.toString();

            return encripted;
        } catch (NoSuchAlgorithmException ex){
            return ex.getMessage();
        }
    }
}
