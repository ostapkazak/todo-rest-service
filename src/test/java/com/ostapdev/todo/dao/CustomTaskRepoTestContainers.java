package com.ostapdev.todo.dao;

import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.repo.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ActiveProfiles({"test"})
@DataJpaTest
public class CustomTaskRepoTestContainers {
    @Autowired
    private TaskRepo taskRepo;

    @Test
    public void Find_IncludeCompleted_TaskNotNull() throws ExecutionException, InterruptedException {
        Task task = new Task("desc",true, Account.builder().id(1L).build());
        taskRepo.save(task);

        List<Task> tasksFromDB = taskRepo.find("",true,1L).get();

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(()-> assertTrue(tasksFromDB.size()>0));
        assertNotNull(tasksFromDB.get(0));
    }
}
