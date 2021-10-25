package com.ostapdev.todo.dao;

public interface TodoDao {
    void add(String taskDescription);

    void toggle(Integer taskId);

    void print(boolean isAll);

    void delete(Integer taskId);

    void edit(Integer taskId,String taskDescription);

    void search(String substring);
}
