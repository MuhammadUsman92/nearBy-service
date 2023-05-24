package com.muhammadusman92.nearbyservice.services.impl;

import com.muhammadusman92.nearbyservice.config.ConversionDtos;
import com.muhammadusman92.nearbyservice.entity.Location;
import com.muhammadusman92.nearbyservice.entity.User;
import com.muhammadusman92.nearbyservice.exception.AlreadyExistExeption;
import com.muhammadusman92.nearbyservice.exception.ResourceNotFoundException;
import com.muhammadusman92.nearbyservice.payload.UserDto;
import com.muhammadusman92.nearbyservice.repo.LocationRepo;
import com.muhammadusman92.nearbyservice.repo.UserRepo;
import com.muhammadusman92.nearbyservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = ConversionDtos.userDtoToUser(userDto);
        if(userRepo.existsUserByEmail(user.getEmail())){
            throw new AlreadyExistExeption("User account with email",user.getEmail());
        }
        user.getLocation().setUser(user);
//        Location location = locationRepo.save(user.getLocation());
//        user.getLocation().setId(location.getId());
        return ConversionDtos.userToUserDto(userRepo.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = ConversionDtos.userDtoToUser(userDto);
        User findUser = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
        user.setId(findUser.getId());
        return ConversionDtos.userToUserDto(userRepo.save(user));
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User findUser = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
        return ConversionDtos.userToUserDto(userRepo.save(findUser));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtoList = userList.stream().map(ConversionDtos::userToUserDto).toList();
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User findUser = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
        userRepo.delete(findUser);
    }
}
