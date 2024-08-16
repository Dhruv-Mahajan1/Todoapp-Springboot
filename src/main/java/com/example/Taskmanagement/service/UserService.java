
package com.example.Taskmanagement.service;

import com.example.Taskmanagement.dto.Request.NewUserDto;
import com.example.Taskmanagement.dto.Response.CustomResponse;
import com.example.Taskmanagement.dto.Response.UserResponseDTO;
import com.example.Taskmanagement.exception.UserNotFoundException;
import com.example.Taskmanagement.mapper.userDtoUserMapper;
import com.example.Taskmanagement.mapper.userToUserResponseDto;
import com.example.Taskmanagement.models.User;
import com.example.Taskmanagement.repo.UserRepo;
import com.example.Taskmanagement.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService() {
    }

    public CustomResponse<?> register(NewUserDto userDetails) {
        String hashedPassword = this.passwordEncoder.encode(userDetails.getUserPassword());
        userDetails.setUserPassword(hashedPassword);


        try {
            User user = userDtoUserMapper.convert(userDetails);
            User savedUser = (User)this.userRepo.save(user);
            return new CustomResponse<>("User Created", HttpStatus.OK.value(), (Object)null);
        } catch (DataIntegrityViolationException var6) {
            return  new CustomResponse<>("User registration failed: ", HttpStatus.NOT_ACCEPTABLE.value(), (Object)null);
        }


    }

    public CustomResponse<?> authenticateUser(String username, String password) {
        User user = (User)this.userRepo.findByUserName(username).orElseThrow(() -> {
            return new UserNotFoundException("User not found");
        });
        if (this.passwordEncoder.matches(password, user.getUserPassword())) {
            UserResponseDTO userResponseDTO = userToUserResponseDto.convert(user, TokenUtil.generateToken(user));
            return new CustomResponse<>("User found: " + username, HttpStatus.OK.value(), userResponseDTO);
        } else {
            return new CustomResponse<>("Incorrect Credentials ", HttpStatus.NOT_FOUND.value(), (Object)null);
        }
    }
}
