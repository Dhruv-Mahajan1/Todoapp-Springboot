package com.example.Taskmanagement.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    int userId;
    @Column(
            name = "username",
            unique = true
    )
    String userName;
    @Column(
            name = "userpassword"
    )
    private String userPassword;
    @OneToMany(
            mappedBy = "createdByUser",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Task> tasks;

}