package com.example.Taskmanagement.repo;


import java.time.LocalDate;
import java.util.List;

import com.example.Taskmanagement.models.Task;
import com.example.Taskmanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Modifying
    @Query("SELECT t FROM Task t WHERE t.deadline  = :date AND t.createdByUser.userId = :userId")
    List<Task> findTasksByDate(@Param("date") LocalDate date, @Param("userId") int userId);

    @Modifying
    @Query("SELECT t FROM Task t WHERE t.deadline = :date AND t.status = :taskstatus AND t.createdByUser.userId = :userId")
    List<Task> findstatusTasksByDate(@Param("date") LocalDate date, @Param("taskstatus") boolean taskstatus, @Param("userId") int userId);

    @Modifying
    @Query("SELECT t FROM Task t WHERE t.status = :taskstatus AND t.createdByUser.userId = :userId")
    List<Task> findByStatus(@Param("taskstatus") boolean taskstatus, @Param("userId") int userId);

    List<Task> findByCreatedByUser(User user);
}