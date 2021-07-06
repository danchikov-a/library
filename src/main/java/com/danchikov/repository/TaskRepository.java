package com.danchikov.repository;

import com.danchikov.entity.Task;
import com.danchikov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByName(String name);
    Optional<Task> findById(Long id);
    List<Task> findAllByUserOfTask(String userOfTask);
}
