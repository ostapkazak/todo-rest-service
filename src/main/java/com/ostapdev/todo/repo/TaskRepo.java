package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task,Long>,CustomTaskRepo {

}
