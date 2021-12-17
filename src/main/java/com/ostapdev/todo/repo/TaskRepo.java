package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>,CustomTaskRepo {
    int deleteAllById(Long id);
}
