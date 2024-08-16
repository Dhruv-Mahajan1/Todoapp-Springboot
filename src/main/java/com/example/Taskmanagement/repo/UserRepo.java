package com.example.Taskmanagement.repo;

import java.util.Optional;

import com.example.Taskmanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);

    Optional<User> findById(int userId);
}

