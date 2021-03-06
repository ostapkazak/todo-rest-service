package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class CustomTaskRepoImpl implements CustomTaskRepo{
    private final EntityManager entityManager;

    @Override
    public CompletableFuture<List<Task>> find(String targetDescription, Boolean includeCompleted, Long accountId) {
        StringBuilder jpql = new StringBuilder("from Task t ");
        List<String> conditions = new ArrayList<>();

        if (Strings.isNotBlank(targetDescription)) {
            conditions.add("t.taskDescription like :desc");
        }

        if (includeCompleted != null){
            if (!includeCompleted){
                conditions.add("t.done = false");
            }
        }

        jpql.append("where t.account.id = ").append(accountId);

        if (!conditions.isEmpty()) {
            jpql.append(String.join(" and ",conditions));
        }


        TypedQuery<Task> typedQuery = entityManager.createQuery(jpql.toString(), Task.class);

        if (Strings.isNotBlank(targetDescription)) {
            typedQuery.setParameter("desc","%"+targetDescription+"%");
        }

        return CompletableFuture.completedFuture(typedQuery.getResultList());
    }
}
