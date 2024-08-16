package com.example.Taskmanagement.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse<T> {

    private String message;
    private int status;
    private T data;
}
