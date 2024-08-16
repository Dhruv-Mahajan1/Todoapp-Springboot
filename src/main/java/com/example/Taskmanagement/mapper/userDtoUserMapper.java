package com.example.Taskmanagement.mapper;


import com.example.Taskmanagement.dto.Request.NewUserDto;
import com.example.Taskmanagement.models.User;

public class userDtoUserMapper {
   public static User convert(NewUserDto userDto) {
      User user = new User();
      user.setUserName(userDto.getUserName());
      user.setUserPassword(userDto.getUserPassword());
      return user;
   }
}
