package com.example.Taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDto {
    int taskId;
    Timestamp createdAt;
    String taskTitle;
    String taskDescription;
    Timestamp editedAt;
    LocalDate deadline;
    Boolean status;

}
