package com.example.Taskmanagement.mapper;


import com.example.Taskmanagement.dto.Response.UserResponseDTO;
import com.example.Taskmanagement.models.User;

public class userToUserResponseDto {
   public static UserResponseDTO convert(User user, String token) {
      UserResponseDTO userResponseDTO = new UserResponseDTO();
      userResponseDTO.setUserName(user.getUserName());
      userResponseDTO.setToken(token);
      return userResponseDTO;
   }
}
