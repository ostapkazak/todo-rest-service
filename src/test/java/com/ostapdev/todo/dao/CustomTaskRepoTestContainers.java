package com.ostapdev.todo.dao;

import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.repo.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ActiveProfiles({"test"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomTaskRepoTestContainers {
    @Autowired
    private TaskRepo taskRepo;

    @Test
    public void Find_IncludeCompleted_TaskNotNull(){
        Task task = new Task("desc",true, Account.builder().id(1L).build());
        taskRepo.save(task);

        List<Task> tasksFromDB = taskRepo.find("",true,1L);

        assertNotNull(tasksFromDB.get(0));
    }
}
