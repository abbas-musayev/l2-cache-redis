package com.example.l2cacheredis.service;

import com.example.l2cacheredis.dto.UserRequestDto;
import com.example.l2cacheredis.dto.UserResponseDto;

import java.util.List;

public interface UserService {


    void createUser(UserRequestDto dto);

    List<UserResponseDto> getALlUsers();

    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByName(String name);

}
