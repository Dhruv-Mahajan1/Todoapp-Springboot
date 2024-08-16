package com.example.Taskmanagement.mapper;


import com.example.Taskmanagement.dto.TaskResponseDto;
import com.example.Taskmanagement.models.Task;

public class TaskToTaskResponseDto {
   public static TaskResponseDto convert(Task task) {
      TaskResponseDto taskResponseDto = new TaskResponseDto(task.getTaskId(), task.getCreatedAt(), task.getTaskTitle(), task.getTaskDescription(), task.getEditedAt(), task.getDeadline(), task.getStatus());
      return taskResponseDto;
   }
}
