package com.ostapdev.todo;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.service.TodoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoApp {
    public static void main(String[] args) {
        final String printCommand = "print";
        final String printAllCommand = "print all";
        final String addCommand = "add";
        final String toggleCommand = "toggle";
        final String quitCommand = "quit";

        TodoDao service = new TodoService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try {
                String inputLine = reader.readLine();

                if (inputLine.startsWith(addCommand)){
                    service.add(inputLine.replace(addCommand,"").trim());
                }

                else if (inputLine.startsWith(printCommand)){
                    if (inputLine.equals(printCommand)) service.print(false);
                    else if (inputLine.equals(printAllCommand)) service.print(true);
                    else System.out.println("Некорректный аргумент для команды");
                }

                else if (inputLine.startsWith(toggleCommand)){
                    try {
                        service.toggle(Integer.parseInt(inputLine.replace(toggleCommand,"").trim()));
                    }
                    catch (NumberFormatException e){
                        System.out.println("Введенный аргумент не является целым числом");
                    }
                }

                else if (inputLine.equals(quitCommand)){
                    break;
                }

                else {
                    System.out.println("Некорректная команда");
                }
            }catch (IOException e){
                System.out.println("IOException");
            }
        }
    }
}
