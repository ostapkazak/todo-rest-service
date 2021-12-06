package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findAllByDone(boolean done);
    List<Task> findAllByTaskDescriptionIsLike(String description);
    List<Task> findAllByDoneAndTaskDescriptionIsLike(boolean done,String description);
}
