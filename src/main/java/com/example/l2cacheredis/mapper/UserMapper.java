package com.example.l2cacheredis.mapper;

import com.example.l2cacheredis.dto.UserRequestDto;
import com.example.l2cacheredis.dto.UserResponseDto;
import com.example.l2cacheredis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //    @Mapping(source = "numberOfSeats", target = "seatCount")
    UserResponseDto entToResponse(User ent);

    //    @Mapping(source = "numberOfSeats", target = "seatCount")
    User requestToEnt(UserRequestDto dto);


    List<UserResponseDto> map(List<User> employees);

//    default UserResponseDto map(User employee) {
//        UserResponseDto dto = new UserResponseDto();
//        dto.set(employee.getFirstName() + " " + employee.getLastName());
//
//        return employeeInfoDTO;
//    }
}
