package com.example.Taskmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {

    private String taskTitle;
    private String taskDescription;
    private Boolean status;
    private LocalDate deadline;
}
