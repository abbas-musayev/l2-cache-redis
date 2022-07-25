package com.example.l2cacheredis.controller;

import com.example.l2cacheredis.dto.UserRequestDto;
import com.example.l2cacheredis.dto.UserResponseDto;
import com.example.l2cacheredis.entity.User;
import com.example.l2cacheredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDto dto){
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return ResponseEntity.ok(userService.getALlUsers());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<UserResponseDto> getByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }




}
