package service;

import dao.TodoDao;
import model.Task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class TodoService implements TodoDao {
    private final Map<Integer,Task> tasks = new LinkedHashMap<>();

    @Override
    public void add(String taskDescription) {
        tasks.put(1,new Task(taskDescription,false));
        //System.out.println("Задача успешно добавлена");
    }

    @Override
    public void toggle(Integer taskId) {
        Task task = tasks.get(taskId);
        try {
            task.setDone(!task.isDone());
            tasks.put(taskId,task);
            //System.out.println("Статус задачи успешно изменён");
        }catch (NullPointerException e){
            //System.out.println("Задачи с таким идентификатором нет");
        }
    }

    @Override
    public void print(boolean isAll) {
        if (tasks.isEmpty()){
            //System.out.println("Список задач пуст");
            return;
        }

        AtomicBoolean hasUnDone = new AtomicBoolean(false);

        tasks.forEach((id, task) -> {
            if (isAll) System.out.println(id + ". " + (task.isDone() ? "X" : " ") + " " + task.getTaskDescription());

            else {
                if (!task.isDone()) {
                    System.out.println(id + ". " + task.getTaskDescription());
                    hasUnDone.set(true);
                }
            }
        });

        if (!hasUnDone.get() && !isAll){
            //System.out.println("Все задачи выполнены");
        }
    }
}
