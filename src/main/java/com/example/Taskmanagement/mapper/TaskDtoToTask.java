package com.example.Taskmanagement.mapper;


import com.example.Taskmanagement.dto.TaskDto;
import com.example.Taskmanagement.models.Task;

public class TaskDtoToTask {
   public static Task convert(TaskDto taskDto) {
      Task task = new Task();
      task.setTaskTitle(taskDto.getTaskTitle());
      task.setTaskDescription(taskDto.getTaskDescription());
      task.setDeadline(taskDto.getDeadline());
      task.setStatus(taskDto.getStatus());
      return task;
   }
}
