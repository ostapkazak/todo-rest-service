package dao;

public interface TodoDao {
    void add(String taskDescription);

    void toggle(Integer taskId);

    void print(boolean isAll);
}
