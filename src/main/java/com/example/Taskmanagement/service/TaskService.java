

package com.example.Taskmanagement.service;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.Taskmanagement.dto.Response.CustomResponse;
import com.example.Taskmanagement.dto.TaskDto;
import com.example.Taskmanagement.dto.TaskResponseDto;
import com.example.Taskmanagement.exception.TaskNotFoundException;
import com.example.Taskmanagement.exception.UserNotTheOwnerException;
import com.example.Taskmanagement.mapper.TaskDtoToTask;
import com.example.Taskmanagement.mapper.TaskToTaskResponseDto;
import com.example.Taskmanagement.models.Task;
import com.example.Taskmanagement.models.User;
import com.example.Taskmanagement.repo.TaskRepo;
import com.example.Taskmanagement.repo.UserRepo;
import com.example.Taskmanagement.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    UserRepo userRepo;


    public List<TaskResponseDto> converttodto(List<Task> reqTasks) {
        List<TaskResponseDto> response = new ArrayList<>();

        for(int i = 0; i < reqTasks.size(); ++i) {
            Task task = (Task)reqTasks.get(i);
            TaskResponseDto taskResponseDto = TaskToTaskResponseDto.convert(task);
            response.add(taskResponseDto);
        }

        return response;
    }

    public CustomResponse<?> save(TaskDto taskDto, String token) {
        User user = UserContext.getUser();
        Task task = TaskDtoToTask.convert(taskDto);
        task.setCreatedByUser(user);
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        this.taskRepo.save(task);
        TaskResponseDto taskResponseDto = TaskToTaskResponseDto.convert(task);
        return new CustomResponse<>("Task Created", HttpStatus.OK.value(), taskResponseDto);
    }

    public CustomResponse<?> getParticularTask(int task_id, String token) {
        User user = UserContext.getUser();
        Task reqTask = (Task)this.taskRepo.findById(task_id).orElseThrow(() -> {
            return new TaskNotFoundException("Task Not Found");
        });
        if (reqTask.getCreatedByUser() != user) {
            throw new UserNotTheOwnerException("User is not the owner of this resource");
        } else {
            TaskResponseDto taskResponseDto = TaskToTaskResponseDto.convert(reqTask);
            return new CustomResponse<>("Task ", HttpStatus.OK.value(), taskResponseDto);
        }
    }

    public CustomResponse<?> delete(int task_id, User user) {
        Task reqTask = (Task)this.taskRepo.findById(task_id).orElseThrow(() -> {
            return new TaskNotFoundException("Task Not Found");
        });
        if (reqTask.getCreatedByUser() != user) {
            throw new UserNotTheOwnerException("User is not the owner of this resource");
        } else {
            this.taskRepo.deleteById(reqTask.getTaskId());
            return new CustomResponse<>("Task Deleted", HttpStatus.OK.value(), (Object)null);
        }
    }

    public CustomResponse<?> getAllTasks(String token) {
        User user = UserContext.getUser();
        List<Task> reqTasks = this.taskRepo.findByCreatedByUser(user);
        return new CustomResponse<>("List of the tasks", HttpStatus.OK.value(), this.converttodto(reqTasks));
    }

    public CustomResponse<?> update(int taskId, TaskDto taskDto, String token) {
        User user = UserContext.getUser();
        Task task = (Task)this.taskRepo.findById(taskId).orElseThrow(() -> {
            return new TaskNotFoundException("Task Not Found");
        });
        if (task.getCreatedByUser() != user) {
            throw new UserNotTheOwnerException("User is not the owner of this resource");
        } else {
            task.setTaskDescription(taskDto.getTaskDescription());
            task.setTaskTitle(taskDto.getTaskTitle());
            task.setDeadline(taskDto.getDeadline());
            task.setStatus(taskDto.getStatus());
            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
            task.setEditedAt(currentTimeStamp);
            this.taskRepo.save(task);
            TaskResponseDto taskResponseDto = TaskToTaskResponseDto.convert(task);
            return new CustomResponse<>("Task Updated", HttpStatus.OK.value(), taskResponseDto);
        }
    }

    public CustomResponse<?> filter(LocalDate date, Boolean taskstatus, String token) {
        User user = UserContext.getUser();
        List reqTasks;
        if (taskstatus != null && date != null) {
            reqTasks = this.taskRepo.findstatusTasksByDate(date, taskstatus, user.getUserId());
        } else if (taskstatus != null) {
            reqTasks = this.taskRepo.findByStatus(taskstatus, user.getUserId());
        } else if (date != null) {
            reqTasks = this.taskRepo.findTasksByDate(date, user.getUserId());
        } else {
            reqTasks = this.taskRepo.findByCreatedByUser(user);
        }

        return new CustomResponse<>("List of the tasks", HttpStatus.OK.value(), this.converttodto(reqTasks));
    }
}
