package com.example.l2cacheredis.service.impl;

import com.example.l2cacheredis.dto.UserRequestDto;
import com.example.l2cacheredis.dto.UserResponseDto;
import com.example.l2cacheredis.entity.User;
import com.example.l2cacheredis.mapper.UserMapper;
import com.example.l2cacheredis.repo.UserRepo;
import com.example.l2cacheredis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void createUser(UserRequestDto dto) {
        User user = UserMapper.INSTANCE.requestToEnt(dto);
        userRepo.save(user);
    }

    @Cacheable(cacheNames = "accountById", key = "all")
    @Override
    public List<UserResponseDto> getALlUsers() {
        List<User> all = userRepo.findAll();
        List<UserResponseDto> map = UserMapper.INSTANCE.map(all);
        return map;
    }


    @Override
    public UserResponseDto getUserById(Long id) {
        User user = (User) redisTemplate.opsForValue().get(id);
        if (user == null) {
            log.info("DATA NOT FOUND FROM REDIS TRYING FROM DATABASE....");
            user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found -> id= {}" + id));
            redisTemplate.opsForValue().set(String.valueOf(id), user);
        }
        return UserMapper.INSTANCE.entToResponse(user);
    }

    @Override
    public UserResponseDto getUserByName(String name) {
        User user = (User) redisTemplate.opsForValue().get(name);
        if (user == null) {
            log.info("DATA NOT FOUND FROM REDIS TRYING FROM DATABASE....");
            user = userRepo.findByName();
            redisTemplate.opsForValue().set(name, user, Duration.ofMinutes(30));
        }
        return UserMapper.INSTANCE.entToResponse(user);
    }
}
