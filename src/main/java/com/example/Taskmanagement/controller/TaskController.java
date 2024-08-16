

package com.example.Taskmanagement.controller;


import java.time.LocalDate;

import com.example.Taskmanagement.annotation.MeasureTime;
import com.example.Taskmanagement.annotation.ValidateHeader;
import com.example.Taskmanagement.dto.Response.CustomResponse;
import com.example.Taskmanagement.dto.TaskDto;
import com.example.Taskmanagement.models.User;
import com.example.Taskmanagement.util.UserContext;
import com.example.Taskmanagement.repo.UserRepo;
import com.example.Taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/task"})
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@ValidateHeader
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    UserRepo userRepo;


    @GetMapping
    @MeasureTime
    public ResponseEntity<?> getAllTasks(@RequestHeader String token) {
        CustomResponse<?> customResponse = this.taskService.getAllTasks(token);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskDto taskDto, @RequestHeader String token) {
        CustomResponse<?> customResponse = this.taskService.save(taskDto, token);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @GetMapping({"{id}"})
    public ResponseEntity<?> getTask(@PathVariable int id, @RequestHeader String token) {
        CustomResponse<?> customResponse = this.taskService.getParticularTask(id, token);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> deleteTask(@PathVariable int id, @RequestHeader String token) {
        User user = UserContext.getUser();
        CustomResponse<?> customResponse = this.taskService.delete(id, user);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @PutMapping({"{taskId}"})
    public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody TaskDto taskDto, @RequestHeader String token) {
        CustomResponse<?> customResponse = this.taskService.update(taskId, taskDto, token);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }

    @GetMapping({"filter"})
    public ResponseEntity<?> getTasks(@RequestHeader String token, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam(required = false) Boolean status) {
        CustomResponse<?> customResponse = this.taskService.filter(date, status, token);
        return new ResponseEntity<>(customResponse, HttpStatus.valueOf(customResponse.getStatus()));
    }
}
