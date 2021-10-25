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
        final String deleteCommand = "delete";
        final String editCommand = "edit";
        final String searchCommand = "search";

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

                else if (inputLine.startsWith(deleteCommand)){
                    try {
                        service.delete(Integer.parseInt(inputLine.replace(deleteCommand,"").trim()));
                    }
                    catch (NumberFormatException e){
                        System.out.println("Введенный аргумент не является целым числом");
                    }
                }

                else if (inputLine.startsWith(editCommand)){
                    try {
                        String[] commandArgs = inputLine.replace(editCommand,"").trim().split(" ",2);
                        service.edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Введенный аргумент не является целым числом");
                    }
                }

                else if (inputLine.startsWith(searchCommand)){
                    service.search(inputLine.replace(searchCommand,"").trim());
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
