package com.example.Taskmanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    int taskId;
    Timestamp createdAt;
    String taskTitle;
    String taskDescription;
    Timestamp editedAt;
    LocalDate deadline;
    Boolean status;
    @ManyToOne
    @JoinColumn(
            name = "created_by_user_id",
            referencedColumnName = "userId"
    )
    private User createdByUser;


}