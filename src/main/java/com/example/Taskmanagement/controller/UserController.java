

package com.example.Taskmanagement.controller;


import com.example.Taskmanagement.dto.LoginDto;
import com.example.Taskmanagement.dto.Request.NewUserDto;
import com.example.Taskmanagement.dto.Response.CustomResponse;
import com.example.Taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/api/v1/user"})
public class UserController {
    @Autowired
    UserService userService;

    public UserController() {
    }

    @PostMapping({"/register"})
    public ResponseEntity<?> register(@RequestBody NewUserDto userDetails) throws DataIntegrityViolationException {
        CustomResponse<?> customResponse = this.userService.register(userDetails);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> authenticateUser1(@RequestBody LoginDto loginDto) {
        CustomResponse<?> customResponse = this.userService.authenticateUser(loginDto.getUsername(), loginDto.getPassword());
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }
}
