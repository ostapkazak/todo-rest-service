package com.ostapdev.todo.parser.command;


public class DeleteCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "delete";

        if (isCommand(inputLine,command)){
            try {
                getService().delete(Integer.parseInt(inputLine.replace(command,"").trim()));
            }
            catch (NumberFormatException e){
                System.out.println("Введенный аргумент не является целым числом");
            }
        }
    }
}
